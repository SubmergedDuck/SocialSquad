package app;

import data_access.InMemoryUsersDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("SocialSquad");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel("login");
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel("logged in");
        SignupViewModel signupViewModel = new SignupViewModel();

        // TODO: change this to the real DAOs later
        InMemoryUsersDataAccessObject userDataAccessObject;
        userDataAccessObject = new InMemoryUsersDataAccessObject();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView.getRootPane(), signupView.viewName);

// Commented out login view use case because not received from remote yet
//        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
//        views.add(loginView, loginView.viewName);
//
//        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
//        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
