package use_case.look_up_participant_info;

import entity.Location.Location;

public interface LookupParticipantInfoOutputBoundary {
    public void prepareSuccessView(LookupParticipantInfoOutputData outputData);

    /**
     * No prepareFailedView() is needed. This use case can't fail.
     */


}
