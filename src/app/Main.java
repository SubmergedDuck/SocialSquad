package app;

import data_access.GenerateRoute;
import data_access.InMemoryCurrentUserDAO;
import data_access.GenerateStaticMapURL;
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
import interface_adapter.get_current_user.*;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_direction.GetDirectionController;
import interface_adapter.get_direction.GetDirectionPresenter;
import interface_adapter.get_direction.GetDirectionViewModel;
import interface_adapter.generate_static_map.GenerateStaticMapController;
import interface_adapter.generate_static_map.GenerateStaticMapPresenter;
import interface_adapter.generate_static_map.GenerateStaticMapViewModel;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.get_event_details.OnlyGetEventDetailsPresenter;
import interface_adapter.get_ids.GetIDsController;
import interface_adapter.get_ids.GetIDsPresenter;
import interface_adapter.get_ids.GetIDsViewModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.my_event.MyEventViewModel;
import interface_adapter.search_nearby.SearchNearbyController;
import interface_adapter.search_nearby.SearchNearbyPresenter;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInteractor;
import use_case.create_event.CreateEventOutputBoundary;
import use_case.get_current_user.GetCurrentUserInteractor;
import use_case.get_direction.GetDirectionInteractor;
import use_case.generate_static_map.GSMInteractor;
import use_case.get_event_details.GetEventDetailsInteractor;
import use_case.get_ids.GetIDsInteractor;
import use_case.join_event.JoinEventInputBoundary;
import use_case.loggedIn.LoggedInInteractor;
import use_case.search_nearby.SearchNearbyInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
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
        MyEventViewModel myEventViewModel = new MyEventViewModel();
        SearchNearbyViewModel searchNearbyViewModel = new SearchNearbyViewModel();
        GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();
        CreateEventViewModel createEventViewModel = new CreateEventViewModel(viewManagerModel);
        GetCurrentUserViewModel getCurrentUserViewModel = new GetCurrentUserViewModel();

        // Instantiate all Data Access Objects
        // TODO: change this to the real DAOs later
        InMemoryCurrentUserDAO currentUserDAO = new InMemoryCurrentUserDAO();
        InMemoryUsersDataAccessObject userDataAccessObject = new InMemoryUsersDataAccessObject();
        InMemoryEventsDataAccessObject eventDataAccessObject = new InMemoryEventsDataAccessObject();
        User testUser = new CommonUser("aa", "123", 1, "f", "contact");
        currentUserDAO.changeUser(testUser);
        GetCurrentUserState testState = getCurrentUserViewModel.getState();
        testState.setUsername(testState.getUsername());
        getCurrentUserViewModel.setState(testState);
        // Instantiate all factories
        EventFactory eventFactory = new CommonEventFactory();

        LocationFactory locationFactory = new CommonLocationFactory();
        InMemoryEventsDataAccessObject myeventDataAccessObject = new InMemoryEventsDataAccessObject();

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
                        userDataAccessObject, eventFactory, locationFactory);
        CreateEventView createEventView = CreateEventUseCaseFactory.create(createEventViewModel, createEventController, backOutController, getCurrentUserViewModel);
        views.add(createEventView.getRootPane(), createEventView.viewName);

        // Instantiate JoinEvent use case
        // TODO replace with factory later
        JoinEventInputBoundary joinEventInteractor = null; //TEMPORARY
        JoinEventController joinEventController = new JoinEventController(joinEventInteractor);

        // Build Login view
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
                signupViewModel, userDataAccessObject, getCurrentUserViewModel);
        views.add(loginView.getRootPane(), loginView.viewName);

        // Build Signup view
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                userDataAccessObject);
        views.add(signupView.getRootPane(), signupView.viewName);

        // Build Home view

        GenerateStaticMapViewModel generateStaticMapViewModel = new GenerateStaticMapViewModel();
        GenerateStaticMapPresenter generateStaticMapPresenter = new GenerateStaticMapPresenter(generateStaticMapViewModel);
        GSMInteractor gsmInteractor = new GSMInteractor(new GenerateStaticMapURL(),userDataAccessObject,eventDataAccessObject,generateStaticMapPresenter);
        GenerateStaticMapController generateStaticMapController = new GenerateStaticMapController(gsmInteractor);
        SearchNearbyPresenter searchNearbyPresenter = new SearchNearbyPresenter(searchNearbyViewModel,viewManagerModel);
        SearchNearbyInteractor searchNearbyInteractor = new SearchNearbyInteractor(eventDataAccessObject,searchNearbyPresenter);
        SearchNearbyController searchNearbyController = new SearchNearbyController(searchNearbyInteractor);
        LoggedInPresenter loggedInPresenter = new LoggedInPresenter(viewManagerModel,loggedInViewModel,loginViewModel,myEventViewModel);
        LoggedInInteractor loggedInInteractor = new LoggedInInteractor(userDataAccessObject,loggedInPresenter);
        LoggedInController loggedInController = new LoggedInController(loggedInInteractor);
        HomeView loggedInView = new HomeView(loggedInViewModel, loggedInController, searchNearbyController,
                createEventController, createEventViewModel, getCurrentUserViewModel, generateStaticMapController,generateStaticMapViewModel,myEventViewModel);
        views.add(loggedInView.getRootPane(), loggedInView.viewName);
        loggedInViewModel.addPropertyChangeListener(loggedInView); // Because HomeView constructor doesn't add the view to the view model.

        //Build MyEvents View
        CommonUserFactory userFactory = new CommonUserFactory();
        User newUser = userFactory.create("username","123",20,"m","test contact");
        currentUserDAO.changeUser(newUser);
        userDataAccessObject.save(newUser);
        ArrayList<String> peopledJoined = new ArrayList<>();
        peopledJoined.add(newUser.getUsername());
        Location location = null;
        Location location2 = null;
        try {
            location = locationFactory.makeLocation("(43.665510,-79.387280)");
            location2 = locationFactory.makeLocation("(43.4669381322,-79.6857955901)");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Event newEvent = eventFactory.create(0,"test event","another user",location,peopledJoined,
                new ArrayList<String>(), LocalDateTime.now(),"test event", "test", false, 10);
        eventDataAccessObject.save(newEvent);
        ArrayList<Event> userJoinedEvents = newUser.getJoinedEvents();
        userJoinedEvents.add(newEvent);
        newUser.setLocation(location2);

        //TODO:fix

        GetIDsViewModel getIDsViewModel = new GetIDsViewModel();

        //Creating the controllers,presenters, and interactors for each use case in this view.
        GetCurrentUserPresenter getCurrentUserPresenter = new GetCurrentUserPresenter(getCurrentUserViewModel);
        GetIDsPresenter getIDsPresenter = new GetIDsPresenter(getIDsViewModel);
        GetEventDetailsPresenter getEventDetailsPresenter = new GetEventDetailsPresenter(getEventDetailsViewModel,viewManagerModel);

        GetCurrentUserInteractor getCurrentUserInteractor = new GetCurrentUserInteractor(getCurrentUserPresenter, currentUserDAO);
        GetIDsInteractor getIDsInteractor = new GetIDsInteractor(userDataAccessObject, getIDsPresenter);
        GetEventDetailsInteractor getEventDetailsInteractor = new GetEventDetailsInteractor(getEventDetailsPresenter,eventDataAccessObject);

        GetCurrentUserController getCurrentUserController1 = new GetCurrentUserController(getCurrentUserInteractor);
        GetIDsController getIDsController1 = new GetIDsController(getIDsInteractor);
        GetEventDetailsController onlyGetEventDetailsController = new GetEventDetailsController(getEventDetailsInteractor);

        MyEventsView myEventsView = MyEventUseCaseFactory.create(viewManagerModel,myEventViewModel,myeventDataAccessObject,getIDsController1,getIDsViewModel,getCurrentUserController1,getCurrentUserViewModel,
                onlyGetEventDetailsController,getEventDetailsViewModel);
        views.add(myEventsView.getRootPane(),myEventsView.viewName);
        myEventViewModel.addPropertyChangeListener(myEventsView);

        // Build SearchNearby view
        SearchNearbyView searchNearbyView = SearchNearbyUseCaseFactory.create(viewManagerModel, searchNearbyViewModel,
                eventDataAccessObject, getEventDetailsController, backOutController);
        views.add(searchNearbyView.getRootPane(), searchNearbyView.viewName);

        // Build GetEventDetails view
        GetDirectionViewModel getDirectionViewModel1 = new GetDirectionViewModel();
        GetDirectionPresenter getDirectionPresenter = new GetDirectionPresenter(getDirectionViewModel1);
        GetDirectionInteractor getDirectionInteractor = new GetDirectionInteractor(getDirectionPresenter,eventDataAccessObject,userDataAccessObject,
                new GenerateRoute());
        GetDirectionController getDirectionController1 = new GetDirectionController(getDirectionInteractor);

        EventDetailsView eventDetailsView = new EventDetailsView(getEventDetailsViewModel, joinEventController,backOutController,
                getDirectionController1,getDirectionViewModel1,getCurrentUserViewModel,getCurrentUserController1);
        views.add(eventDetailsView.getRootPane(), eventDetailsView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
