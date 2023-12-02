package app;

import data_access.GenerateStaticMapBody;
import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Users.CommonUserFactory;
import entity.Users.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.back_out.BackOutController;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.create_event.CreateEventViewModel;
import interface_adapter.generate_static_map.GenerateStaticMapPresenter;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.generate_static_map.GenerateStaticMapController;
import interface_adapter.generate_static_map.GenerateStaticMapViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.my_event.MyEventViewModel;
import interface_adapter.search_nearby.SearchNearbyController;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import use_case.generate_static_map.GSMInteractor;
import use_case.loggedIn.LoggedInUserDataAccessInterface;
import use_case.loggedIn.LoggedInInputBoundary;
import use_case.loggedIn.LoggedInInteractor;
import use_case.loggedIn.LoggedInOutputBoundary;
import use_case.loggedIn.LoggedInUserDataAccessInterface;
import use_case.search_nearby.SearchNearbyDataAccessInterface;
import use_case.search_nearby.SearchNearbyInteractor;
import use_case.search_nearby.SearchNearbyDataAccessInterface;
import view.HomeView;


import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory(){

    }
    public static HomeView create(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            SearchNearbyViewModel searchNearbyViewModel,
            LoginViewModel loginViewModel,
            LoggedInUserDataAccessInterface userDataAccessInterface,
            SearchNearbyDataAccessInterface searchNearbyDataAccessObject,
            CreateEventController createEventController,
            CreateEventViewModel createEventViewModel,
            MyEventViewModel myEventViewModel,
            GetCurrentUserViewModel getCurrentUserViewModel) {
        try{
            LoggedInController loggedInController = createLoggedInUseCase(viewManagerModel,loggedInViewModel, loginViewModel,myEventViewModel,userDataAccessInterface);
            SearchNearbyController searchNearbyController = SearchNearbyUseCaseFactory.createSearchNearbyUseCase(viewManagerModel, searchNearbyViewModel, searchNearbyDataAccessObject);

            GenerateStaticMapViewModel generateStaticMapViewModel = new GenerateStaticMapViewModel();
            GenerateStaticMapPresenter generateStaticMapPresenter = new GenerateStaticMapPresenter(generateStaticMapViewModel);
            GSMInteractor generateStaticMapInteractor = new GSMInteractor(new GenerateStaticMapBody(), new InMemoryUsersDataAccessObject(),
                    new InMemoryEventsDataAccessObject(), generateStaticMapPresenter);
            GenerateStaticMapController generateStaticMapController = new GenerateStaticMapController(generateStaticMapInteractor);
            return new HomeView(loggedInViewModel,loggedInController, searchNearbyController,
                    createEventController, createEventViewModel, getCurrentUserViewModel,generateStaticMapController, generateStaticMapViewModel,myEventViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;

    }

    private static  LoggedInController createLoggedInUseCase(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            LoginViewModel loginViewModel,
            MyEventViewModel myEventViewModel,
            LoggedInUserDataAccessInterface userDataAccessInterface
    ) throws IOException{
        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(viewManagerModel,loggedInViewModel,loginViewModel,myEventViewModel);
        UserFactory userFactory = new CommonUserFactory();

        LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(userDataAccessInterface, loggedInOutputBoundary);

        return new LoggedInController(loggedInInteractor);
    }
}
