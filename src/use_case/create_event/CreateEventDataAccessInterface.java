package use_case.create_event;

import entity.Events.Event;

public interface CreateEventDataAccessInterface {
    Integer generateEventID();
    void save(Event event);
}
