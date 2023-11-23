package use_case.generate_static_map;

import entity.Events.Event;

import java.util.HashMap;

/**
 * Event data access interface for the generate static map use case.
 */
public interface GSMEventDataAccessInterface {
    HashMap<Integer, Event> getEvents(int amount);
}
