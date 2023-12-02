package app;

import entity.Users.CommonUserFactory;
import entity.Users.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.my_event.MyEventController;
import interface_adapter.my_event.MyEventPresenter;
import interface_adapter.my_event.MyEventViewModel;
import interface_adapter.search_nearby.SearchNearbyController;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import use_case.loggedIn.LoggedInUserDataAccessInterface;
import use_case.loggedIn.LoggedInInputBoundary;
import use_case.loggedIn.LoggedInInteractor;
import use_case.loggedIn.LoggedInOutputBoundary;
import use_case.my_event.MyEventDataAccessInterface;
import use_case.my_event.MyEventInputBoundary;
import use_case.my_event.MyEventInteractor;
import use_case.my_event.MyEventOutputBoundary;
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
            MyEventDataAccessInterface dataAccessInterface,
            SearchNearbyDataAccessInterface searchNearbyDataAccessObject,
            CreateEventController createEventController,
            MyEventViewModel myEventViewModel) {
        try{
            LoggedInController loggedInController = createLoggedInUseCase(viewManagerModel,loggedInViewModel, loginViewModel,userDataAccessInterface,myEventViewModel);
            SearchNearbyController searchNearbyController = SearchNearbyUseCaseFactory.createSearchNearbyUseCase(viewManagerModel, searchNearbyViewModel, searchNearbyDataAccessObject);
            MyEventController myEventController = createUserMyEventUseCase(viewManagerModel,myEventViewModel,loginViewModel,dataAccessInterface);
            return new HomeView(loggedInViewModel,loggedInController, searchNearbyController, createEventController, myEventController, myEventViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;

    }

    private static  LoggedInController createLoggedInUseCase(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            LoginViewModel loginViewModel,
            LoggedInUserDataAccessInterface userDataAccessInterface,
            MyEventViewModel myEventViewModel
    ) throws IOException{
        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(viewManagerModel,loggedInViewModel,loginViewModel, myEventViewModel);
        UserFactory userFactory = new CommonUserFactory();

        LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(userDataAccessInterface, loggedInOutputBoundary);

        return new LoggedInController(loggedInInteractor);
    }
    private static MyEventController createUserMyEventUseCase(
            ViewManagerModel viewManagerModel,
            MyEventViewModel myEventViewModel,
            LoginViewModel loginViewModel,
            MyEventDataAccessInterface dataAccessInterface) throws IOException{
        MyEventOutputBoundary myEventOutputBoundary = new MyEventPresenter(myEventViewModel);

        UserFactory userFactory = new CommonUserFactory();
        MyEventInputBoundary myEventInputBoundary = new MyEventInteractor(
                myEventOutputBoundary,dataAccessInterface
        );
        return  new MyEventController(myEventInputBoundary);
    }
}
