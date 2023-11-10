package use_case.remove_participant;

public interface RemoveParticipantOutputBoundary {

    /*
    Provides the RemoveParticipantState with the deleted user's username.
     */
    void prepareView(RemoveParticipantOutputData outputData);
}
