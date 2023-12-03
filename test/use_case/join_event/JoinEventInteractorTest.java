package use_case.join_event;

import data_access.*;
import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUser;
import entity.Users.User;
import interface_adapter.join_event.JoinEventState;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.fail;

/**
 * Unit test for JoinEventInteractor.
 * What we want to test:
 * 1. The current user is added to the CSVFileEventDAO event's ArrayList<String> peopleJoined;
 * 2. The event is added to the CSVFileUserDAO user's ArrayList<Event> joinedEvents;
 * 3. The event is added to the current user's ArrayList<Event> joinedEvents;
 */


public class JoinEventInteractorTest {

    private JoinEventInteractor joinEventInteractor;
    InMemoryCurrentUserDAO inMemoryCurrentUserDAO = new InMemoryCurrentUserDAO();
    InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
    InMemoryEventsDataAccessObject inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();

    @Test
    public void testExecute() throws IOException {

        ArrayList<String> testPeopleJoined = new ArrayList<>();

        User testUser =  new CommonUser("Anna", "123", 20, "m", "test@gmail.com");
        Event testEvent = new CommonEvent(12345, "test event", "OrganizerBobTheBuilder",
                null, testPeopleJoined, null, null, "type", "description",
                false, 10);

        inMemoryEventsDataAccessObject.save(testEvent);
        inMemoryCurrentUserDAO.loginCurrentUser(testUser);
        inMemoryUsersDataAccessObject.save(testUser);


        JoinEventOutputBoundary joinEventPresenter = new JoinEventOutputBoundary() {
            @Override // Mock Presenter
            public void prepareSuccessView(JoinEventOutputData outputData) {
            }

            @Override // Mock Presenter
            public void prepareFailView(JoinEventOutputData outputData) {
            }

        };

        JoinEventInputData joinEventInputDataAnna = new JoinEventInputData(testEvent.getEventID(), "Anna");
        JoinEventUserDataAccessInterface joinEventUsersDataAccessObject = inMemoryUsersDataAccessObject;
        JoinEventEventDataAccessInterface joinEventEventsDataAccessObject = inMemoryEventsDataAccessObject;

        joinEventInteractor = new JoinEventInteractor(joinEventPresenter, inMemoryUsersDataAccessObject,
                inMemoryEventsDataAccessObject, inMemoryCurrentUserDAO);


        assert inMemoryEventsDataAccessObject.getPeopleJoined(12345).isEmpty();
        assert inMemoryUsersDataAccessObject.getUserJoinedEvents("Anna").isEmpty();


        assert inMemoryCurrentUserDAO.getCurrentUser().getJoinedEvents().isEmpty();

        joinEventInteractor.execute(joinEventInputDataAnna);
        inMemoryCurrentUserDAO.logoutCurrentUser();


        // Tests that the current user is added to the CSVFileEventDAO event's ArrayList<String> peopleJoined;
        assert !inMemoryEventsDataAccessObject.getPeopleJoined(12345).isEmpty();
        assert inMemoryEventsDataAccessObject.getPeopleJoined(12345).contains("Anna");


        // Tests that the event is added to the CSVFileUserDAO user's ArrayList<Event> joinedEvents;
        assert !inMemoryUsersDataAccessObject.getUserJoinedEvents("Anna").isEmpty();
        assert inMemoryUsersDataAccessObject.getUserJoinedEvents("Anna").contains(testEvent);


        // get the first event in the user's joinedEvents list
        Event evtInArray = inMemoryUsersDataAccessObject.getUserJoinedEvents("Anna").get(0);

    }
    @Test
    public void testHasJoinedEvent() {
        ArrayList<String> testPeopleJoined = new ArrayList<>();

        User testUser =  new CommonUser("Anna", "123", 20, "m", "test@gmail.com");
        Event testEvent = new CommonEvent(12345, "test event", "OrganizerBobTheBuilder",
                null, testPeopleJoined, null, null, "type", "description",
                false, 10);

        inMemoryEventsDataAccessObject.save(testEvent);
        inMemoryCurrentUserDAO.loginCurrentUser(testUser);
        inMemoryUsersDataAccessObject.save(testUser);
        testEvent.getPeopleJoined().add(testUser.getUsername()); // Make the user join the event


        JoinEventOutputBoundary joinEventPresenter = new JoinEventOutputBoundary() {
            @Override // Mock Presenter
            public void prepareSuccessView(JoinEventOutputData outputData) {
                fail("prepare success view is called");
            }

            @Override // Mock Presenter
            public void prepareFailView(JoinEventOutputData outputData) {
                String failReason = outputData.getFailureReason();
                assert (failReason.equals("Note: You have already joined this event!"));
            }

        };

        JoinEventInputData joinEventInputDataAnna = new JoinEventInputData(testEvent.getEventID(), testUser.getUsername());

        joinEventInteractor = new JoinEventInteractor(joinEventPresenter, inMemoryUsersDataAccessObject,
                inMemoryEventsDataAccessObject, inMemoryCurrentUserDAO);

        joinEventInteractor.execute(joinEventInputDataAnna);

    }

    @Test
    public void testEventFull() {
        ArrayList<String> testPeopleJoined = new ArrayList<>();

        User testUser =  new CommonUser("Anna", "123", 20, "m", "test@gmail.com");
        Event testEvent = new CommonEvent(12345, "test event", "OrganizerBobTheBuilder",
                null, testPeopleJoined, null, null, "type", "description",
                false, 1);

        inMemoryEventsDataAccessObject.save(testEvent);
        inMemoryCurrentUserDAO.loginCurrentUser(testUser);
        inMemoryUsersDataAccessObject.save(testUser);
        testEvent.getPeopleJoined().add(testUser.getUsername()); // Make the user join the event
        assert testEvent.getCapacity() == testEvent.getPeopleJoined().size(); // Event is full.


        JoinEventOutputBoundary joinEventPresenter = new JoinEventOutputBoundary() {
            @Override // Mock Presenter
            public void prepareSuccessView(JoinEventOutputData outputData) {
                fail("prepare success view is called");
            }

            @Override // Mock Presenter
            public void prepareFailView(JoinEventOutputData outputData) {
                String failReason = outputData.getFailureReason();
                assert (failReason.equals("Note: Event is at full capacity!"));
                assert outputData.getPeopleJoined().contains("Anna");
                assert !outputData.getPeopleJoined().contains("another user");
            }

        };

        JoinEventInputData inputData= new JoinEventInputData(testEvent.getEventID(), "another user");

        joinEventInteractor = new JoinEventInteractor(joinEventPresenter, inMemoryUsersDataAccessObject,
                inMemoryEventsDataAccessObject, inMemoryCurrentUserDAO);

        joinEventInteractor.execute(inputData);

    }
}
