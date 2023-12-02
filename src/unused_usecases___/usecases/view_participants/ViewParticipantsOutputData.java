package unused_usecases___.usecases.view_participants;

import java.util.ArrayList;

/**
 * Output data for the view participants use case.
 */
public class ViewParticipantsOutputData {
    private final String joinedParticipants;

    /**
     * Constructor for ViewParticipantsOutputData
     * @param participants the participants of the event
     */
    public ViewParticipantsOutputData(ArrayList<String> participants){
        String participantsSoFar = "";
        for (int i = 0; i < participants.size(); i++){
            if (i == participants.size() - 1){
                participantsSoFar = participantsSoFar + participants.get(i);
            } else {
                participantsSoFar = participantsSoFar + participants.get(i) + ",";
            }
        }
        joinedParticipants = participantsSoFar;
    }

    String getJoinedParticipants(){return this.joinedParticipants;}
}
