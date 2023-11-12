package use_case.create_event;

import entity.Events.Event;

/**
 * The data access interface for the create event use case.
 */
public interface CreateEventDataAccessInterface {
    Integer generateEventID();
    void save(Event event);
}
