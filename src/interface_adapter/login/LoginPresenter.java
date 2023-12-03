package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.get_current_user.GetCurrentUserState;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import java.io.IOException;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;

    private final GetCurrentUserViewModel getCurrentUserViewModel;
    private ViewManagerModel viewManagerModel;


    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          GetCurrentUserViewModel getCurrentUserViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.getCurrentUserViewModel = getCurrentUserViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) throws IOException {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());

        GetCurrentUserState getCurrentUserState = getCurrentUserViewModel.getState();
        getCurrentUserState.setUsername(response.getUsername());
        getCurrentUserState.setUserCoordinates(response.getUserCoordinates());
        getCurrentUserViewModel.setState(getCurrentUserState);
        getCurrentUserViewModel.firePropertyChanged();


        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView("Home");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareLinkView(ViewModel viewModel) {
        // jump to another view, set its previous view name to current one
        viewModel.setPreviousViewName(loginViewModel.getViewName());
        this.viewManagerModel.setActiveView(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }


    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
