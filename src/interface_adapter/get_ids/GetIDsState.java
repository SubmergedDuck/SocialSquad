package interface_adapter.get_ids;

import java.util.ArrayList;

/**
 * State of the GetIDs view.
 */
public class GetIDsState {
    private ArrayList<Integer> allIDs;
    private boolean isCreated;

    public GetIDsState(GetIDsState copy){
        this.allIDs = copy.allIDs;
    }

    public GetIDsState(){}

    /**
     * Gets the selected IDs
     * @return a list of ids
     */
    public ArrayList<Integer> getAllIDs(){return this.allIDs;}

    /**
     * Sets the list of IDs
     * @param allIDs the list of IDs that the state's instance will be set to.
     */
    public void setAllIDs(ArrayList<Integer> allIDs){this.allIDs = allIDs;}

    /**
     * Checks if the ID list consist of the user's created events or not
     * @return if the list of event ids are the user's created event.
     */
    public boolean getIsCreated(){return isCreated;}

    /**
     * Sets the isCreated instance
     * @param isCreated a boolean determining if the list of event ids are the user's created events.
     */
    public void setIsCreated(boolean isCreated){this.isCreated = isCreated;}
}
