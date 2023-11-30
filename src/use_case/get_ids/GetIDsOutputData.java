package use_case.get_ids;

import java.util.ArrayList;

/**
 * Output data for the GetIDs use case.
 */
public class GetIDsOutputData {
    ArrayList<Integer> eventIDs;

    /**
     * Constructor for GetIDsOutputData
     * @param eventIDs the selected ids
     */
    public GetIDsOutputData(ArrayList<Integer> eventIDs){
        this.eventIDs = eventIDs;
    }

    /**
     * All event IDs that we want.
     * @return a list of specific ids needed.
     */
    public ArrayList<Integer> getEventIDs(){return this.eventIDs;}
}
