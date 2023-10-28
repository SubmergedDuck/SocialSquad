package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.util.ArrayList;

public class CommonRestrictedEvent extends CommonEvent implements RestrictedEvent{

    private final Integer AgeRestriction;
    private final String SexRestriction;

    public CommonRestrictedEvent(Integer eventID, String eventName, Organizer owner, Location location,
                                 ArrayList<Integer> peopleJoined, ArrayList<Integer> peopleWaitlisted,
                                 String time, String type, String description, Boolean privacy,
                                 Integer capacity, Integer ageRestriction, String sexRestriction) {
        super(eventID, eventName, owner, location, peopleJoined, peopleWaitlisted, time, type,
                description, privacy, capacity);

        this.AgeRestriction = ageRestriction;
        this.SexRestriction = sexRestriction;
    }


    @Override
    public Integer getAgeRestriction() {
        return AgeRestriction;
    }

    @Override
    public String getSexRestriction() {
        return SexRestriction;
    }
}
