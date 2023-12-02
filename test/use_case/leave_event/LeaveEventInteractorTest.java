package use_case.leave_event;

import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEventFactory;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LeaveEventInteractorTest {
    @Test
    public void leaveEventTest() throws IOException {

        InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
        InMemoryEventsDataAccessObject inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();
        CommonUserFactory userFactory = new CommonUserFactory();
        CommonEventFactory eventFactory = new CommonEventFactory();
        CommonLocationFactory locationFactory = new CommonLocationFactory();
        User testUser = userFactory.create("testUser","123",20, "m","testContact");
        Location eventLocation = locationFactory.makeLocation("(43.651070,-79.347015)");
        ArrayList<String> joinedUsers = new ArrayList<>();
        joinedUsers.add(testUser.getUsername());
        Event event = eventFactory.create(1,"myEvent", "random owner",eventLocation,
                joinedUsers, new ArrayList<String>(), LocalDateTime.now(), "test event", "description",
                false, 20);
        ArrayList<Event> joinedEvents = testUser.getJoinedEvents();
        joinedEvents.add(event);
        inMemoryEventsDataAccessObject.save(event);
        inMemoryUsersDataAccessObject.save(testUser);
        assert(event.getPeopleJoined().contains(testUser.getUsername()));
        assert(testUser.getJoinedEvents().contains(event));
        LeaveEventOutputBoundary mockPresenter = new LeaveEventOutputBoundary() {
            @Override
            public void prepareSuccessView(LeaveEventOutputData outputData) {
                assert(!event.getPeopleJoined().contains(testUser.getUsername()));
                assert(!testUser.getJoinedEvents().contains(event));
            }
        };
        LeaveEventInteractor interactor = new LeaveEventInteractor(mockPresenter,inMemoryUsersDataAccessObject,inMemoryEventsDataAccessObject);
        LeaveEventInputData inputData = new LeaveEventInputData(event.getEventID(), testUser.getUsername());
        interactor.execute(inputData);
    }
}