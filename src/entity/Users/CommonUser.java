package entity.Users;

import entity.Location.Location;
import entity.Events.Event;

import java.util.ArrayList;

public class CommonUser implements User {
    private final String username;
    private final String password;
    private final ArrayList<Event> joinedEvents;
    private final ArrayList<Event> createdEvents;
    private final Integer age;
    private final String sex;
    private final Integer userID;
    private final String contact;
    private final Location location;


    public CommonUser(String username, String password, ArrayList<Event> joinedEvents,
                      ArrayList<Event> createdEvents, Integer age, String sex, Integer userID, String contact,
                      Location location) {
        this.username = username;
        this.password = password;
        this.joinedEvents = joinedEvents;
        this.createdEvents = createdEvents;
        this.age = age;
        this.sex = sex;
        this.userID = userID;
        this.contact = contact;
        this.location = location;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public ArrayList<Event> getJoinedEvents() {
        return joinedEvents;
    }

    @Override
    public ArrayList<Event> getCreatedEvents() {
        return createdEvents;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getSex() {
        return sex;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public String getContact() {
        return contact;
    }

    @Override
    public Location getLocation() {
        return location;
    }
}
