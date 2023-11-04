package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.util.ArrayList;

public class CommonRestrictedEvent extends CommonEvent implements RestrictedEvent{

    private final Integer ageRestriction;
    private final String sexRestriction;

    public CommonRestrictedEvent(Integer eventID, String eventName, Integer owner, Location location,
                                 ArrayList<Integer> peopleJoined, ArrayList<Integer> peopleWaitlisted,
                                 String time, String type, String description, Boolean privacy,
                                 Integer capacity, Integer ageRestriction, String sexRestriction) {
        super(eventID, eventName, owner, location, peopleJoined, peopleWaitlisted, time, type,
                description, privacy, capacity);

        this.ageRestriction = ageRestriction;
        this.sexRestriction = sexRestriction;
    }


    @Override
    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    @Override
    public String getSexRestriction() {
        return sexRestriction;
    }
}
