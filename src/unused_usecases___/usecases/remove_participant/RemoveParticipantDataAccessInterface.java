package unused_usecases___.usecases.remove_participant;

public interface RemoveParticipantDataAccessInterface {
    /*
    This method will use the user DAO to look up the user and remove the given event in their joined events list.
     */
    void removeUser(String username, Integer eventID);
}
