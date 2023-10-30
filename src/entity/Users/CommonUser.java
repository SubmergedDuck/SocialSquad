package entity.Users;

import entity.Location.Location;
import entity.Events.Event;

import java.util.ArrayList;

public class CommonUser implements User {

    static int nextUserID = 1;

    private final int ID;
    private final String realName;
    private final String password;
    private ArrayList<Event> joinedEvents = new ArrayList<>();
    private ArrayList<Event> createdEvents = new ArrayList<>();
    private final Integer age;
    private final String sex;
    private final String contact;
    private final Location location = null;


    public CommonUser(String userRealName, String password, Integer age, String sex, String realName, String contact) {
        this.ID = nextUserID;
        this.realName = userRealName;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        nextUserID += 1; //get ready for the ID of the next user
    }


    @Override
    public String getUserRealName() {
        return this.realName;
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
        return this.ID;
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
