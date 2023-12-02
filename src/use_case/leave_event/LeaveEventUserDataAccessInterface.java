package use_case.leave_event;

import entity.Events.Event;

import java.util.ArrayList;

/**
 * The user data access interface for the leave event use case.
 */

public interface LeaveEventUserDataAccessInterface {
    /**
     * This method will use the user DAO to look up the user and remove the event from their joinedEvents list.
     */
    void userLeaveEvent(String username, Integer eventID);

    ArrayList<Event> getUserJoinedEvents(String username);
}

