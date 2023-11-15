package use_case.look_up_participant_info;

import entity.Users.User;

public interface LookupPariticpantInfoDataAccessInterface {
    /**
     * The data access interface for the LookupParticipantInfo use case.
     */

    /**
     * A public method that asks the DAO to retrieve the participant from the User database.
     * @param participant The participant being looked up, in the form of a input data object.
     * @return A User object that stores information of this participant.
     */

    public User getParticipant(LookupParticipantInfoInputData participant);
}
