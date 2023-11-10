package use_case.remove_participant;

public class RemoveParticipantInputData {
    private final String deletedUser;

    private final Integer eventID;

    /**
     * Constructor for RemoveParticipantInputData
     * @param deletedUser the username of the removed user from the event
     * @param eventID the ID of the event that the user is being removed from
     */
    public RemoveParticipantInputData(String deletedUser, String eventID){
        this.deletedUser = deletedUser;
        this.eventID = Integer.valueOf(eventID);
    }

    String getDeletedUser(){return this.deletedUser;}

    Integer getEventID(){return this.eventID;}
}
