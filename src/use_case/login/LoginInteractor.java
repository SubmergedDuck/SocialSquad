package use_case.login;

import data_access.InMemoryUsersDataAccessObject;
import entity.Users.CommonUser;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.*;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User User = userDataAccessObject.get(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(User.getUsername(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
    public static void main(String[] args) {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewmodel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                System.out.println("sign in succeed");
            }

            @Override
            public void prepareFailView(String error) {
                System.out.println("sign in failed");
            }

        };

        LoginUserDataAccessInterface inMemoryUserDAO = new InMemoryUsersDataAccessObject();
        LoginInputBoundary interactor = new LoginInteractor(inMemoryUserDAO, presenter);
        inMemoryUserDAO.save(new CommonUser("user1","aa",2,"",""));
        LoginInputData inputData = new LoginInputData("user1", "aa");
        interactor.execute(inputData);
        LoginInputData inputData2 = new LoginInputData("user1", "bb");
        interactor.execute(inputData2);
        LoginInputData inputData3 = new LoginInputData("user2", "aa");
        interactor.execute(inputData3);

    }
}
