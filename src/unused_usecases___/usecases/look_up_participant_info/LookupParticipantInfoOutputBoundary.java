package unused_usecases___.usecases.look_up_participant_info;

public interface LookupParticipantInfoOutputBoundary {
    /** The interface for the LookupParticipantInfo use case, used by the presenter and interactor.
     */

    /**
     * A public method called after the interactor completes the use case.
     * @param outputData Carries username, age, sex, contact, and Location of the participant being looked up.
     */
    public void prepareSuccessView(LookupParticipantInfoOutputData outputData);

    /**
     * No prepareFailedView() is needed. This use case can't fail.
     */


}
