package use_case.look_up_participant_info;

import entity.Users.User;

public interface LookupPariticpantInfoDataAccessInterface {

    public User getParticipant(LookupParticipantInfoInputData participant);
}
