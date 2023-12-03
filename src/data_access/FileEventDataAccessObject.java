package data_access;

import entity.Events.Event;
import entity.Events.EventFactory;
import entity.Location.DistanceCalculator;
import entity.Location.DistanceCalculatorInterface;
import entity.Location.Location;
import entity.Location.LocationFactory;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.create_event.CreateEventEventDataAccessInterface;
import use_case.generate_static_map.GSMEventDataAccessInterface;
import use_case.get_direction.GetDirectionEventDataAccessInterface;
import use_case.get_event_details.GetEventDetailsDataAccessInterface;
import use_case.join_event.JoinEventEventDataAccessInterface;
import use_case.leave_event.LeaveEventEventDataAccessInterface;
import unused_usecases___.usecases.remove_participant.RemoveParticipantDataAccessInterface;
import unused_usecases___.usecases.search_event.SearchEventDataAccessInterface;
import unused_usecases___.usecases.search_event.SearchEventInputData;
import use_case.search_nearby.SearchNearbyDataAccessInterface;
import use_case.search_nearby.SearchNearbyInputData;
import unused_usecases___.usecases.view_participants.ViewParticipantsDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * File data access object for events.
 */
public class FileEventDataAccessObject implements SearchEventDataAccessInterface,
        RemoveParticipantDataAccessInterface, ViewParticipantsDataAccessInterface, GetDirectionEventDataAccessInterface,
        GetEventDetailsDataAccessInterface, CreateEventDataAccessInterface, SearchNearbyDataAccessInterface,GSMEventDataAccessInterface,
        CreateEventEventDataAccessInterface, JoinEventEventDataAccessInterface, LeaveEventEventDataAccessInterface {
    private final File eventDatabase;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<Integer, Event> eventsToID = new HashMap<>();
    private final EventFactory eventFactory;
    private final LocationFactory locationFactory;
    private final String elementSeperator = " ";
    private final DateTimeFormatter formatter;

    /**
     * Constructor for FileEventDataAccessObject. This reads the csv file and creates new event objects and saves it to the event map.
     * @param csvPath the csv file name
     * @param eventFactory an event factory
     * @param locationFactory a location factory
     * @param formatter localdatetime formatter
     * @throws IOException error when reading the csv file
     */
    public FileEventDataAccessObject(String csvPath,EventFactory eventFactory, LocationFactory locationFactory, DateTimeFormatter formatter) throws IOException {
        this.eventDatabase = new File(csvPath);
        this.eventFactory = eventFactory;
        this.locationFactory = locationFactory;
        this.formatter = formatter;

        String[] labels = {"owner", "eventID", "eventName", "coordinates", "peopleJoined", "peopleWaitlisted",
                "time", "type", "description", "privacy", "capacity"};
        for (int i = 0; i < labels.length; i++){
            headers.put(labels[i], i);
        }

        if (eventDatabase.length() == 0){
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(eventDatabase))){
                String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null){
                    String[] eventValues = row.split(",");

                    //Parsing values
                    String ownerUser = eventValues[headers.get("owner")];
                    Integer eventID = Integer.valueOf(eventValues[headers.get("eventID")]);
                    String eventName = eventValues[headers.get("eventName")];
                    String[] coordinates = eventValues[headers.get("coordinates")].split(elementSeperator);
                    Location eventLocation = locationFactory.makeLocation(String.format("(%s,%s)", coordinates[0], coordinates[1]));
                    String[] usernamesJoined = eventValues[headers.get("peopleJoined")].split(elementSeperator);
                    ArrayList<String> participantsJoined = new ArrayList<>(Arrays.asList(usernamesJoined));
                    String[] usernamesWaitlisted = eventValues[headers.get("peopleWaitlisted")].split(elementSeperator);
                    ArrayList<String> participantsWaitlisted = new ArrayList<>(Arrays.asList(usernamesWaitlisted));
                    LocalDateTime eventTime = LocalDateTime.parse(eventValues[headers.get("time")],formatter);
                    String eventType = eventValues[headers.get("type")];
                    String description = eventValues[headers.get("description")];
                    boolean privacy = false;
                    if (eventValues[headers.get("privacy")].toLowerCase().equals("true")){
                        privacy = true;
                    }
                    Integer capacity = Integer.valueOf(eventValues[headers.get("capacity")]);

                    //Creating the event object
                    Event createdEvent = eventFactory.create(eventID,eventName,ownerUser,eventLocation,participantsJoined,
                            participantsWaitlisted,eventTime,eventType,description,privacy,capacity);

                    //Saving the created event to the arraylist
                    eventsToID.put(createdEvent.getEventID(),createdEvent);
                }
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Saves an event and stores it in the file.
     * @param event the event that is being saved
     */
    public void save(Event event){
        System.out.println("temp point");
        eventsToID.put(event.getEventID(), event);
        save();
    }

    /**
     * Retrieves an event based on its ID
     * @param id the ID of the event
     * @return the selected event
     */
    public Event getEvent(int id){
        return eventsToID.get(id);
    }

    /**
     * Rewrites the entire csv file to save all events in the map.
     */
    private void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(eventDatabase));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            for (Event event : eventsToID.values()){
                String[] coordinates = event.getLocation().getCoordinates();
                String formattedCoordinates = String.format("%s%s%s", coordinates[0], elementSeperator, coordinates[1]);
                String peopleJoined = FormatStringList.formatStringList(event.getPeopleJoined(),elementSeperator);
                String peopleWaitlisted = FormatStringList.formatStringList(event.getPeopleWaitlisted(),elementSeperator);
                String eventTime = event.getTime().format(formatter);
                if (peopleJoined.equals("")){
                    peopleJoined = " "; //adding a space so that we can load from the csv without any error
                }
                if (peopleWaitlisted.equals("")){
                    peopleWaitlisted = " ";
                }
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", event.getOwnerUser(), event.getEventID(),
                        event.getEventName(), formattedCoordinates, peopleJoined, peopleWaitlisted, eventTime,
                        event.getType(), event.getDescription(), event.getPrivacy().toString(),event.getCapacity().toString());
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Provides the element seperator that seperates elements in a collection in the csv file.
     * @return the element seperator
     */
    public String getElementSeperator(){return this.elementSeperator;}

    @Override
    public HashMap<Integer, Event> getEvents(int amount) {
        HashMap<Integer, Event> outputMap = new HashMap<>();
        for (Integer key : eventsToID.keySet()){
            if (outputMap.size() < amount){
                outputMap.put(key, eventsToID.get(key));
            }
        }
        return outputMap;
    }

    @Override
    public String[] getEventCoordinates(int eventID) {
        Event event = eventsToID.get(eventID);
        return event.getLocation().getCoordinates();
    }

    @Override
    public void removeUser(String username, Integer eventID) {
        Event event = eventsToID.get(eventID);
        ArrayList<String> joinedUsernames = event.getPeopleJoined();
        joinedUsernames.remove(username);
    }

    @Override
    public ArrayList<Event> getNearbyEvent(SearchNearbyInputData inputData) throws IOException {
        ArrayList<Event> returnEvents = new ArrayList<>();
        ArrayList<Event> events = new ArrayList(eventsToID.values());
        String[] strCoord = inputData.getCoordinates();

        DistanceCalculatorInterface distanceCalculator = new DistanceCalculator();
        for (Event event: events) {
            if (distanceCalculator.within2KM(strCoord, event)) {
                returnEvents.add(event);
            }
        }
        return returnEvents;
    }

    @Override
    public ArrayList<Event> getFullMatchEvents(SearchEventInputData inputData) {
        ArrayList<Event> returnList = new ArrayList<>();
        for (Event event : eventsToID.values()){
            if (event.getEventName().equals(inputData.getSearchRequest())){
                returnList.add(event);
            }
        }
        return returnList;
    }

    @Override
    public ArrayList<Event> getPartialMatchEvents(SearchEventInputData inputData) {
        ArrayList<Event> returnList = new ArrayList<>();
        String[] keywords = inputData.getSearchRequest().split(" "); // Break down the search request into keywords to compare
        for (Event event : eventsToID.values()){
            String name = event.getEventName();
            for (String keyword : keywords){
                if (name.equals(keyword) || event.getType().equals(keyword)){
                    returnList.add(event);
                }
                if (event.getType().equals(keyword)){
                    returnList.add(event);
                }
            }
        }

        // remove full match event from the list
        ArrayList<Event> fullMatchEvents = getFullMatchEvents(inputData);
        for (Event event: fullMatchEvents) {
            if (returnList.contains(event)) {
                returnList.remove(event);
            }
        }
        return returnList;
    }

    @Override
    public Integer generateEventID() {
        Integer currentID = 0;
        for (Integer eventID : eventsToID.keySet()){
            //The new eventID will be the highest event ID.
            if (currentID <= eventID){
                currentID = currentID + 1;
            }
        }
        return currentID;
    }

    @Override
    public void userJoinEvent(String username, Integer eventID) {
        Event event = eventsToID.get(eventID);
        event.getPeopleJoined().add(username);
        save();
    }

    @Override
    public String getCapacity(Integer eventID) {
        Event event = eventsToID.get(eventID);
        return String.valueOf(event.getCapacity());
    }

    @Override
    public void userLeaveEvent(String username, Integer eventID) {
        Event event = eventsToID.get(eventID);
        ArrayList<String> joinedUsers = event.getPeopleJoined();
        joinedUsers.remove(username);
    }

    @Override
    public ArrayList<String> getPeopleJoined(Integer eventID) {
        Event event = eventsToID.get(eventID);
        return event.getPeopleJoined();
    }

    @Override
    public Event getEvent(Integer eventID) {
        return eventsToID.get(eventID);
    }

    @Override
    public List<String> getParticipants(Integer eventID) {
        return null;
    }
}
