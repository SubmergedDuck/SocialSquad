package data_access;

import entity.Events.Event;
import entity.Users.User;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.remove_participant.RemoveParticipantDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;
import use_case.search_event.SearchEventInputData;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryUsersDataAccessObject implements
        SearchEventDataAccessInterface,
        JoinEventDataAccessInterface, RemoveParticipantDataAccessInterface {

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
    public void save(User user){
        usernameToUser.put(user.getUsername(), user);
    }
    @Override
    public ArrayList<Event> getFullMatchEvents(SearchEventInputData inputData) {
        return null;
    }

    @Override
    public ArrayList<Event> getPartialMatchEvents(SearchEventInputData inputData) {
        return null;
    }
}

