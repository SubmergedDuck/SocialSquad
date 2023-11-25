package use_case.leave_event;

/**
 * Input data for the leave event use case.
 */

public class LeaveEventInputData {

    private final int eventID;
    private final String username;

    /**
     * Constructor for LeaveEventInputData.
     * @param eventID the ID of the event that we are trying to have the user join
     * @param username the username of the user that we are trying to have join the event
     */
    public LeaveEventInputData(int eventID, String username){
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

