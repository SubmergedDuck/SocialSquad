package app;

import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.*;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUser;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.back_out.BackOutController;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.create_event.CreateEventPresenter;
import interface_adapter.create_event.CreateEventViewModel;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_nearby.SearchNearbyPresenter;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInteractor;
import use_case.create_event.CreateEventOutputBoundary;
import use_case.get_event_details.GetEventDetailsInteractor;
import use_case.join_event.JoinEventInputBoundary;
import use_case.join_event.JoinEventInteractor;
import use_case.search_nearby.SearchNearbyOutputData;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("SocialSquad");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.

        // Instantiate all View Models
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        SearchNearbyViewModel searchNearbyViewModel = new SearchNearbyViewModel();
        GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();
        CreateEventViewModel createEventViewModel = new CreateEventViewModel(viewManagerModel);

        // Instantiate all Data Access Objects
        // TODO: change this to the real DAOs later
        InMemoryUsersDataAccessObject userDataAccessObject = new InMemoryUsersDataAccessObject();
        InMemoryEventsDataAccessObject eventDataAccessObject = new InMemoryEventsDataAccessObject();

        // Instantiate all factories
        EventFactory eventFactory = new CommonEventFactory();
        RestrictedEventFactory restrictedEventFactory = new RestrictedEventFactory() {
            @Override
            public RestrictedEvent create(Integer eventID, String eventName, String owner, Location location, ArrayList<String> peopleJoined, ArrayList<String> peopleWaitlisted, LocalDateTime time, String type, String description, Boolean privacy, Integer capacity, Integer ageRestriction, String sexRestriction) {
                return null;
            }
        };

        InviteOnlyEventFactory inviteEventFactory = new InviteOnlyEventFactory() {
            @Override
            public InviteOnlyEvent create(Integer eventID, String eventName, String owner, Location location, ArrayList<String> peopleJoined, ArrayList<String> peopleWaitlisted, LocalDateTime time, String type, String description, Boolean privacy, Integer capacity, ArrayList<String> peopleInvited) {
                return null;
            }
        };

        LocationFactory locationFactory = new CommonLocationFactory();

        // Create sample entities
        userDataAccessObject.save(new CommonUser("aa", "123", 1, "f", "contact"));

        try {
            User user = new CommonUser("owner", "password", 20, "f", "contact");

            LocationFactory factory = new CommonLocationFactory();
            Location location = factory.makeLocation("(43.665510,-79.387280)"); // Home, within 2KM
            Location location2 = factory.makeLocation("(43.645531,-79.380348)"); // Union Station (3KM)
            entity.Events.Event event = new CommonEvent(1, "badminton", "owner", location, new ArrayList<>(),
                    new ArrayList<>(), LocalDateTime.now(), "type", "description", false,
                    10); // This event should be returned
            entity.Events.Event event2 = new CommonEvent(2, "group trip", "owner", location2, new ArrayList<>(),
                    new ArrayList<>(), LocalDateTime.now(), "type", "description", false, 10);

            ArrayList<entity.Events.Event> eventArrayList = new ArrayList<>();
            eventArrayList.add(event);
            eventArrayList.add(event2);

            user.setCreatedEvents(eventArrayList); // Let the user create these events

            // Save the objects to inMemoryDAOs for use
            userDataAccessObject.save(user);
            for (Event event1 : eventArrayList) {
                eventDataAccessObject.save(event1);
            }

        } catch (Exception e) {
            System.out.println("run time exceptions occured.");
        }


        // Instantiate BackOut use case
        BackOutController backOutController = BackOutUseCaseFactory.createBackOutUseCase(viewManagerModel);

        // Instantiate EventDetails use case
        GetEventDetailsController getEventDetailsController =
                GetEventDetailsUseCaseFactory.createGetEventDetailsUseCase(getEventDetailsViewModel, viewManagerModel, eventDataAccessObject);

        // Instantiate CreateEvent use case
        CreateEventController createEventController =
                CreateEventUseCaseFactory.createEventUseCase(createEventViewModel, eventDataAccessObject,
                        userDataAccessObject, eventFactory, inviteEventFactory, restrictedEventFactory, locationFactory);
        CreateEventView createEventView = CreateEventUseCaseFactory.create(createEventViewModel, createEventController, backOutController);
        views.add(createEventView.getRootPane(), createEventView.viewName);

        // Instantiate JoinEvent use case
        // TODO replace with factory later
        JoinEventInputBoundary joinEventInteractor = new JoinEventInteractor();
        JoinEventController joinEventController = new JoinEventController(joinEventInteractor);

        // Build Login view
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
                signupViewModel, userDataAccessObject);
        views.add(loginView.getRootPane(), loginView.viewName);

        // Build Signup view
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject);
        views.add(signupView.getRootPane(), signupView.viewName);

        // Build Home view
        HomeView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel,loggedInViewModel,searchNearbyViewModel,
                loginViewModel, userDataAccessObject, eventDataAccessObject, createEventController, createEventViewModel);
        views.add(loggedInView.getRootPane(), loggedInView.viewName);
        loggedInViewModel.addPropertyChangeListener(loggedInView); // Because HomeView constructor doesn't add the view to the view model.


        // Build SearchNearby view
        SearchNearbyView searchNearbyView = SearchNearbyUseCaseFactory.create(viewManagerModel, searchNearbyViewModel,
                eventDataAccessObject, getEventDetailsController, backOutController);
        views.add(searchNearbyView.getRootPane(), searchNearbyView.viewName);

        // Build GetEventDetails view
        EventDetailsView eventDetailsView = GetEventDetailsUseCaseFactory.create(getEventDetailsViewModel, searchNearbyView, joinEventController, backOutController);
        views.add(eventDetailsView.getRootPane(), eventDetailsView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
