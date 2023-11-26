package data_access;

import entity.Events.CommonEventFactory;
import entity.Events.Event;
import entity.Events.EventFactory;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileEventDataAccessObjectTest {
    private FileEventDataAccessObject eventDataAccessObject;
    private Event testEvent1;
    private final String csvPath = "TestEventDatabase.csv";
    private final File eventDatabase = new File(csvPath);

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Before
    public void init() throws IOException{
        EventFactory eventFactory = new CommonEventFactory();
        LocationFactory locationFactory = new CommonLocationFactory();
        Location eventLocation = locationFactory.makeLocation("(43.651070,-79.347015)");
        LocalDateTime eventTime = LocalDateTime.parse("2023-03-04 11:30",formatter);
        String[] usersList1 = {"user1","user2"};
        String[] usersList2 = {"user3","user4"};
        ArrayList<String> users1 = new ArrayList<>(Arrays.asList(usersList1));
        ArrayList<String> users2 = new ArrayList<>(Arrays.asList(usersList2));
        testEvent1 = eventFactory.create(0,"testEvent1","owner1",eventLocation,users1,
                users2, eventTime,"testEvent","testing",false,10);
        eventDataAccessObject = new FileEventDataAccessObject(csvPath,eventFactory,locationFactory,formatter);
        eventDataAccessObject.save(testEvent1);
    }

    @Test
    public void readHeader(){
        try (BufferedReader reader = new BufferedReader(new FileReader(eventDatabase))) {
            String header = reader.readLine();
            assertEquals("owner,eventID,eventName,coordinates,peopleJoined,peopleWaitlisted,time,type,description,privacy,capacity",header);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readEventLine(){
        try (BufferedReader reader = new BufferedReader(new FileReader(eventDatabase))) {
            reader.readLine();
            String userLine = reader.readLine();
            String[] eventOneCoordinates = testEvent1.getLocation().getCoordinates();
            String formattedCoordinates = String.format("(%s%s%s)",eventOneCoordinates[0],
                    eventDataAccessObject.getElementSeperator(),eventOneCoordinates[1]);
            String peopleJoined = formatStringList(testEvent1.getPeopleJoined());
            String peopledWaitlisted = formatStringList(testEvent1.getPeopleWaitlisted());
            String eventTime = testEvent1.getTime().format(formatter);
            String expectedResult = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",testEvent1.getOwnerUser(),
                    testEvent1.getEventID().toString(),testEvent1.getEventName(),formattedCoordinates,peopleJoined,peopledWaitlisted,eventTime
                    ,testEvent1.getType(),testEvent1.getDescription(),testEvent1.getPrivacy().toString(),testEvent1.getCapacity().toString());
            assertEquals(expectedResult,userLine);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void saveEvent(){
        assertEquals(eventDataAccessObject.getEvent(0).getEventName(),testEvent1.getEventName());
    }

    private String formatStringList(ArrayList<String> stringList){
        String currentString = "";
        for (int i = 0; i < stringList.size(); i++){
            if (i == stringList.size() - 1){
                currentString = currentString + stringList.get(i);
            } else {
                currentString = currentString + stringList.get(i) + eventDataAccessObject.getElementSeperator();
            }
        }
        return currentString;
    }

}