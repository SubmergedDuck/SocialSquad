package use_case.loggedIn;

import interface_adapter.ViewModel;

public class LoggedInInputData {

    final private String username;

    final private ViewModel viewModel;

    public LoggedInInputData(String username, ViewModel viewModel) {

        this.username = username;
        this.viewModel = viewModel;
    }

    String getUsername() {
        return username;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }
}
