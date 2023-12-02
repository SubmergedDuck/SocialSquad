package use_case.join_event;

import java.util.ArrayList;
import interface_adapter.join_event.JoinEventState;

/**
 * Output data for the join event use case.
 */

public class JoinEventOutputData {
    private final ArrayList<String> getPeopleJoined;
    private String failureReason;

    /**
     * Constructor for JoinEventOutputData.
     * @param getPeopleJoined the people joined
     */

    public JoinEventOutputData(ArrayList<String> getPeopleJoined, String errorMessage) {
        this.getPeopleJoined = getPeopleJoined;
        this.failureReason = errorMessage;
    }

    public ArrayList<String> getPeopleJoined() {
        return getPeopleJoined;
    }

    // If string is null, then there are no errors
    public String getFailureReason() {
        return failureReason;
    }

}
