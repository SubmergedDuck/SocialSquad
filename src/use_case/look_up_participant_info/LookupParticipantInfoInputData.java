package use_case.look_up_participant_info;

import entity.Users.User;

public class LookupParticipantInfoInputData {
    private final User participant;

    public LookupParticipantInfoInputData(User participant) {
        this.participant = participant;
    }

    public User getParticipant() {
        return this.participant;
    }
}
