package interface_adapter.get_event_details;

import interface_adapter.ViewManagerModel;
import use_case.get_event_details.GetEventDetailsOutputBoundary;
import use_case.get_event_details.GetEventDetailsOutputData;

public class OnlyGetEventDetailsPresenter implements GetEventDetailsOutputBoundary {

    private final GetEventDetailsViewModel getEventDetailsViewModel;

    public OnlyGetEventDetailsPresenter(GetEventDetailsViewModel getEventDetailsViewModel) {
        this.getEventDetailsViewModel = getEventDetailsViewModel;
    }
    @Override
    public void prepareView(GetEventDetailsOutputData outputData) {
        GetEventDetailsState state = getEventDetailsViewModel.getState();
        state.setOwnerUser(outputData.getOwnerUser());
        state.setEventName(outputData.getEventName());
        state.setEventAddress(outputData.getEventAddress());
        state.setEventDate(outputData.getDate());
        state.setEventDescription(outputData.getDescription());
        state.setEventCapacity(outputData.getCapacity());
        state.setEventID(outputData.getEventID());
        this.getEventDetailsViewModel.setState(state);
        getEventDetailsViewModel.firePropertyChanged();

    }
}
