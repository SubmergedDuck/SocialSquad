package use_case.get_ids;

import java.util.ArrayList;

/**
 * Output data for the GetIDs use case.
 */
public class GetIDsOutputData {
    private final ArrayList<Integer> eventIDs;

    private final boolean isCreated;

    /**
     * Constructor for GetIDsOutputData
     * @param eventIDs the selected ids
     */
    public GetIDsOutputData(ArrayList<Integer> eventIDs, boolean isCreated){
        this.eventIDs = eventIDs;
        this.isCreated = isCreated;
    }

    /**
     * All event IDs that we want.
     * @return a list of specific ids needed.
     */
    public ArrayList<Integer> getEventIDs(){return this.eventIDs;}

    public boolean isCreated(){return this.isCreated;}
}
