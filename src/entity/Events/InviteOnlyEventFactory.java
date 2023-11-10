package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface InviteOnlyEventFactory {

    InviteOnlyEvent create(Integer eventID, String eventName, String owner, Location location,
                           ArrayList<String> peopleJoined, ArrayList<String> peopleWaitlisted, LocalDateTime time,
                           String type, String description, Boolean privacy, Integer capacity,
                           ArrayList<Integer> peopleInvited);
}
