package use_case.get_current_user;

/**
 * Output data for the GetCurrentUser use case.
 */
public class GetCurrentUserOutputData {
    private final String currentUser;

    /**
     * Constructor for GetCurrentUserOutputData
     * @param currentUser the username of the user logged in
     */
    public GetCurrentUserOutputData(String currentUser){
        this.currentUser = currentUser;
    }

    /**
     * Provides the username of the logged in user.
     * @return the username of the logged in user.
     */
    public String getCurrentUser() {
        return currentUser;
    }
}
