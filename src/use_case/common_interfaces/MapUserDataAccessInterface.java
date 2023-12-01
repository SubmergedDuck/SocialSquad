package use_case.common_interfaces;

/**
 * User data access interface for use cases that deal with maps.
 */
public interface MapUserDataAccessInterface {
    String[] getCoordinates(String user);
}
