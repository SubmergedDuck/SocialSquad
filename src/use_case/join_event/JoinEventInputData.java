package use_case.join_event;

/**
 * Input data for the join event use case.
 */
public class JoinEventInputData {
    private final int eventID;
    private final String username;

    /**
     * Constructor for JoinEventInputData.
     * @param eventID the ID of the event that we are trying to have the user join
     * @param username the username of the user that we are trying to have join the event
     */
    public JoinEventInputData(int eventID, String username){
        this.eventID = eventID;
        this.username = username;
    }

    /**
     * Getter for the event ID.
     * @return the event ID
     */
    int getEventID(){return this.eventID;}

    /**
     * Getter for the username.
     * @return the username
     */
    String getUsername(){return this.username;}

}
