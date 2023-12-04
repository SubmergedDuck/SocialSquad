package use_case.login;

import data_access.CoordinatesFromIP;
import data_access.InMemoryCurrentUserDAO;
import data_access.InMemoryUsersDataAccessObject;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUser;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.common_interfaces.GetCoordinatesIP;
import use_case.signup.*;

import java.io.IOException;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;

    final LoginCurrentUserDataAccessInterface currentUserDataAccessObject;

    final LoginOutputBoundary loginPresenter;

    final LocationFactory locationFactory;

    final GetCoordinatesIP getCoordinatesIP;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary, LoginCurrentUserDataAccessInterface currentUserDataAccessObject, LocationFactory locationFactory,
                           GetCoordinatesIP getCoordinatesIP) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.currentUserDataAccessObject = currentUserDataAccessObject;
        this.locationFactory = locationFactory;
        this.getCoordinatesIP = getCoordinatesIP;
    }

    @Override
    public void execute(LoginInputData loginInputData) throws IOException {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        ViewModel viewModel = loginInputData.getViewModel();
        if (viewModel != null && viewModel.getViewName() !=null && !viewModel.getViewName().isEmpty()) {
            loginPresenter.prepareLinkView(viewModel);
        } else if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {
                User user = userDataAccessObject.get(loginInputData.getUsername());
                String[] currentCoordinates = getCoordinatesIP.getCoordinates();
                String formattedCoordinates = String.format("(%s,%s)",currentCoordinates[0], currentCoordinates[1]);

                Location userLocation = locationFactory.makeLocation(formattedCoordinates);
                user.setLocation(userLocation);
                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), user.getLocation().getCoordinates());
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }


//
//    public static void main(String[] args) throws IOException {
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        SignupViewModel signupViewmodel = new SignupViewModel();
//        LoginViewModel loginViewModel = new LoginViewModel();
//        LoginOutputBoundary presenter = new LoginOutputBoundary() {
//            @Override
//            public void prepareSuccessView(LoginOutputData user) {
//                System.out.println("sign in succeed");
//            }
//
//            @Override
//            public void prepareLinkView(ViewModel viewModel) {
//
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                System.out.println("sign in failed");
//            }
//
//        };
//
//        LoginUserDataAccessInterface inMemoryUserDAO = new InMemoryUsersDataAccessObject();
//        LoginInputBoundary interactor = new LoginInteractor(inMemoryUserDAO, presenter, new InMemoryCurrentUserDAO(),
//                new CommonLocationFactory());
//        inMemoryUserDAO.save(new CommonUser("user1","aa",2,"",""));
//        LoginInputData inputData = new LoginInputData("user1", "aa",null);
//        interactor.execute(inputData);
//        LoginInputData inputData2 = new LoginInputData("user1", "bb",null);
//        interactor.execute(inputData2);
//        LoginInputData inputData3 = new LoginInputData("user2", "aa",null);
//        interactor.execute(inputData3);
//
//    }

}
