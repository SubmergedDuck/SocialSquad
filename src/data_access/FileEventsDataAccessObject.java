package data_access;


import entity.Location.Location;
import entity.Events.*;
import entity.Location.LocationFactory;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileEventsDataAccessObject implements GetDirectionDataAccessInterface,
                                                   CreateEventDataAccessInterface,
                                                   SearchEventDataAccessInterface,
                                                   JoinEventDataAccessInterface {
    private final File eventDatabase;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<Integer, Event> events = new HashMap<>();

    private EventFactory eventFactory;
    private InviteOnlyEventFactory inviteEventFactory;
    private RestrictedEventFactory restrictedEventFactory;

    private LocationFactory locationFactory;

    public FileEventsDataAccessObject(String csvPath, EventFactory eventFactory, InviteOnlyEventFactory
            inviteEventFactory, RestrictedEventFactory restrictedEventFactory, LocationFactory locationFactory)
            throws IOException {

        this.eventDatabase = new File(csvPath);
        this.eventFactory = eventFactory;
        this.inviteEventFactory = inviteEventFactory;
        this.restrictedEventFactory = restrictedEventFactory;
        this.locationFactory = locationFactory;


        String[] labels = {"eventID", "eventName", "owner", "private", "ageRestriction", "sexRestriction", "capacity", "peopleJoined", "peopleInvited", "time", "type", "description"};
        for (int i = 0; i < labels.length; i++){
            headers.put(labels[i], i);
        }

        if (eventDatabase.length() == 0){
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(eventDatabase))){
                String header = reader.readLine();

                assert header.equals("eventID,eventName,owner,location,private,ageRestriction,sexRestriction,capacity,peopleJoined,peopleWaitlisted,peopleInvited,time,type,description");

                String row;
                while ((row = reader.readLine()) != null){
                    String[] col = row.split(",");
                    Integer eventID = Integer.valueOf((col[headers.get("eventID")]));
                    String eventName = String.valueOf(col[headers.get("eventName")]);

                    //Need to get owner ID and access the user database to get the user.
                    Integer owner = Integer.valueOf(col[headers.get("owner")]);

                    //Location in the csv will be "longitude, latitude, address, country"
                    String[] locationCols = col[headers.get("location")].split(",");
                    //Location factory parameters: List coordinates, String address, String country
                    List<Integer> coordinates = List.of(new Integer[]{Integer.valueOf(col[0]), Integer.valueOf(col[1])});
                    Location eventLocation = this.locationFactory.create(coordinates, col[2], col[3]);

                    //In the CSV file, privacy is a string that is true or false
                    Boolean privacy = false;
                    if (String.valueOf(col[headers.get("privacy")]).toLowerCase().equals("true")){
                        privacy = true;
                    }

                    Integer ageRestriction = null;
                    if (!col[headers.get("ageRestriction")].equals("")){
                        //Events without any age restriction have their ageRestriction column as an empty string in the csv.
                        //If statement prevents any errors trying to parse an empty string.
                        ageRestriction = Integer.valueOf(col[headers.get("ageRestriction")]);
                    }
                    String sexRestriction = String.valueOf(col[headers.get("sexRestriction")]);

                    Integer capacity = null;
                    if (!col[headers.get("capacity")].equals(null)){
                        capacity = Integer.valueOf(col[headers.get("capacity")]);
                    }

                    //In the CSV, the peopleJoined column would look something like "1, 5, 8" where the numbers are the IDs.
                    String[] joinedUserIDs = col[headers.get("peopleJoined")].split(",");
                    ArrayList<Integer> peopleJoined = new ArrayList<>();
                    for (String id : joinedUserIDs){
                        peopleJoined.add(Integer.valueOf(id));
                    }

                    //In the CSV, the waitlistedPeople column is a string "1, 5, 8" where the numbers are the waitlisted user IDs.
                    String[] waitlistedIDs = col[headers.get("peopleWaitlisted")].split(",");
                    ArrayList<Integer> peopleWaitlisted = new ArrayList<>();
                    for (String id : waitlistedIDs){
                        peopleWaitlisted.add(Integer.valueOf(id));
                    }

                    String eventTime = String.valueOf(col[headers.get("time")]);

                    String eventType = String.valueOf(col[headers.get("type")]);

                    String eventDescription = String.valueOf(col[headers.get("description")]);

                    String[] invitedIDs = col[headers.get("peopleInvited")].split(",");
                    ArrayList<Integer> peopleInvited = new ArrayList<>();

                    for (String id : invitedIDs){
                        peopleInvited.add(Integer.valueOf(id));
                    }

                    //Adds events to the hashmap. Each event is identified based on certain parameters that they have.
                    Event currentEvent = null;
                    if (!ageRestriction.equals(null) || !sexRestriction.equals("")){
                        //If conditions identify that the event is restricted.
                        currentEvent = (Event)this.restrictedEventFactory.create(eventID, eventName, owner, eventLocation,
                                peopleJoined, peopleWaitlisted, eventTime, eventType, eventDescription, privacy,
                                capacity, ageRestriction, sexRestriction);
                    } else if (!peopleInvited.get(0).equals(-1)){
                        //For non-invite only events, their peopleInvited column is set to -1, which can be seen in save()
                        //This will help to identify invite only events.
                        currentEvent = (Event)this.inviteEventFactory.create(eventID, eventName, owner, eventLocation,
                                peopleJoined, peopleWaitlisted, eventTime, eventType, eventDescription, privacy, capacity,
                                peopleInvited);
                    } else {
                        currentEvent = this.eventFactory.create(eventID, eventName, owner, eventLocation, peopleJoined,
                                peopleWaitlisted, eventTime, eventType, eventDescription, privacy, capacity);
                    }
                    this.events.put(currentEvent.getEventID(), currentEvent);
                }
            }
        }

    }

    public void save(Event event){
        events.put(event.getEventID(), event);
    }

    public Event get(int id){
        return events.get(id);
    }

    public ArrayList<Event> makeEvents(ArrayList<Integer> eventIDs) {
        ArrayList<Event> givenEvents = new ArrayList<>();
        for (Integer id : eventIDs){
            givenEvents.add(get(id));
        }
        return givenEvents;
    }

    private void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(eventDatabase));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Event event : events.values()){
                String ageRestriction = "";
                String sexRestriction = "";
                String peopleInvited = "-1";
                String capacity = "";

                if (event instanceof CommonRestrictedEvent){
                    ageRestriction = ((CommonRestrictedEvent) event).getAgeRestriction().toString();
                    sexRestriction = ((CommonRestrictedEvent) event).getSexRestriction();
                }
                if (event instanceof CommonInviteOnlyEvent){
                    peopleInvited = ((CommonInviteOnlyEvent) event).getPeopleInvited().toString();
                    capacity = event.getCapacity().toString();

                }
                //Order: eventID, eventName, owner, location, private, ageRestriction, sexRestriction, capacity, peoplejoined
                //peopleWaitlisted, peopleInvited, time, type, description
                String line = String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                        event.getEventID(), event.getEventName(), event.getOwnerID(), event.getLocation(),
                        event.getPrivacy(), ageRestriction, sexRestriction, capacity, event.getPeopleJoined(), event.getPeopleWaitlisted(),
                        peopleInvited, event.getTime(), event.getType(), event.getDescription());
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
