package entity.Users;

import entity.Location.Location;
import entity.Events.Event;

import java.util.ArrayList;

public class CommonUser {
    private final String username;
    private final String password;
    private final ArrayList<Event> joinedEvents;
    private final ArrayList<Event> createdEvents;
    private final int age;
    private final String sex;
    private final int userid;
    private final String contact;
    private final Location location;


    public CommonUser(String username, String password, ArrayList<Event> joinedEvents,
                      ArrayList<Event> createdEvents, int age, String sex, int userid, String contact,
                      Location location) {
        this.username = username;
        this.password = password;
        this.joinedEvents = joinedEvents;
        this.createdEvents = createdEvents;
        this.age = age;
        this.sex = sex;
        this.userid = userid;
        this.contact = contact;
        this.location = location;
    }
}
