package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.loggedIn.LoggedInOutputBoundary;
import use_case.loggedIn.LoggedInOutputData;

public class LoggedInPresenter implements LoggedInOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;

    private final LoginViewModel loginViewModel;

    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel,
                             LoginViewModel loginViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoggedInOutputData response) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(error);
        loginViewModel.firePropertyChanged();


    }
}
