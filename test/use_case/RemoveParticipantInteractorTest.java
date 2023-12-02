package use_case;

import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEventFactory;
import entity.Events.Event;
import entity.Events.EventFactory;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import unused_usecases___.interface_adapter.remove_participant.MockRemoveParticipantPresenter;

import unused_usecases___.usecases.remove_participant.RemoveParticipantDataAccessInterface;
import unused_usecases___.usecases.remove_participant.RemoveParticipantInputData;
import unused_usecases___.usecases.remove_participant.RemoveParticipantInteractor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RemoveParticipantInteractorTest {

    private RemoveParticipantInteractor removeEventInteractor;

    private RemoveParticipantDataAccessInterface inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();

    private RemoveParticipantDataAccessInterface inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();

    private User testUser;

    private Event testEvent;

    /*
    Initializes the variables above for testing.
     */
    @Before
    public void init() throws IOException {
        UserFactory userFactory = new CommonUserFactory();
        EventFactory eventFactory = new CommonEventFactory();
        LocationFactory locationFactory = new CommonLocationFactory();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime eventTime = LocalDateTime.parse("2016-03-04 11:30", formatter);

        //Creates a test location
        Location location = locationFactory.makeLocation("(47.64054,-122.12934)");

        //Creates and adds a user to the in memory user DAO.
        User user = userFactory.create("Bob", "123", 20, "m", "bob@gmail.com");
        InMemoryUsersDataAccessObject userDAO = (InMemoryUsersDataAccessObject)inMemoryUsersDataAccessObject;
        userDAO.save(user);


        //Creates and adds an event to the in memory event DAO.
        ArrayList<String> joinedUsers = new ArrayList<>();
        joinedUsers.add(user.getUsername());
        Event event  = eventFactory.create(0, "Test Event", "Owner", location, joinedUsers, new ArrayList<>(),
                eventTime, "Event", "Testing", false, 5);
        InMemoryEventsDataAccessObject eventDAO = (InMemoryEventsDataAccessObject)inMemoryEventsDataAccessObject;
        eventDAO.save(event);

        //Will add the created event to the user's joined events.
        ArrayList<Event> joinedEvents = user.getJoinedEvents();
        joinedEvents.add(event);

        //Will store the user and event to check if their attributes change in the tests.
        testUser = user;
        testEvent = event;

        //Creates the interactor
        removeEventInteractor = new RemoveParticipantInteractor(inMemoryUsersDataAccessObject,
                inMemoryEventsDataAccessObject, new MockRemoveParticipantPresenter());
    }

    /*
    Tests if an event was removed from a user's joinedEvents instance.
     */
    @Test
    public void removeJoinedEvents(){
        RemoveParticipantInputData input = new RemoveParticipantInputData("Bob", "0");
        assertEquals(1, testUser.getJoinedEvents().size());
        removeEventInteractor.execute(input);
        assertEquals(0, testUser.getJoinedEvents().size());
    }

    /*
    Tests if a user was removed from an event's joinedUsers instance.
     */
    @Test
    public void removeJoinedUsers(){
        RemoveParticipantInputData input = new RemoveParticipantInputData("Bob", "0");
        assertEquals(1, testEvent.getPeopleJoined().size());
        removeEventInteractor.execute(input);
        assertEquals(0, testEvent.getPeopleJoined().size());
    }




}