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
import org.junit.Before;
import org.junit.Test;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.remove_participant.RemoveParticipantInteractor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

class RemoveParticipantInteractorTest {

    private RemoveParticipantInteractor removeEventInteractor;

    private CreateEventDataAccessInterface inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();

    private CreateEventDataAccessInterface inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();

    /*
    Initializes the variables above for testing.
     */
    @Before
    public void init(){
        UserFactory userFactory = new CommonUserFactory();
        EventFactory eventFactory = new CommonEventFactory();
        LocationFactory locationFactory = new CommonLocationFactory();

        LocalDateTime eventTime = LocalDateTime.parse("2016-03-04 11:30");

        //Creates a test location
        Location location = locationFactory.makeLocation("(0,0)");

        //Creates and adds a user to the in memory user DAO.
        User user = userFactory.create("Bob", "123", 20, "m", "Bob", "bob@gmail.com");
        InMemoryUsersDataAccessObject userDAO = (InMemoryUsersDataAccessObject)inMemoryUsersDataAccessObject;
        userDAO.save(user);

        //Creates and adds an event to the in memory event DAO.
        ArrayList<Integer> joinedUsers = new ArrayList<>();
        joinedUsers.add(user.get);
        Event event  = eventFactory.create(0, "Test Event", "Owner", location, joinedUsers, new ArrayList<>(),
                eventTime, "Event", "Testing", false, 5);
    }
    /*
    Integer eventID, String eventName, String owner, Location location,
                        ArrayList<Integer> peopleJoined, ArrayList<Integer> peopleWaitlisted, LocalDateTime time,
                        String type, String description, Boolean privacy, Integer capacity
     */
}