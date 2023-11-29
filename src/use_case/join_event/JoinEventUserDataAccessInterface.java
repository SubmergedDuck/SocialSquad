package use_case.join_event;

import entity.Events.Event;

/**
 * The data access interface for the join event use case.
 */
public interface JoinEventUserDataAccessInterface {
    /**
    This method will use the user DAO to look up the user and add the given event in their joined events list.
    */
    void userJoinEvent(String username, Event event);

}
