package use_case.get_direction;

/**
 * User data access interface for GetDirection (used for getting the coordinates of a specified user)
 */
public interface GetDirectionUserDataAccessInterface {
    String[] getCoordinates(String user);
}
