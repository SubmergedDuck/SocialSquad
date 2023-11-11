package data_access;

import entity.Events.Event;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;
import java.util.HashMap;
import java.util.Map;

public class InMemoryEventsDataAccessObject implements GetDirectionDataAccessInterface,
        CreateEventDataAccessInterface,
        SearchEventDataAccessInterface,
        JoinEventDataAccessInterface {
    /**
     * This is an in-memory event DAO to allow testing with the relevant interactors.
     * @param
     * @return
     */
    private final Map<Integer, Event> EventstoID = new HashMap<>();
    //NOTE: Switched the key to being the event ID since that's how we identify events.

    @Override
    public Integer generateEventID() {
        Integer currentID = 0;
        for (Integer eventID : EventstoID.keySet()){
            //The new eventID will be the highest event ID.
            if (currentID < eventID){
                currentID = eventID + 1;
            }
        }
        return currentID;
    }

    public Map<Integer,Event> getEventMap(){
        return this.EventstoID;
    }

    public void save(Event event){
        EventstoID.put(event.getEventID(), event);
    }
}