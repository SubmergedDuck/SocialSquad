package unused_usecases___.usecases.get_most_recent_event;

/**
 * Output data for the get recent event use case.
 */
public class RecentEventOutputData {
    private final int eventID;

    /**
     * Constructor for RecentEventOutputData
     * @param eventID the event ID of the most recently selected event.
     */
    public RecentEventOutputData(int eventID){
        this.eventID = eventID;
    }

    /**
     * Returns the event ID of the most recently selected event.
     * @return the ID of the most recently selected event.
     */
    public int getEventID(){return this.eventID;}
}
