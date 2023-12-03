package use_case.get_ids;

import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEventFactory;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.get_ids.GetIDsPresenter;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GetIDsInteractorTest {

    @Test
    public void getJoinedEvents() throws IOException {
        InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        CommonEventFactory commonEventFactory = new CommonEventFactory();
        CommonLocationFactory locationFactory = new CommonLocationFactory();
        Location eventLocation = locationFactory.makeLocation("(43.651070,-79.347015)");
        User user = commonUserFactory.create("testUser","123",20,"f","testcontact");

        ArrayList<String> joinedUsers = new ArrayList<>();
        joinedUsers.add(user.getUsername());
        Event event = commonEventFactory.create(1,"myEvent", "random owner",eventLocation,
                joinedUsers, new ArrayList<String>(), LocalDateTime.now(), "test event", "description",
                false, 20);
        ArrayList<Event> joinedEvents = user.getJoinedEvents();
        ArrayList<Event> createdEvents = user.getCreatedEvents();
        joinedEvents.add(event);
        ArrayList<Integer> joinedIDs = new ArrayList<>();
        ArrayList<Integer> createdIDs = new ArrayList<>();
        for (Event joinedEvent : joinedEvents){
            joinedIDs.add(joinedEvent.getEventID());
        }
        for (Event createdEvent : createdEvents){
            createdIDs.add(createdEvent.getEventID());
        }
        inMemoryUsersDataAccessObject.save(user);
        GetIDsOutputBoundary mockPresenter = new GetIDsOutputBoundary() {
            @Override
            public void prepareView(GetIDsOutputData outputData) {
                assertEquals(false, outputData.isCreated());
                for (int eventID : outputData.getEventIDs()){
                    if (!joinedIDs.contains(eventID)){
                        fail();
                    }
                }
            }
        };
        GetIDsOutputBoundary mockPresenter2 = new GetIDsOutputBoundary() {
            @Override
            public void prepareView(GetIDsOutputData outputData) {
                assertEquals(true,outputData.isCreated());
                for (int eventID : outputData.getEventIDs()){
                    if (!createdEvents.contains(eventID)){
                        fail();
                    }
                }
            }
        };
        GetIDsInteractor getIDsInteractor = new GetIDsInteractor(inMemoryUsersDataAccessObject,mockPresenter);
        GetIDsInteractor getIDsInteractor2 = new GetIDsInteractor(inMemoryUsersDataAccessObject,mockPresenter2);
        GetIDsInputData inputData = new GetIDsInputData(user.getUsername(), false);
        GetIDsInputData inputData2 = new GetIDsInputData(user.getUsername(), true);
        getIDsInteractor.execute(inputData);
        getIDsInteractor2.execute(inputData2);
    }
}