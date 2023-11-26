package entity.Events;

import entity.Location.Location;
import entity.Users.Organizer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonEvent implements Event {
    private final String owner;
    private final Integer eventID;
    private final String eventName;
    private final Location location;
    private final ArrayList<String> peopleJoined;
    private final ArrayList<String> peopleWaitlisted;
    private final LocalDateTime time;
    private final String type;
    private final String description;
    private final Boolean privacy;
    private final Integer capacity;

    public CommonEvent(Integer eventID, String eventName, String owner, Location location,
                 ArrayList<String> peopleJoined, ArrayList<String> peopleWaitlisted, LocalDateTime time,
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
    public String getOwnerUser() {return this.owner;}

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
    public ArrayList<String> getPeopleJoined() {
        return peopleJoined;
    }

    @Override
    public ArrayList<String> getPeopleWaitlisted() {
        return peopleWaitlisted;
    }

    @Override
    public LocalDateTime getTime() {
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

    @Override
    public String getEventAddress() {
        return location.getAddress();
    }

    @Override
    public String getEventDate() {
        String strTime = String.valueOf(time.getHour()) + ":" + String.valueOf(time.getMinute()) + " " +
                String.valueOf(time.getDayOfMonth()) + "/" + String.valueOf(time.getMonth()) + "/" +
                String.valueOf(time.getYear()) + " ";
        return strTime;
    }
}
