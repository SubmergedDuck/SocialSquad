package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.get_ids.GetIDsState;
import interface_adapter.get_ids.GetIDsViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.loggedIn.LoggedInOutputBoundary;
import use_case.loggedIn.LoggedInOutputData;

public class LoggedInPresenter implements LoggedInOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;

    private final LoginViewModel loginViewModel;

    private final GetIDsViewModel getIDsViewModel;

    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel,
                             LoginViewModel loginViewModel, GetIDsViewModel getIDsViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.getIDsViewModel = getIDsViewModel;
    }

    @Override
    public void prepareSuccessView(LoggedInOutputData user) {
        GetIDsState getIDsState = getIDsViewModel.getState();
        this.getIDsViewModel.setState(getIDsState);
        getIDsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(getIDsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareLogOutView(LoggedInOutputData response) {
        /*
        return to login view
         */
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

    @Override
    public void prepareLinkView(ViewModel viewModel) {
        // jump to another view, set its previous view name to current one
        viewModel.setPreviousViewName(loggedInViewModel.getViewName());
        this.viewManagerModel.setActiveView(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
