package data_access;

import entity.Events.Event;
import entity.Users.User;
import use_case.common_interfaces.MapUserDataAccessInterface;
import use_case.get_ids.GetIDsDataAccessInterface;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.loggedIn.LoggedInUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.join_event.JoinEventUserDataAccessInterface;
import use_case.remove_participant.RemoveParticipantDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;
import use_case.search_event.SearchEventInputData;
import use_case.signup.SignupUserDataAccessInterface;
import data_access.InMemoryEventsDataAccessObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InMemoryUsersDataAccessObject implements
        RemoveParticipantDataAccessInterface, SignupUserDataAccessInterface,
        CreateEventDataAccessInterface, MapUserDataAccessInterface,LoggedInUserDataAccessInterface,
        LoginUserDataAccessInterface, GetIDsDataAccessInterface, JoinEventUserDataAccessInterface {

    private final HashMap<String, User> usernameToUser = new HashMap();


    @Override
    public void removeUser(String username, Integer eventID) {
        User deletedUser = usernameToUser.get(username);
        ArrayList<Event> joinedEvents = deletedUser.getJoinedEvents();

        Event eventRemove = null;
        for (Event event : joinedEvents) {
            if (event.getEventID() == eventID) {
                eventRemove = event;
            }
        }
        joinedEvents.remove(eventRemove);
    }

    @Override
    public boolean existsByName(String identifier) {
        return usernameToUser.containsKey(identifier);
    }
    //TODO: should be return usernameToUser.containsKey(identifier)?

    public void save(User user){
        usernameToUser.put(user.getUsername(), user);
    }

    @Override
    public User get(String username) {
        return usernameToUser.get(username);
    }

    @Override
    public String[] getCoordinates(String user) {
        User selectedUser = usernameToUser.get(user);
        return selectedUser.getLocation().getCoordinates();
    }

    public User getUser(String username){
        return usernameToUser.get(username);
    }

    @Override
    public void save(Event event) {
        String ownerUser = event.getOwnerUser();
        User eventOwner = this.usernameToUser.get(ownerUser);
        ArrayList<Event> hostedEvents = eventOwner.getCreatedEvents();
        hostedEvents.add(event);
    }

    @Override
    public ArrayList<Integer> getIds(String username, boolean isCreatedEvent) {
        ArrayList<Integer> currentIDs = new ArrayList<>();
        User user = usernameToUser.get(username);
        if (isCreatedEvent){
            for (Event event : user.getCreatedEvents()){
                currentIDs.add(event.getEventID());
            }
        } else {
            for (Event event : user.getJoinedEvents()){
                currentIDs.add(event.getEventID());
            }
        }
        return currentIDs;
    }

    @Override
    public void userJoinEvent(String username, Event event) {
        User user = usernameToUser.get(username);
        user.getJoinedEvents().add(event);
    }


    public ArrayList<Event> getUserJoinedEvents(String username) {
        User user = usernameToUser.get(username);
        return user.getJoinedEvents();
    }

    public HashMap<String, User> getUsernameToUser() {
        return usernameToUser;
    }
}

