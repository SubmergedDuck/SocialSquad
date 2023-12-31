package interface_adapter.login;

import interface_adapter.ViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import java.io.IOException;

public class LoginController {
    final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password, ViewModel viewModel) throws IOException {
        LoginInputData loginInputData = new LoginInputData(username, password, viewModel);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
