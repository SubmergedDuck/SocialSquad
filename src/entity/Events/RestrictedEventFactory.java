package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.util.ArrayList;

public interface RestrictedEventFactory {

     RestrictedEvent create(Integer eventID, String eventName, Integer owner, Location location,
                                  ArrayList<Integer> peopleJoined, ArrayList<Integer> peopleWaitlisted, String time,
                                  String type, String description, Boolean privacy, Integer capacity,
                                  Integer ageRestriction, String sexRestriction);
}
