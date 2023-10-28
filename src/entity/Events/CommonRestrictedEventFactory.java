package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.util.ArrayList;

public class CommonRestrictedEventFactory implements RestrictedEventFactory {
    public RestrictedEvent create(Integer eventID, String eventName, Organizer owner, Location location,
                                  ArrayList<Integer> peopleJoined, ArrayList<Integer> peopleWaitlisted, String time,
                                  String type, String description, Boolean privacy, Integer capacity,
                                  Integer ageRestriction, String sexRestriction){
        return new CommonRestrictedEvent(eventID, eventName, owner, location, peopleJoined,
                peopleWaitlisted, time, type, description, privacy, capacity, ageRestriction, sexRestriction);


    }
}
