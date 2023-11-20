package app;

import entity.Users.CommonUserFactory;
import entity.Users.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import use_case.loggedIn.LoggedInUserDataAccessInterface;
import use_case.loggedIn.LoggedInInputBoundary;
import use_case.loggedIn.LoggedInInteractor;
import use_case.loggedIn.LoggedInOutputBoundary;
import use_case.loggedIn.LoggedInUserDataAccessInterface;
import view.HomeView;


import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory(){

    }
    public static HomeView create(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            LoginViewModel loginViewModel,
            LoggedInUserDataAccessInterface userDataAccessInterface

    ){
        try{
            LoggedInController loggedInController = createLoggedInUseCase(viewManagerModel,loggedInViewModel, loginViewModel,userDataAccessInterface);
            return new HomeView(loggedInViewModel,loggedInController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;

    }

    private static  LoggedInController createLoggedInUseCase(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            LoginViewModel loginViewModel,
            LoggedInUserDataAccessInterface userDataAccessInterface
    ) throws IOException{
        LoggedInOutputBoundary loggedInOutputBoundary = new LoggedInPresenter(viewManagerModel,loggedInViewModel,loginViewModel);
        UserFactory userFactory = new CommonUserFactory();

        LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(userDataAccessInterface, loggedInOutputBoundary);

        return new LoggedInController(loggedInInteractor);
    }
}
