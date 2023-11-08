package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Event {
    String getOwnerUser();
    Integer getEventID();
    String getEventName();
    Location getLocation();
    ArrayList<Integer> getPeopleJoined();
    ArrayList<Integer> getPeopleWaitlisted();
    LocalDateTime getTime();
    String getType();
    String getDescription();
    Boolean getPrivacy();
    Integer getCapacity();

}
