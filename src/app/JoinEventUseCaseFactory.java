package app;

import entity.Events.EventFactory;
import entity.Location.LocationFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.back_out.BackOutController;
import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_direction.GetDirectionController;
import interface_adapter.get_direction.GetDirectionViewModel;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.join_event.JoinEventPresenter;
import interface_adapter.join_event.JoinEventViewModel;
import use_case.get_event_details.GetEventDetailsDataAccessInterface;
import use_case.get_event_details.GetEventDetailsInputBoundary;
import use_case.get_event_details.GetEventDetailsInteractor;
import use_case.get_event_details.GetEventDetailsOutputBoundary;
import use_case.join_event.*;
import view.EventDetailsView;


public class JoinEventUseCaseFactory {
    private JoinEventUseCaseFactory(){}

        public static EventDetailsView create(GetEventDetailsViewModel getEventDetailsViewModel, JoinEventController joinEventController,
                                              JoinEventViewModel joinEventViewModel, BackOutController backOutController,
                                              GetDirectionController getDirectionController,
                                              GetDirectionViewModel getDirectionViewModel,
                                              GetCurrentUserViewModel currentUserViewModel,
                                              GetCurrentUserController getCurrentUserController) {
            return new EventDetailsView(getEventDetailsViewModel, joinEventController, joinEventViewModel,backOutController, getDirectionController,
                    getDirectionViewModel, currentUserViewModel, getCurrentUserController);

        }


    public static JoinEventController joinEventUseCase(JoinEventViewModel joinEventViewModel,
                                                       JoinEventEventDataAccessInterface eventDAO,
                                                       JoinEventUserDataAccessInterface userDAO,
                                                       JoinEventCurrentUserDataAccessInterface currentUserDAO){
        JoinEventOutputBoundary presenter = new JoinEventPresenter(joinEventViewModel);
        JoinEventInputBoundary interactor = new JoinEventInteractor(presenter, userDAO, eventDAO, currentUserDAO);
        return new JoinEventController(interactor);
    }

    public static GetEventDetailsController createGetEventDetailsUseCase(GetEventDetailsViewModel getEventDetailsViewModel,
                                                                         ViewManagerModel viewManagerModel,
                                                                         GetEventDetailsDataAccessInterface eventDataAccessObject) {
        GetEventDetailsOutputBoundary presenter = new GetEventDetailsPresenter(getEventDetailsViewModel, viewManagerModel);
        GetEventDetailsInputBoundary interactor = new GetEventDetailsInteractor(presenter, eventDataAccessObject);
        return new GetEventDetailsController(interactor);


    }
}
