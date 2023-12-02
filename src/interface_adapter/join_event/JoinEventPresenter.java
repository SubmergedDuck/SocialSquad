package interface_adapter.join_event;

import use_case.join_event.JoinEventOutputBoundary;
import use_case.join_event.JoinEventOutputData;

public class JoinEventPresenter implements JoinEventOutputBoundary {
    private final JoinEventViewModel joinEventViewModel;

    // No other view models are needed because join event doesn't work among multiple view models.

    public JoinEventPresenter(JoinEventViewModel joinEventViewModel) {
        this.joinEventViewModel = joinEventViewModel;
    }

    @Override
    public void prepareSuccessView() {
        JoinEventState joinEventState = joinEventViewModel.getState();
        joinEventState.setSuccess(true);
        joinEventViewModel.setState(joinEventState);
        joinEventViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
        JoinEventState joinEventState = joinEventViewModel.getState();
        //joinEventState.setError(outputData.getFailureReason());
        joinEventViewModel.setState(joinEventState);
        joinEventViewModel.firePropertyChanged();

    }
}
