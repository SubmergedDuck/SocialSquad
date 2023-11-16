package entity.Users;

import entity.Events.Event;
import entity.Location.Location;

import java.util.ArrayList;

public class CommonOrganizerFactory implements OrganizerFactory {
    @Override
    public Organizer create(String username, String password, Event eventOf, ArrayList<Event> joinedEvents, ArrayList<Event> createdEvents, int age, String sex, int userid, String contact, Location location) {
        return new CommonOrganizer(username, password, eventOf, age, sex, contact);
    }
}
