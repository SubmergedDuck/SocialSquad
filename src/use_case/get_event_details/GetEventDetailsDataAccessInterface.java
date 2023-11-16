package use_case.get_event_details;

import entity.Events.Event;

import java.util.Map;

/**
 * Data access interface for the get event details use case.
 */
public interface GetEventDetailsDataAccessInterface {
    Event getEvent(int eventID);
}
