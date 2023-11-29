package app;

import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.*;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Users.CommonUser;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.back_out.BackOutController;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.get_event_details.GetEventDetailsInteractor;
import use_case.join_event.JoinEventInputBoundary;
import use_case.join_event.JoinEventInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        SearchNearbyViewModel searchNearbyViewModel =  new SearchNearbyViewModel();
        GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();

        // Instantiate all Data Access Objects
        // TODO: change this to the real DAOs later
        InMemoryUsersDataAccessObject userDataAccessObject;
        userDataAccessObject = new InMemoryUsersDataAccessObject();
        userDataAccessObject.save(new CommonUser("aa", "123", 1, "f", "contact"));
        InMemoryEventsDataAccessObject eventDataAccessObject = new InMemoryEventsDataAccessObject();

        // Instantiate BackOut use case
        BackOutController backOutController = BackOutUseCaseFactory.createBackOutUseCase(viewManagerModel);

        // Instantiate EventDetails use case
        GetEventDetailsController getEventDetailsController = GetEventDetailsUseCaseFactory.createGetEventDetailsUseCase(getEventDetailsViewModel, viewManagerModel, eventDataAccessObject);

        // Instantiate CreateEvent use case
        // TODO replace with factory later
        CreateEventController createEventController = new CreateEventController();

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
                loginViewModel, userDataAccessObject, eventDataAccessObject, createEventController);
        views.add(loggedInView.getRootPane(), loggedInView.viewName);
        loggedInViewModel.addPropertyChangeListener(loggedInView); // Because HomeView constructor doesn't add the view to the view model.

        // Build GetEventDetails view
        // TODO: replace with the factory later
        EventDetailsView eventDetailsView = GetEventDetailsUseCaseFactory.create(getEventDetailsViewModel, joinEventController, backOutController);
        views.add(eventDetailsView.getRootPane(), eventDetailsView.viewName);

        // Build SearchNearby view
        // Instantiate GetEventDetails Controller and BackOutController here because their building method in their factory have not been written.
        SearchNearbyView searchNearbyView = SearchNearbyUseCaseFactory.create(viewManagerModel, searchNearbyViewModel,
                eventDataAccessObject, getEventDetailsController, backOutController);
        views.add(searchNearbyView.getRootPane(), searchNearbyView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
