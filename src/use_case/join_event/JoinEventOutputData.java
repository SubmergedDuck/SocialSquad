package use_case.join_event;

import java.util.ArrayList;
import interface_adapter.join_event.JoinEventState;

/**
 * Output data for the join event use case.
 */

public class JoinEventOutputData {
    private final ArrayList<String> getPeopleJoined;

    /**
     * Constructor for JoinEventOutputData.
     * @param getPeopleJoined the people joined
     */

    public JoinEventOutputData(ArrayList<String> getPeopleJoined) {
        this.getPeopleJoined = getPeopleJoined;
    }

// TODO:

//    public final boolean usecaseSuccess = false; // Output Data is only needed when the use case failed
//    private final String failureReason;
//
//    public JoinEventOutputData(String failureReason) {
//        this.failureReason = failureReason;
//    }
//
//    public String getFailureReason() {
//        return failureReason;
//    }

}
