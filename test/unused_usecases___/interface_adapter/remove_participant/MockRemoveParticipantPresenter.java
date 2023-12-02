package unused_usecases___.interface_adapter.remove_participant;

import unused_usecases___.usecases.remove_participant.RemoveParticipantOutputBoundary;
import unused_usecases___.usecases.remove_participant.RemoveParticipantOutputData;

public class MockRemoveParticipantPresenter implements RemoveParticipantOutputBoundary {
    @Override
    public void prepareView(RemoveParticipantOutputData outputData) {
        System.out.println("Removed " + outputData.getDeletedUser());
    }
}
