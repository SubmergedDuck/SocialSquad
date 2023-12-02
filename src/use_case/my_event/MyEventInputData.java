package use_case.my_event;

import entity.Events.Event;

import java.util.ArrayList;

public class MyEventInputData {
    private final String username;

    private final ArrayList<Event> joinedEvent;
    private final ArrayList<Event> createdEvent;

    public MyEventInputData(String username, ArrayList<Event> joinedEvent, ArrayList<Event> createdEvent) {
        this.username = username;
        this.joinedEvent = joinedEvent;
        this.createdEvent = createdEvent;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Event> getCreatedEvent() {
        return createdEvent;
    }

    public ArrayList<Event> getJoinedEvent() {
        return joinedEvent;
    }
}
