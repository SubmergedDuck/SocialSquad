package use_case.leave_event;

import java.util.ArrayList;

/**
 * The data access interface for the leave event use case.
 */

public interface LeaveEventEventDataAccessInterface {
    /**
     * This method will use the event DAO to look up the event and remove the user from the peopleJoined list.
     */
    void userLeaveEvent(String username, Integer eventID);
}
