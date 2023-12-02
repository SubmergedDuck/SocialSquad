package use_case.join_event;

import entity.Events.Event;

/**
 * Input data for the join event use case.
 */
public class JoinEventInputData {
    private final int eventID;
    private final String username;

    /**
     * Constructor for JoinEventInputData.
     * @param eventID the ID to the event that we are trying to have the user join
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
    public int getEventID(){return this.eventID;}

    /**
     * Getter for the username.
     * @return the username
     */
    public String getUsername(){return this.username;}

}
