package unused_usecases___.usecases.remove_participant;

public class RemoveParticipantOutputData {
    private final String deletedUser; //The username of the user that got removed from the event.

    /**
     * Constructor for RemoveParticipantOutputData
     * @param deletedUser the username of the removed user from the event
     */
    public RemoveParticipantOutputData(String deletedUser){
        this.deletedUser = deletedUser;
    }

    /**
     * Returns the username of the removed participant
     * @return returns the username of the removed participant
     */
    public String getDeletedUser(){return this.deletedUser;}
}
