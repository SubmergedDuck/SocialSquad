package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.my_event.MyEventState;
import interface_adapter.my_event.MyEventViewModel;
import use_case.loggedIn.LoggedInOutputBoundary;
import use_case.loggedIn.LoggedInOutputData;

public class LoggedInPresenter implements LoggedInOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;

    private final LoginViewModel loginViewModel;

    private final MyEventViewModel myEventViewModel;

    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel,
                             LoginViewModel loginViewModel, MyEventViewModel myEventViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.myEventViewModel = myEventViewModel;
    }

    @Override
    public void prepareSuccessView(LoggedInOutputData user) {
        MyEventState myEventState = myEventViewModel.getState();
        this.myEventViewModel.setState(myEventState);
        myEventViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(myEventViewModel.getViewName());
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
