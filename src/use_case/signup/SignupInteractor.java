package use_case.signup;

import data_access.InMemoryUsersDataAccessObject;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import view.SignupView;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject, SignupOutputBoundary userPresenter, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    public void execute(SignupInputData signupInputData) {
        if (signupInputData.isClickBack()) {
            userPresenter.preparePreviousView();
        } else if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already exists");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords do not match");
//        } else if (signupInputData.getAge() < 0){
//            //Maybe make it so that the user can only input numbers in the signup age textbox
//            userPresenter.prepareFailView("Invalid age input");
//        } else if (signupInputData.inputsEmpty()){
//            userPresenter.prepareFailView("Fill out all the inputs");
//        } else if (signupInputData.getSex().toLowerCase() != "m" || signupInputData.getSex().toLowerCase() != "f"){
//            //Checks if the input sex information is valid.
//            userPresenter.prepareFailView("Invalid sex");
        } else {
            // All the inputs are good.

            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getAge(),
                    signupInputData.getSex(), signupInputData.getContact());
            userDataAccessObject.save(user);
            SignupOutputData signupOutputData = new SignupOutputData(signupInputData.getUsername());

            userPresenter.prepareSuccessView(signupOutputData);
        }
    }}

//    public static void main(String[] args) {
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        SignupViewModel signupViewmodel = new SignupViewModel();
//        LoginViewModel loginViewModel = new LoginViewModel();
//        CommonUserFactory factory = new CommonUserFactory();
//        SignupOutputBoundary presenter = new SignupOutputBoundary() {
//            @Override
//            public void prepareFailView(String error) {
//                System.out.println("sign up failed");
//            }
//
//            @Override
//            public void prepareSuccessView(SignupOutputData output) {
//                System.out.println("sign up succeed");
//
//            }
//
//            @Override
//            public void preparePreviousView() {
//                System.out.println("back succeed");
//            }
//        };
//        SignupUserDataAccessInterface inMemoryUserDAO = new InMemoryUsersDataAccessObject();
//        SignupInputBoundary interactor = new SignupInteractor(inMemoryUserDAO, presenter, factory);
//        SignupInputData inputData = new SignupInputData("user1", "", "123", "123", "1", "f", "contact",true);
//        interactor.execute(inputData);
//        SignupInputData inputData2 = new SignupInputData("user1", "", "123", "123", "1", "f", "contact",true);
//        interactor.execute(inputData2);
//        interactor.execute(inputData2);
//    }
//}
//
