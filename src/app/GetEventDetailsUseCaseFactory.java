package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewManagerModelAdapter;
import interface_adapter.back_out.BackOutController;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.join_event.JoinEventController;
import use_case.back_out.BackOutInteractor;
import use_case.get_event_details.*;
import use_case.join_event.JoinEventInteractor;
import view.EventDetailsView;
import view.SearchNearbyView;

public class GetEventDetailsUseCaseFactory {
    private GetEventDetailsUseCaseFactory(){}

//    public static EventDetailsView create(GetEventDetailsViewModel getEventDetailsViewModel, SearchNearbyView searchNearbyView,
//                                          JoinEventController joinEventController,
//                                          BackOutController backOutController) {
//        getEventDetailsViewModel.addPropertyChangeListener(searchNearbyView);
//        return new EventDetailsView(getEventDetailsViewModel, joinEventController, backOutController);
//    }

    public static GetEventDetailsController createGetEventDetailsUseCase(GetEventDetailsViewModel getEventDetailsViewModel,
                                                                         ViewManagerModel viewManagerModel,
                                                                         GetEventDetailsDataAccessInterface eventDataAccessObject) {
        GetEventDetailsOutputBoundary presenter = new GetEventDetailsPresenter(getEventDetailsViewModel, viewManagerModel);
        GetEventDetailsInputBoundary interactor = new GetEventDetailsInteractor(presenter, eventDataAccessObject);
        return new GetEventDetailsController(interactor);

    }
}
