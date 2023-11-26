package data_access;

import entity.Events.Event;
import entity.Events.EventFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class FileEventDataAccessObject {
    private final File eventDatabase;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<Integer, Event> eventsToID = new HashMap<>();
    private final EventFactory eventFactory;
    private final LocationFactory locationFactory;
    private final String elementSeperator = " ";
    private final DateTimeFormatter formatter;

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

    public void save(Event event){
        eventsToID.put(event.getEventID(), event);
        save();
    }
    public Event getEvent(int id){
        return eventsToID.get(id);
    }
    private void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(eventDatabase));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            for (Event event : eventsToID.values()){
                String[] coordinates = event.getLocation().getCoordinates();
                String formattedCoordinates = String.format("(%s%s%s)", coordinates[0], elementSeperator, coordinates[1]);
                String peopleJoined = formatStringList(event.getPeopleJoined());
                String peopleWaitlisted = formatStringList(event.getPeopleWaitlisted());
                String eventTime = event.getTime().format(formatter);
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
    public String getElementSeperator(){return this.elementSeperator;}
    private String formatStringList(ArrayList<String> stringList){
        String currentString = "";
        for (int i = 0; i < stringList.size(); i++){
            if (i == stringList.size() - 1){
                currentString = currentString + stringList.get(i);
            } else {
                currentString = currentString + stringList.get(i) + elementSeperator;
            }
        }
        return currentString;
    }
}
