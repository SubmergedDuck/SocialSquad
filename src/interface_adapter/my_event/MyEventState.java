package interface_adapter.my_event;

import com.sun.java.accessibility.util.EventID;
import entity.Events.Event;
import entity.Users.User;

import java.util.ArrayList;

public class MyEventState {
    private ArrayList<Event> joinedEvent = null;

    private ArrayList<Event> createdEvent = null;

    private User user = null;

    private String eventName = "";

    private String location = "";

    public MyEventState(MyEventState copy){
        joinedEvent = copy.joinedEvent;
        createdEvent = copy.createdEvent;
        eventName = copy.eventName;
        location = copy.location;
        user = copy.user;
    }
    public MyEventState(){}

    public User getUser(){return user;}

    public ArrayList<Event> getJoinedEvent(){
        return joinedEvent;
    }
    public void setJoinedEvent(ArrayList<Event> joinedEvent){
       this.joinedEvent =joinedEvent;

    }
    public ArrayList<Event> getCreatedEvent(){
        return createdEvent;
    }
    public void setCreatedEvent(ArrayList<Event> createdEvent){
        this.createdEvent = createdEvent;

    }

}

