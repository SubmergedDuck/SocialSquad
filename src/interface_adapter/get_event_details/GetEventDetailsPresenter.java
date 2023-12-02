package interface_adapter.get_event_details;

import interface_adapter.ViewManagerModel;
import use_case.get_event_details.GetEventDetailsOutputBoundary;
import use_case.get_event_details.GetEventDetailsOutputData;

public class GetEventDetailsPresenter implements GetEventDetailsOutputBoundary {

    private final GetEventDetailsViewModel getEventDetailsViewModel;
    private ViewManagerModel viewManagerModel;

    public GetEventDetailsPresenter(GetEventDetailsViewModel getEventDetailsViewModel,
                                    ViewManagerModel viewManagerModel) {
        this.getEventDetailsViewModel = getEventDetailsViewModel;
        this.viewManagerModel = viewManagerModel;
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

        if (outputData.isChangeView()){
            viewManagerModel.setActiveView(getEventDetailsViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }

    }
}
