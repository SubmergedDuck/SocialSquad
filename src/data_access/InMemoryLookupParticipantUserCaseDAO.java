package data_access;

import entity.Users.User;
import use_case.look_up_participant_info.LookupPariticpantInfoDataAccessInterface;
import use_case.look_up_participant_info.LookupParticipantInfoInputData;
import use_case.search_event.SearchEventOutputData;

import java.util.HashMap;

public class InMemoryLookupParticipantUserCaseDAO implements LookupPariticpantInfoDataAccessInterface {
    /**
     * The inmemory data access object for the LookupParticipantInfo use case, implemented for testing.
     */
    HashMap<String, User> usernameToUser = new HashMap<>();

    /**
     * A public method called when saving a User into the participant directory of an event, usernameToUser.
     * @param participant The participant (User object) being saved.
     */
    public void save(User participant) {
        usernameToUser.put(participant.getUsername(), participant);
    }

    /**
     * A public method that retrieves the participant from the event participant directory, in the form of a User object.
     * @param participant The participant being looked up, in the form of a input data object.
     * @return The user object that contains all information of the participant.
     */
    @Override
    public User getParticipant(LookupParticipantInfoInputData participant) {
        return usernameToUser.get(participant.getParticipant().getUsername());
    }
}
