package entity.Users;

import entity.Events.Event;
import entity.Location.Location;

import java.util.ArrayList;

public class CommonUserFactory implements UserFactory {


    @Override
    public User create(String username, String password, ArrayList<Event> joinedEvents,
                       ArrayList<Event> createdEvents, int age, String sex, int userid, String contact,
                       Location location) {
        return new CommonUser(username, password, joinedEvents, createdEvents, age, sex, userid,
                contact, location);
    }
}
