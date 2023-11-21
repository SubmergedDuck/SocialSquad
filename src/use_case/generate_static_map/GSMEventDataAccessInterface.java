package use_case.generate_static_map;

import entity.Events.Event;

import java.util.HashMap;

public interface GSMEventDataAccessInterface {
    HashMap<Integer, Event> getEvents(int amount);
}
