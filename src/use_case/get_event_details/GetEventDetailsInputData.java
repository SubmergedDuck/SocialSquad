package use_case.get_event_details;


/**
 * Input data for the get event details use case.
 */
public class GetEventDetailsInputData {
    private final int eventID;

    private final boolean changeView;

    /**
     * Constructor for GetEventDetailsInputData.
     * @param eventID the ID of the event that we are trying to get the details from
     */
    public GetEventDetailsInputData(int eventID, boolean changeView){
        this.eventID = eventID;
        this.changeView = changeView;
    }

    public int getEventID(){return this.eventID;}

    public boolean ischangeView(){return this.changeView;}
}