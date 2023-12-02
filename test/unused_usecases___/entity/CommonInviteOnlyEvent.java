package unused_usecases___.entity;

import entity.Events.CommonEvent;
import entity.Location.Location;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonInviteOnlyEvent extends CommonEvent implements InviteOnlyEvent {
    private final ArrayList<String> peopleInvited;


    public CommonInviteOnlyEvent(Integer eventID, String eventName, String owner, Location location,
                                 ArrayList<String> peopleJoined, ArrayList<String> peopleWaitlisted,
                                 LocalDateTime time, String type, String description, Boolean privacy,
                                 Integer capacity, ArrayList<String> peopleInvited) {
        super(eventID, eventName, owner, location, peopleJoined, peopleWaitlisted, time, type, description, privacy, capacity);

        this.peopleInvited = peopleInvited;
    }

    @Override
    public ArrayList<String> getPeopleInvited() {
        return peopleInvited;
    }
}
