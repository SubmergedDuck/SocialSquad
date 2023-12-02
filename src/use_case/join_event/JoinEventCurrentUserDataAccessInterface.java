package use_case.join_event;

import entity.Users.User;

/**
 * In memory current user data access interface for the join event use case.
 */
public interface JoinEventCurrentUserDataAccessInterface {
    /**
     * This method will use the current user DAO to look up the current user and add the given event
     * in their joined events list.
     */
    void currentUserJoinEvent(Integer eventID);

}
