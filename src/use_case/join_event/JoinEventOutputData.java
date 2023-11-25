package use_case.join_event;

import java.util.ArrayList;

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
}
