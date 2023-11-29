package use_case.join_event;

import entity.Events.Event;

/**
 * Input data for the join event use case.
 */
public class JoinEventInputData {
    private final Event event;
    private final String username;

    /**
     * Constructor for JoinEventInputData.
     * @param event the event that we are trying to have the user join
     * @param username the username of the user that we are trying to have join the event
     */
    public JoinEventInputData(Event event, String username){
        this.event = event;
        this.username = username;
    }

    /**
     * Getter for the event ID.
     * @return the event ID
     */
    Event getEvent(){return this.event;}

    /**
     * Getter for the username.
     * @return the username
     */
    String getUsername(){return this.username;}

}
