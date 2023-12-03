package use_case.get_event_details;

import data_access.InMemoryEventsDataAccessObject;
import entity.Events.CommonEventFactory;
import entity.Events.Event;

import entity.Events.EventFactory;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.*;
import interface_adapter.get_event_details.MockGetEventDetailsPresenter;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Tests for the event details use case
 */
public class GetEventDetailsInteractorTest {
    private Event currentEvent;
    private InMemoryEventsDataAccessObject dataAccessObject = new InMemoryEventsDataAccessObject();


    @Before
    public void init() throws IOException {
        EventFactory eventFactory = new CommonEventFactory();
        LocationFactory locationFactory = new CommonLocationFactory();
        Location eventLocation;
        try{
            eventLocation = locationFactory.makeLocation("(47.64054,-122.12934)");
        } catch (IOException e){
            eventLocation = locationFactory.create(new String[]{"47.64054", "122.12934"},
                    "15800 NE One Microsoft Way, Redmond, WA 98052, United States", "United States");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse("2022-04-08 12:30",formatter);
        Event event = eventFactory.create(0, "test event", "Bob", eventLocation,
                new ArrayList<String>(), new ArrayList<String>(), time, "test event", "have fun",
                false, 10);
        dataAccessObject.save(event);
        currentEvent = event;
    }

    /**
     * Tests if the presenter correctly gets the correct output, i.e., the event details.
     */
    @Test
    public void eventDetailsTest(){
        String ownerUser = currentEvent.getOwnerUser();
        String eventName = currentEvent.getEventName();
        String eventDescription = currentEvent.getDescription();
        String eventAddress = currentEvent.getLocation().getAddress();
        String eventDate = currentEvent.getTime().toString();
        String eventCapacity = currentEvent.getPeopleJoined().size() + "/" + currentEvent.getCapacity().toString();
        String expectedOutput = String.format("%s,%s,%s,%s,%s,%s",ownerUser,eventName,eventDescription,eventAddress,eventDate,eventCapacity);
        GetEventDetailsOutputBoundary mockPresenter = new GetEventDetailsOutputBoundary() {
            @Override
            public void prepareView(GetEventDetailsOutputData outputData) {
                String actualOutput = String.format("%s,%s,%s,%s,%s,%s",outputData.getOwnerUser(), outputData.getEventName(),
                        outputData.getDescription(), outputData.getEventAddress(), outputData.getDate(),outputData.getCapacity());
                assertEquals(expectedOutput,actualOutput);
            }
        };
        GetEventDetailsInputData inputData = new GetEventDetailsInputData(currentEvent.getEventID(), false);
        GetEventDetailsInteractor interactor = new GetEventDetailsInteractor(mockPresenter, dataAccessObject);
        interactor.execute(inputData);
    }
}