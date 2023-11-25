package use_case.join_event;

/**
 * The data access interface for the join event use case.
 */
public interface JoinEventEventDataAccessInterface {
    /**
    This method will use the event DAO to look up the event and add the given user in joined events list.
    */
    void userJoinEvent(String username, Integer eventID);
}
