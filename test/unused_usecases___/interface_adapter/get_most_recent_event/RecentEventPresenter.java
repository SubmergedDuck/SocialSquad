package unused_usecases___.interface_adapter.get_most_recent_event;

import unused_usecases___.usecases.get_most_recent_event.RecentEventOutputBoundary;
import unused_usecases___.usecases.get_most_recent_event.RecentEventOutputData;

public class RecentEventPresenter implements RecentEventOutputBoundary {

    private final RecentEventViewModel recentEventViewModel;

    public RecentEventPresenter(RecentEventViewModel recentEventViewModel){
        this.recentEventViewModel = recentEventViewModel;
    }

    @Override
    public void prepareView(RecentEventOutputData outputData) {
        RecentEventState state = recentEventViewModel.getState();
        state.setEventID(outputData.getEventID());
        recentEventViewModel.setState(state);
        recentEventViewModel.firePropertyChanged();
    }
}
