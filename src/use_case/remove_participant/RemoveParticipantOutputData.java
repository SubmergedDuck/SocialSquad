package use_case.remove_participant;

public class RemoveParticipantOutputData {
    private final String deletedUser; //The username of the user that got removed from the event.

    /*
    Constructor for the instance attributes
     */
    public RemoveParticipantOutputData(String deletedUser){
        this.deletedUser = deletedUser;
    }

    public String getDeletedUser(){return this.deletedUser;}
}
