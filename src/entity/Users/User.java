package entity.Users;

import entity.Events.Event;
import entity.Location.Location;

import java.util.ArrayList;

public interface User {
    String getUsername();
    String getPassword();
    ArrayList<Event> getJoinedEvents();
    ArrayList<Event> getCreatedEvents();
    int getAge();
    String getSex();
    String getRealName();

    String getContact();
    Location getLocation();
}
