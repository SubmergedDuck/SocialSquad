package use_case.remove_participant;

import entity.Events.Event;
import entity.Users.User;

public interface RemoveParticipantDataAccessInterface {
    /*
    This method will use the user DAO to look up the user and remove the given event in their joined events list.
     */
    void removeUser(String username, Integer eventID);
}
