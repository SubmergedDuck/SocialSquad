package use_case.get_direction;

/**
 * Input data for the get direction use case
 */
public class GetDirectionInputData {
    private final int eventID;
    private final String username;
    public GetDirectionInputData(int eventID, String username){
        this.eventID = eventID;
        this.username = username;
    }
    int getEventID(){return this.eventID;}
    String getUsername(){return this.username;}
}
