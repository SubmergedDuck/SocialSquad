package interface_adapter.get_ids;

import java.util.ArrayList;

public class GetIDsState {
    private ArrayList<Integer> allIDs;

    public GetIDsState(GetIDsState copy){
        this.allIDs = copy.allIDs;
    }

    public GetIDsState(){}

    public ArrayList<Integer> getAllIDs(){return this.allIDs;}

    public void setAllIDs(ArrayList<Integer> allIDs){this.allIDs = allIDs;}
}
