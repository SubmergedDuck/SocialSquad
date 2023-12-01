package app;

import entity.Events.EventFactory;
import entity.Events.InviteOnlyEventFactory;
import entity.Events.RestrictedEventFactory;
import entity.Location.LocationFactory;
import interface_adapter.back_out.BackOutController;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.create_event.CreateEventPresenter;
import interface_adapter.create_event.CreateEventViewModel;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInteractor;
import use_case.create_event.CreateEventOutputBoundary;
import view.CreateEventView;

public class CreateEventUseCaseFactory {
    private CreateEventUseCaseFactory(){}

    public static CreateEventView create(CreateEventViewModel createEventViewModel,
                                         CreateEventController createEventController,
                                         BackOutController backOutController) {
        return new CreateEventView(createEventViewModel, createEventController, backOutController);
    }

    public static CreateEventController createEventUseCase(CreateEventViewModel createEventViewModel,
                                                           CreateEventDataAccessInterface eventDAO,
                                                           CreateEventDataAccessInterface userDAO, EventFactory eventFactory,
                                                           InviteOnlyEventFactory inviteEventFactory,
                                                           RestrictedEventFactory restrictedEventFactory,
                                                           LocationFactory locationFactory){
        CreateEventOutputBoundary presenter = new CreateEventPresenter(createEventViewModel);
        CreateEventInputBoundary interactor = new CreateEventInteractor(eventDAO, userDAO, presenter, eventFactory, inviteEventFactory, restrictedEventFactory, locationFactory);
        return new CreateEventController(interactor);
    }
}
