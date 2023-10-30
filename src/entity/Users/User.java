package entity.Users;

import entity.Events.Event;
import entity.Location.Location;

import java.util.ArrayList;

public interface User {
    String getUserRealName();
    String getPassword();
    ArrayList<Event> getJoinedEvents();
    ArrayList<Event> getCreatedEvents();
    int getAge();
    String getSex();
    int getUserID();
    String getContact();
    Location getLocation();
}
