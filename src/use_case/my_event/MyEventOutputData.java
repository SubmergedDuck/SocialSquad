package use_case.my_event;

import entity.Events.Event;

import java.util.ArrayList;

public class MyEventOutputData {
    /**
     * The output data class for the MyEvent use case.
     */
    private final ArrayList<Event> joinedEvents;

    private final ArrayList<Event> createdEvents;
    public MyEventOutputData(ArrayList<Event> joinedEvents, ArrayList<Event> createdEvents){
        this.createdEvents = createdEvents;
        this.joinedEvents = joinedEvents;

    }
    public ArrayList<Event> getJoinedEvents(){ return joinedEvents;}
    public ArrayList<Event> getCreatedEvents(){ return createdEvents;}

}
