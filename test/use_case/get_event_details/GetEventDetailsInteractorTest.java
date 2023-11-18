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
import java.util.ArrayList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class GetEventDetailsInteractorTest {
    private GetEventDetailsInteractor getEventDetailsInteractor;
    private Event currentEvent;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void init() throws IOException {
        EventFactory eventFactory = new CommonEventFactory();
        OrganizerFactory organizerFactory = new CommonOrganizerFactory();
        LocationFactory locationFactory = new CommonLocationFactory();
        InMemoryEventsDataAccessObject dataAccessObject = new InMemoryEventsDataAccessObject();

        /*
            public Organizer create(String username, String password, Event eventOf,
            ArrayList<Event> joinedEvents, ArrayList<Event> createdEvents, int age, String sex, int userid, String contact, Location location) {

         */
        Location eventLocation = null;
        try{
            eventLocation = locationFactory.makeLocation("(47.64054,-122.12934)");
        } catch (IOException e){
            eventLocation = locationFactory.create(new String[]{"47.64054", "122.12934"},
                    "15800 NE One Microsoft Way, Redmond, WA 98052, United States", "United States");
        }

        ArrayList<Event> createdEvents = new ArrayList<>();
        createdEvents.add(currentEvent);
        Organizer eventOrganizer = organizerFactory.create("Bob", "123", currentEvent,
                new ArrayList<Event>(), createdEvents, 20, "m", "bob@gmail.com", eventLocation);
        Event event = eventFactory.create(0, "test event", eventOrganizer, eventLocation,
                new ArrayList<Integer>(), new ArrayList<Integer>(), "2014-04-08 12:30", "test event", "have fun",
                false, 10);
        dataAccessObject.save(event);
        currentEvent = event;
        GetEventDetailsDataAccessInterface eventDetailsDataAccessInterface = (GetEventDetailsDataAccessInterface)dataAccessObject;
        GetEventDetailsOutputBoundary mockPresenter = new MockGetEventDetailsPresenter();
        getEventDetailsInteractor = new GetEventDetailsInteractor(mockPresenter, eventDetailsDataAccessInterface);
    }

    /**
     * Sets up a print stream before the test (used for checking printed outputs)
     */
    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restores the stream after the test (used for checking printed outputs)
     */
    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    /**
     * Tests if the presenter correctly gets the correct output, i.e., the event details.
     */
    @Test
    public void eventDetailsTest(){
        GetEventDetailsInputData inputData = new GetEventDetailsInputData(currentEvent.getEventID());
        getEventDetailsInteractor.execute(inputData);
        String printedOutput = outContent.toString();
        String ownerUser = ((User)currentEvent.getOwner()).getUsername();
        String eventName = currentEvent.getEventName();
        String eventDescription = currentEvent.getDescription();
        String eventAddress = currentEvent.getLocation().getAddress();
        String eventDate = currentEvent.getTime();
        String eventCapacity = currentEvent.getPeopleJoined().size() + "/" + currentEvent.getCapacity().toString();
        String output = String.format("%s,%s,%s,%s,%s,%s",ownerUser,eventName,eventDescription,eventAddress,eventDate,eventCapacity);
        assertEquals(output + "\n", printedOutput);
    }


}