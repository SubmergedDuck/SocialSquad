package use_case.remove_participant;

public interface RemoveParticipantInputBoundary {
    /*
    Input data given to the interactor, which would be the user and the event that they are being removed from.
     */
    void execute(RemoveParticipantInputData inputData);
}
