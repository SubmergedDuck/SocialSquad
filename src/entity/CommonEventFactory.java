package entity;

import java.util.ArrayList;

public class CommonEventFactory {
    public Event create(Integer eventID, String eventName, Organizer owner, Location location,
                 ArrayList<Integer> peopleJoined, ArrayList<Integer> peopleWaitlisted, String time,
                 String type, String description, Boolean privacy, Integer capacity){
        return new CommonEvent(eventID, eventName, owner, location, peopleJoined,
                peopleWaitlisted, time, type, description, privacy, capacity);


    }
}
