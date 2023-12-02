package app;

import entity.Location.CommonLocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_account.CreateAccountController;
import interface_adapter.create_account.CreateAccountPresesnter;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.create_account.CreateAccountInputBoundary;
import use_case.create_account.CreateAccountInteractor;
import use_case.create_account.CreateAccountOutputBoundary;
import use_case.login.*;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            SignupViewModel signupViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            GetCurrentUserViewModel getCurrentUserViewModel, LoginCurrentUserDataAccessInterface currentUserDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel,signupViewModel, userDataAccessObject, getCurrentUserViewModel, currentUserDataAccessObject);
            CreateAccountController createAccountController = createAccountUseCase(viewManagerModel,loginViewModel,signupViewModel,userDataAccessObject);
            return new LoginView(loginViewModel,loginController,signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            SignupViewModel signupViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            GetCurrentUserViewModel getCurrentUserViewModel, LoginCurrentUserDataAccessInterface currentUserDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, getCurrentUserViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary,currentUserDataAccessObject, new CommonLocationFactory());

        return new LoginController(loginInteractor);
    }
    private static CreateAccountController createAccountUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SignupViewModel signupViewModel,
            LoginUserDataAccessInterface userDataAccessInterface) throws IOException {

        CreateAccountOutputBoundary createAccountOutputBoundary = new CreateAccountPresesnter(viewManagerModel, signupViewModel, loginViewModel);

        CreateAccountInputBoundary createAccountInputBoundary = new CreateAccountInteractor(
                createAccountOutputBoundary);
        return new CreateAccountController(createAccountInputBoundary);

    }

}
