package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_ids.GetIDsViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import use_case.loggedIn.LoggedInInteractor;
import use_case.loggedIn.LoggedInUserDataAccessInterface;

public class LoggedInUseCaseFactory {
    public static LoggedInController createLoggedInUseCase(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel,
                                                           LoginViewModel loginViewModel, GetIDsViewModel getIDsViewModel,
                                                           LoggedInUserDataAccessInterface userDataAccesssObject){
        LoggedInPresenter loggedInPresenter = new LoggedInPresenter(viewManagerModel,loggedInViewModel,loginViewModel,getIDsViewModel);
        LoggedInInteractor loggedInInteractor = new LoggedInInteractor(userDataAccesssObject,loggedInPresenter);
        LoggedInController loggedInController = new LoggedInController(loggedInInteractor);
        return loggedInController;
    }
}