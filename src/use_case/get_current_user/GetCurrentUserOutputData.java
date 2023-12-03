package use_case.get_current_user;

/**
 * Output data for the GetCurrentUser use case.
 */
public class GetCurrentUserOutputData {
    private final String currentUser;

    private final String[] userCoordinates;

    /**
     * Constructor for GetCurrentUserOutputData
     * @param currentUser the username of the user logged in
     * @param userCoordinates the coordinates of the current user.
     */
    public GetCurrentUserOutputData(String currentUser, String[] userCoordinates){
        this.currentUser = currentUser;
        this.userCoordinates = userCoordinates;
    }

    /**
     * Provides the username of the logged in user.
     * @return the username of the logged in user.
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * Provides the coordinates of the logged in user
     * @return the coordinates of the logged in user
     */
    public String[] getUserCoordinates(){return userCoordinates;}
}
