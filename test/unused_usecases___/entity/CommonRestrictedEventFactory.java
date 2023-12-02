package unused_usecases___.entity;

import entity.Location.Location;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonRestrictedEventFactory implements RestrictedEventFactory {
    public RestrictedEvent create(Integer eventID, String eventName, String owner, Location location,
                                  ArrayList<String> peopleJoined, ArrayList<String> peopleWaitlisted, LocalDateTime time,
                                  String type, String description, Boolean privacy, Integer capacity,
                                  Integer ageRestriction, String sexRestriction){
        return new CommonRestrictedEvent(eventID, eventName, owner, location, peopleJoined,
                peopleWaitlisted, time, type, description, privacy, capacity, ageRestriction, sexRestriction);


    }
}
