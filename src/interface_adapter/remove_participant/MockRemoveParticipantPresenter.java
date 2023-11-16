package interface_adapter.remove_participant;

import use_case.remove_participant.RemoveParticipantOutputBoundary;
import use_case.remove_participant.RemoveParticipantOutputData;

public class MockRemoveParticipantPresenter implements RemoveParticipantOutputBoundary {
    @Override
    public void prepareView(RemoveParticipantOutputData outputData) {
        System.out.println("Removed " + outputData.getDeletedUser());
    }
}
