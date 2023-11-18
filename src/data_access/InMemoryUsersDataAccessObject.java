package data_access;

import entity.Events.Event;
import entity.Users.User;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.remove_participant.RemoveParticipantDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryUsersDataAccessObject implements GetDirectionDataAccessInterface,
        CreateEventDataAccessInterface,
        SearchEventDataAccessInterface,
        JoinEventDataAccessInterface, RemoveParticipantDataAccessInterface {

    private final HashMap<String, User> usernameToUser = new HashMap();

    @Override
    public void removeUser(String username, Integer eventID) {
        User deletedUser = usernameToUser.get(username);
        ArrayList<Event> joinedEvents = deletedUser.getJoinedEvents();

        Event eventRemove = null;
        for (Event event : joinedEvents){
            if (event.getEventID() == eventID){
                eventRemove = event;
            }
        }
        joinedEvents.remove(eventRemove);
    }

    @Override
    public Integer generateEventID() {
        return null;
    }

    public void save(User user){
        usernameToUser.put(user.getUsername(), user);
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
    public void removeUser(String username, Integer eventID) {
        User deletedUser = usernameToUser.get(username);
        ArrayList<Event> joinedEvents = deletedUser.getJoinedEvents();

        Event eventRemove = null;
        for (Event event : joinedEvents){
            if (event.getEventID() == eventID){
                eventRemove = event;
            }
        }
        joinedEvents.remove(eventRemove);
    }
}
