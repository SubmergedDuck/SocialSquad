package app;

import entity.Events.EventFactory;
import entity.Location.LocationFactory;
import interface_adapter.back_out.BackOutController;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.create_event.CreateEventPresenter;
import interface_adapter.create_event.CreateEventViewModel;
import interface_adapter.generate_static_map.GenerateStaticMapController;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import use_case.create_event.*;
import view.CreateEventView;

public class CreateEventUseCaseFactory {
    private CreateEventUseCaseFactory(){}

    public static CreateEventView create(CreateEventViewModel createEventViewModel,
                                         CreateEventController createEventController,
                                         BackOutController backOutController,
                                         GetCurrentUserViewModel getCurrentUserViewModel, GenerateStaticMapController generateStaticMapController) {
        return new CreateEventView(createEventViewModel, createEventController, backOutController, getCurrentUserViewModel, generateStaticMapController);
    }

    public static CreateEventController createEventUseCase(CreateEventViewModel createEventViewModel,
                                                           CreateEventEventDataAccessInterface eventDAO,
                                                           CreateEventDataAccessInterface userDAO, EventFactory eventFactory,
                                                           LocationFactory locationFactory){
        CreateEventOutputBoundary presenter = new CreateEventPresenter(createEventViewModel);
        CreateEventInputBoundary interactor = new CreateEventInteractor(eventDAO, userDAO, presenter, eventFactory, locationFactory);
        return new CreateEventController(interactor);
    }
}
