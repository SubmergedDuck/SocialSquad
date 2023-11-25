package use_case.leave_event;

import java.util.ArrayList;

/**
 * Output data for the leave event use case.
 */
public class LeaveEventOutputData {

    // We're outputting this to use to get the number of people joined
    private final ArrayList<String> getPeopleJoined;

    /**
     * Constructor for JoinEventOutputData.
     * @param getPeopleJoined the people joined
     */

    public LeaveEventOutputData(ArrayList<String> getPeopleJoined) {
            this.getPeopleJoined = getPeopleJoined;
    }
}





