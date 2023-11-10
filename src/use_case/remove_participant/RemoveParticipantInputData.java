package use_case.remove_participant;

public class RemoveParticipantInputData {
    private final String deletedUser;

    private final Integer eventID;

    public RemoveParticipantInputData(String deletedUser, String eventID){
        this.deletedUser = deletedUser;
        this.eventID = Integer.valueOf(eventID);
    }

    String getDeletedUser(){return this.deletedUser;}

    Integer getEventID(){return this.eventID;}
}
