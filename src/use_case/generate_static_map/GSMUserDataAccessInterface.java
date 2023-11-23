package use_case.generate_static_map;

/**
 * User data access interface for the generate map use case.
 */
public interface GSMUserDataAccessInterface {
    String[] getUserCoordinates(String user);
}
