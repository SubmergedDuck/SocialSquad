package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonInviteOnlyEvent extends CommonEvent implements InviteOnlyEvent {
    private final ArrayList<Integer> peopleInvited;


    public CommonInviteOnlyEvent(Integer eventID, String eventName, String owner, Location location,
                                 ArrayList<String> peopleJoined, ArrayList<String> peopleWaitlisted,
                                 LocalDateTime time, String type, String description, Boolean privacy,
                                 Integer capacity, ArrayList<Integer> peopleInvited) {
        super(eventID, eventName, owner, location, peopleJoined, peopleWaitlisted, time, type, description, privacy, capacity);

        this.peopleInvited = peopleInvited;
    }

    @Override
    public ArrayList<Integer> getPeopleInvited() {
        return peopleInvited;
    }
}
