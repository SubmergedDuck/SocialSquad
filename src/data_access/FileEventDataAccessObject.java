package data_access;

import entity.Events.Event;
import entity.Events.EventFactory;
import entity.Location.LocationFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class FileEventDataAccessObject {
    private final File eventDatabase;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<Integer, Event> eventsToID = new HashMap<>();
    private final EventFactory eventFactory;
    private final LocationFactory locationFactory;

    public FileEventDataAccessObject(String csvPath,EventFactory eventFactory, LocationFactory locationFactory){
        this.eventDatabase = new File(csvPath);
        this.eventFactory = eventFactory;
        this.locationFactory = locationFactory;

        String[] labels = {"owner", "eventID", "eventName", "coordinates", "peopleJoined", "peopleWaitlisted",
                "time", "type", "description", "privacy", "capacity"};
        for (int i = 0; i < labels.length; i++){
            headers.put(labels[i], i);
        }
    }
    private void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(eventDatabase));
            writer.write(String.join(",", headers.keySet()));
            for (Event event : eventsToID.values()){
                String[] coordinates = event.getLocation().getCoordinates();
                String formattedCoordinates = String.format("(%s,%s)", coordinates[0], coordinates[1]);
                String peopleJoined = formatStringList(event.getPeopleJoined());
                String peopleWaitlisted = formatStringList(event.getPeopleWaitlisted());
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", event.getOwnerUser(), event.getEventID(),
                        event.getEventName(), peopleJoined, peopleWaitlisted, event.getTime().toString(), event.getDescription(),
                        event.getPrivacy().toString(),event.getCapacity().toString());
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String formatStringList(ArrayList<String> stringList){
        String currentString = "";
        for (int i = 0; i < stringList.size(); i++){
            if (i == stringList.size() - 1){
                currentString = currentString + stringList.get(i);
            } else {
                currentString = currentString + stringList.get(i) + ",";
            }
        }
        return currentString;
    }
}
