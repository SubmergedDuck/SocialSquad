package entity.Users;

import entity.Location.Location;
import entity.Events.Event;

import java.util.ArrayList;

public class CommonUser implements User {
    private final String username;
    private final String password;
    private ArrayList<Event> joinedEvents = new ArrayList<>();
    private ArrayList<Event> createdEvents = new ArrayList<>();
    private final Integer age;
    private final String sex;
    private final String realName;
    private final String contact;
    private final Location location = null;


    public CommonUser(String username, String password, Integer age, String sex, String realName, String contact) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.realName = realName;
        this.contact = contact;
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
    public String getRealName() {
        return realName;
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
