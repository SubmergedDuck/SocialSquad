package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.util.ArrayList;

public class CommonEvent implements Event {
    private final Organizer owner;
    private final Integer eventID;
    //TODO: should it be int or Integer?
    private final String eventName;
    private final Location location;
    private final ArrayList<Integer> peopleJoined;
    private final ArrayList<Integer> peopleWaitlisted;
    private final String time;
    private final String type;
    private final String description;
    private final Boolean privacy;
    private final Integer capacity;

    public CommonEvent(Integer eventID, String eventName, Organizer owner, Location location,
                 ArrayList<Integer> peopleJoined, ArrayList<Integer> peopleWaitlisted, String time,
                 String type, String description, Boolean privacy, Integer capacity) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.owner = owner;
        this.location = location;
        this.peopleJoined = peopleJoined;
        this.peopleWaitlisted = peopleWaitlisted;
        this.time = time;
        this.type = type;
        this.description = description;
        this.privacy = privacy;
        this.capacity = capacity;
    }


    @Override
    public Organizer getOwner() {
        return owner;
    }

    @Override
    public Integer getEventID() {
        return eventID;
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public ArrayList<Integer> getPeopleJoined() {
        return peopleJoined;
    }

    @Override
    public ArrayList<Integer> getPeopleWaitlisted() {
        return peopleWaitlisted;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Boolean getPrivacy() {
        return privacy;
    }

    @Override
    public Integer getCapacity() {
        return capacity;
    }
}
