package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonInviteOnlyEventFactory implements InviteOnlyEventFactory {
    public InviteOnlyEvent create(Integer eventID, String eventName, String owner, Location location,
                        ArrayList<Integer> peopleJoined, ArrayList<Integer> peopleWaitlisted, LocalDateTime time,
                        String type, String description, Boolean privacy, Integer capacity,
                        ArrayList<Integer> peopleInvited){
        return new CommonInviteOnlyEvent(eventID, eventName, owner, location, peopleJoined,
                peopleWaitlisted, time, type, description, privacy, capacity, peopleInvited);


    }
}
