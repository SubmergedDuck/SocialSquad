package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.login.LoginInputBoundary;
import use_case.signup.SignupInputBoundary;

import javax.swing.*;

public class LoginViewTest {
    @Test
    public void testLoginView() {
        SignupView.main(new String[]{});

        LoginInputBoundary sib = null;
        LoginController controller = new LoginController(sib);
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        JFrame loginView = new LoginView(loginViewModel, controller, signupViewModel);
        loginView.setVisible(true);

    }
}
