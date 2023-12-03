package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.my_event.MyEventViewModel;
import use_case.loggedIn.LoggedInInteractor;
import use_case.loggedIn.LoggedInUserDataAccessInterface;

public class LoggedInUseCaseFactory {
    public static LoggedInController createLoggedInUseCase(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel,
                                                           LoginViewModel loginViewModel, MyEventViewModel myEventViewModel,
                                                           LoggedInUserDataAccessInterface userDataAccesssObject){
        LoggedInPresenter loggedInPresenter = new LoggedInPresenter(viewManagerModel,loggedInViewModel,loginViewModel,myEventViewModel);
        LoggedInInteractor loggedInInteractor = new LoggedInInteractor(userDataAccesssObject,loggedInPresenter);
        LoggedInController loggedInController = new LoggedInController(loggedInInteractor);
        return loggedInController;
    }
}