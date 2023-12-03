package interface_adapter.get_current_user;

/**
 * The view state for the GetCurrentUser use case.
 */
public class GetCurrentUserState {
    private String username;

    private String[] userCoordinates;

    public GetCurrentUserState(GetCurrentUserState copy){
        this.username = copy.username;
        this.userCoordinates = copy.userCoordinates;
    }

    public GetCurrentUserState(){}

    /**
     * Gets the username of the logged in user
     * @return the logged in user's username
     */
    public String getUsername(){return this.username;}

    /**
     * Gets the coordinates of the logged in user
     * @return the coordinates of the logged in user
     */
    public String[] getUserCoordinates(){return this.userCoordinates;}

    /**
     * Sets the state's username instance.
     * @param username the username that is being set
     */
    public void setUsername(String username){this.username = username;}

    /**
     * Saves the user coordinates
     * @param userCoordinates the current user's coordinates
     */
    public void setUserCoordinates(String[] userCoordinates){this.userCoordinates = userCoordinates;}
}
