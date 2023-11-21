package use_case.get_direction;

/**
 * Event data access interface for GetDirection (used for getting an event's coordinates)
 */
public interface GetDirectionEventDataAccessInterface {
    String[] getEventCoordinates(int eventID);
}
