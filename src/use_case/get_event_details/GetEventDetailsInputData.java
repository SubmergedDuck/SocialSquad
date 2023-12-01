package use_case.get_event_details;


/**
 * Input data for the get event details use case.
 */
public class GetEventDetailsInputData {
    private final int eventID;

    /**
     * Constructor for GetEventDetailsInputData.
     * @param eventID the ID of the event that we are trying to get the details from
     */
    public GetEventDetailsInputData(int eventID){
        this.eventID = eventID;
    }

    public int getEventID(){return this.eventID;}
}