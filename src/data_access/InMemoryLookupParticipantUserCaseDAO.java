package data_access;

import entity.Users.User;
import use_case.look_up_participant_info.LookupPariticpantInfoDataAccessInterface;
import use_case.look_up_participant_info.LookupParticipantInfoInputData;
import use_case.search_event.SearchEventOutputData;

import java.util.HashMap;

public class InMemoryLookupParticipantUserCaseDAO implements LookupPariticpantInfoDataAccessInterface {
    HashMap<String, User> usernameToUser = new HashMap<>();

    public void save(User participant) {
        usernameToUser.put(participant.getUsername(), participant);
    }
    @Override
    public User getParticipant(LookupParticipantInfoInputData participant) {
        return usernameToUser.get(participant.getParticipant().getUsername());
    }
}
