package data_access;

import entity.Events.Event;
import entity.Users.User;
import use_case.create_event.CreateEventDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryUsersDataAccessObject implements
        CreateEventDataAccessInterface{

    private final HashMap<String, User> usernameToUser = new HashMap();

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
}
