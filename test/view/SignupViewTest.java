package view;

import data_access.InMemoryUsersDataAccessObject;
import entity.Users.CommonUser;
import entity.Users.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInputBoundary;
import org.junit.Before;
import org.junit.Test;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class SignupViewTest {
    @Test
    public void testSignupView() {
        SignupView.main(new String[]{});

        SignupInputBoundary sib = null;
        SignupController controller = new SignupController(sib);
        SignupViewModel viewModel = new SignupViewModel();

        JFrame signupView = new SignupView(controller, viewModel);
        signupView.setVisible(true);

    }

}
