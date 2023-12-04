package entity.Users;

import entity.Location.Location;
import entity.Events.Event;

import java.util.ArrayList;

public class CommonUser implements User {
    private final String username;
    private final String password;
    private ArrayList<Event> joinedEvents;
    private ArrayList<Event> createdEvents;
    private final Integer age;
    private final String sex;
    private String contact;
    private Location location = null;


    public CommonUser(String username, String password, Integer age, String sex, String contact) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.createdEvents = new ArrayList<>();
        this.joinedEvents = new ArrayList<>(); //When instantiated, a user hasn't created or joined any events
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
    public String getContact() {
        return contact;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setCreatedEvents(ArrayList<Event> eventsCreated) {
        this.createdEvents = eventsCreated;
    }

    @Override
    public void setJoinedEvents(ArrayList<Event> eventsJoined) {
        this.joinedEvents = eventsJoined;

    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }
}
