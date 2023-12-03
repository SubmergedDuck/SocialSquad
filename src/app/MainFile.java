package app;

import data_access.*;
import entity.Events.CommonEventFactory;
import entity.Location.CommonLocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.back_out.BackOutController;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.create_event.CreateEventViewModel;
import interface_adapter.generate_static_map.GenerateStaticMapController;
import interface_adapter.generate_static_map.GenerateStaticMapPresenter;
import interface_adapter.generate_static_map.GenerateStaticMapViewModel;
import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserPresenter;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_direction.GetDirectionController;
import interface_adapter.get_direction.GetDirectionPresenter;
import interface_adapter.get_direction.GetDirectionViewModel;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.get_ids.GetIDsController;
import interface_adapter.get_ids.GetIDsPresenter;
import interface_adapter.get_ids.GetIDsViewModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.join_event.JoinEventViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.my_event.MyEventViewModel;
import interface_adapter.search_nearby.SearchNearbyController;
import interface_adapter.search_nearby.SearchNearbyPresenter;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.generate_static_map.GSMInteractor;
import use_case.get_current_user.GetCurrentUserInteractor;
import use_case.get_direction.GetDirectionInteractor;
import use_case.get_event_details.GetEventDetailsInteractor;
import use_case.get_ids.GetIDsInteractor;
import use_case.join_event.JoinEventInputBoundary;
import use_case.loggedIn.LoggedInInteractor;
import use_case.search_nearby.SearchNearbyInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MainFile {
    public static void main(String[] args) throws IOException {
        JFrame application = new JFrame("SocialSquad");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        //Create view models
        JoinEventViewModel joinEventViewModel = new JoinEventViewModel("join event");
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        MyEventViewModel myEventViewModel = new MyEventViewModel();
        SearchNearbyViewModel searchNearbyViewModel = new SearchNearbyViewModel();
        GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();
        CreateEventViewModel createEventViewModel = new CreateEventViewModel(viewManagerModel);
        GetCurrentUserViewModel getCurrentUserViewModel = new GetCurrentUserViewModel();
        GetDirectionViewModel getDirectionViewModel = new GetDirectionViewModel();
        GetIDsViewModel getIDsViewModel = new GetIDsViewModel();
        GenerateStaticMapViewModel generateStaticMapViewModel = new GenerateStaticMapViewModel();

        //Create DAOs
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        FileEventDataAccessObject fileEventDataAccessObject = new FileEventDataAccessObject("events.csv",new CommonEventFactory(),new CommonLocationFactory(),formatter);
        FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject("users.csv", new CommonUserFactory(), fileEventDataAccessObject);
        InMemoryCurrentUserDAO currentUserDAO = new InMemoryCurrentUserDAO();
        CommonUserFactory userFactory = new CommonUserFactory();
        User temporaryUser = userFactory.create("username","123",5,"m","contact");
        currentUserDAO.changeUser(temporaryUser);
        fileUserDataAccessObject.save(temporaryUser);

        //Create controllers
        BackOutController backOutController = BackOutUseCaseFactory.createBackOutUseCase(viewManagerModel);
        GetIDsController getIDsController = GetIDsUseCaseFactory.createGetIDsUseCase(getIDsViewModel,fileUserDataAccessObject);
        GetEventDetailsController onlyGetEventDetailsController = GetEventDetailsUseCaseFactory.createGetEventDetailsUseCase(getEventDetailsViewModel,viewManagerModel,fileEventDataAccessObject);
        GetCurrentUserController getCurrentUserController = GetCurrentUserUseCaseFactory.createGetCurrentUserUseCase(getCurrentUserViewModel,currentUserDAO);
        SearchNearbyController searchNearbyController = SearchNearbyUseCaseFactory.createSearchNearbyUseCase(viewManagerModel,searchNearbyViewModel,fileEventDataAccessObject);
        GenerateStaticMapController generateStaticMapController = GenerateStaticMapUseCaseFactory.createGenerateStaticMapUseCase(generateStaticMapViewModel,
                fileEventDataAccessObject,fileUserDataAccessObject);
        GetDirectionController getDirectionController = GetDirectionUseCaseFactory.createGetDirectionUseCase(getDirectionViewModel,fileEventDataAccessObject,
                fileUserDataAccessObject);
        LoggedInController loggedInController = LoggedInUseCaseFactory.createLoggedInUseCase(viewManagerModel,loggedInViewModel,loginViewModel,myEventViewModel,fileUserDataAccessObject);
        JoinEventController joinEventController = JoinEventUseCaseFactory.joinEventUseCase(joinEventViewModel, fileEventDataAccessObject, fileUserDataAccessObject, currentUserDAO);


        CreateEventController createEventController = CreateEventUseCaseFactory.createEventUseCase(createEventViewModel, fileEventDataAccessObject,
                fileUserDataAccessObject, new CommonEventFactory(), new CommonLocationFactory());
        CreateEventView createEventView = CreateEventUseCaseFactory.create(createEventViewModel, createEventController, backOutController, getCurrentUserViewModel);
        views.add(createEventView.getRootPane(), createEventView.viewName);


        //Build login view
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel,
                signupViewModel, fileUserDataAccessObject, getCurrentUserViewModel, currentUserDAO);
        views.add(loginView.getRootPane(), loginView.viewName);

        //Build signup view
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel,
                fileUserDataAccessObject);
        views.add(signupView.getRootPane(), signupView.viewName);

        HomeView loggedInView = new HomeView(loggedInViewModel, loggedInController, searchNearbyController,
                createEventController, createEventViewModel, getCurrentUserViewModel, generateStaticMapController,generateStaticMapViewModel,myEventViewModel);
        views.add(loggedInView.getRootPane(), loggedInView.viewName);
        loggedInViewModel.addPropertyChangeListener(loggedInView); // Because HomeView constructor doesn't add the view to the view model.

        MyEventsView myEventsView = MyEventUseCaseFactory.create(viewManagerModel,myEventViewModel,fileUserDataAccessObject,
                getIDsController,getIDsViewModel,getCurrentUserController,backOutController,getCurrentUserViewModel,
                onlyGetEventDetailsController,getEventDetailsViewModel);
        views.add(myEventsView.getRootPane(),myEventsView.viewName);
        myEventViewModel.addPropertyChangeListener(myEventsView);
        getCurrentUserViewModel.addPropertyChangeListener(myEventsView);

        // Build SearchNearby view
        SearchNearbyView searchNearbyView = SearchNearbyUseCaseFactory.create(viewManagerModel, searchNearbyViewModel,
                fileEventDataAccessObject, onlyGetEventDetailsController, backOutController);
        views.add(searchNearbyView.getRootPane(), searchNearbyView.viewName);

        // Build GetEventDetails view
        GetDirectionViewModel getDirectionViewModel1 = new GetDirectionViewModel();
        GetDirectionPresenter getDirectionPresenter = new GetDirectionPresenter(getDirectionViewModel1);
        GetDirectionInteractor getDirectionInteractor = new GetDirectionInteractor(getDirectionPresenter,fileEventDataAccessObject,fileUserDataAccessObject,
                new GenerateRoute());
        GetDirectionController getDirectionController1 = new GetDirectionController(getDirectionInteractor);

        EventDetailsView eventDetailsView = new EventDetailsView(getEventDetailsViewModel, joinEventController,
                joinEventViewModel,backOutController,
                getDirectionController1,getDirectionViewModel1,getCurrentUserViewModel,getCurrentUserController);
        views.add(eventDetailsView.getRootPane(), eventDetailsView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
