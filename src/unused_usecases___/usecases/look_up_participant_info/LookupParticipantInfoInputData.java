package unused_usecases___.usecases.look_up_participant_info;

import entity.Users.User;

public class LookupParticipantInfoInputData {
    /**
     * The input data class for the LookupParticipantInfo use case.
     */
    private final User participant;

    /**
     * The constructor class for a LookupParticipantInfo object.
     * @param participant Represents the participant being looked up.
     */
    public LookupParticipantInfoInputData(User participant) {
        this.participant = participant;
    }

    /**
     * The public getter method for the participant being looked up.
     * @return The participant being looked up.
     */
    public User getParticipant() {
        return this.participant;
    }
}
