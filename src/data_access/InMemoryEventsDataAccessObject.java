package data_access;

import entity.Events.Event;

import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryEventsDataAccessObject implements GetDirectionDataAccessInterface,
        SearchEventDataAccessInterface,
        JoinEventDataAccessInterface{

    private final Map<Integer, Event> EventstoID = new HashMap<>();
    //NOTE: Switched the key to being the event ID since that's how we identify events.


    public Map<Integer,Event> getEventMap(){
        return this.EventstoID;
    }

    private boolean withinRange(Event event, LocalDateTime start, LocalDateTime end) {
        return start.isAfter(event.getTime()) && end.isBefore(event.getTime());
    }
}