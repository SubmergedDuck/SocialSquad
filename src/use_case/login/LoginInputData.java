package use_case.login;

import interface_adapter.ViewModel;

public class LoginInputData {
    final private String username;
    final private String password;

    final private ViewModel viewModel;

    public LoginInputData(String username, String password, ViewModel viewModel) {
        this.username = username;
        this.password = password;
        this.viewModel = viewModel;
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }


}
