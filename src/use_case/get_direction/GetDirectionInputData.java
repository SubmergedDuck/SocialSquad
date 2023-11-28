package use_case.get_direction;

/**
 * Input data for the get direction use case
 */
public class GetDirectionInputData {
    private final int eventID;
    private final String username;
    private final int width;
    private final int height;

    /**
     * Constructor for GetDirectionInputData
     * @param eventID ID of the event we want the coordinates of
     * @param username username of the user we want the coordinates of
     */
    public GetDirectionInputData(int eventID, String username, int width, int height){
        this.eventID = eventID;
        this.username = username;
        this.width = width;
        this.height = height;
    }
    int getEventID(){return this.eventID;}
    String getUsername(){return this.username;}
    int getWidth(){return this.width;}
    int getHeight(){return this.height;}
}
