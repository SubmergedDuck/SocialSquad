package interface_adapter.get_current_user;

/**
 * The view state for the GetCurrentUser use case.
 */
public class GetCurrentUserState {
    private String username;

    public GetCurrentUserState(GetCurrentUserState copy){
        this.username = copy.username;
    }

    public GetCurrentUserState(){}

    /**
     * Gets the username of the logged in user
     * @return the logged in user's username
     */
    public String getUsername(){return this.username;}

    /**
     * Sets the state's username instance.
     * @param username the username that is being set
     */
    public void setUsername(String username){this.username = username;}
}
