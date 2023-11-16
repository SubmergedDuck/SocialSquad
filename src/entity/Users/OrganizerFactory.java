package entity.Users;

import entity.Events.Event;
import entity.Location.Location;

import java.util.ArrayList;

public interface OrganizerFactory {
    Organizer create(String username, String password, Event eventOf, ArrayList<Event> joinedEvents,
                ArrayList<Event> createdEvents, int age, String sex, String realName, String contact,
                Location location);
}
