package entity;

import java.util.ArrayList;

public interface EventFactory {

    Event create(Integer eventID, String eventName, Organizer owner, Location location,
                 ArrayList<Integer> peopleJoined, ArrayList<Integer> peopleWaitlisted, String time,
                 String type, String description, Boolean privacy, Integer capacity);
}
