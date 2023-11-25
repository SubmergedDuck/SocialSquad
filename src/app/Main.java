package app;

import data_access.FileEventsDataAccessObject;
import data_access.FileUserDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.*;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Users.CommonUser;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import view.HomeView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

//        FileUserDataAccessObject userDataAccessObject;
//        try {
//            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new FileEventsDataAccessObject("./user.csv", new EventFactory() {
//                @Override
//                public Event create(Integer eventID, String eventName, String owner, Location location, ArrayList<String> peopleJoined, ArrayList<String> peopleWaitlisted, LocalDateTime time, String type, String description, Boolean privacy, Integer capacity) {
//                    return null;
//                }
//            }, new InviteOnlyEventFactory() {
//                @Override
//                public InviteOnlyEvent create(Integer eventID, String eventName, String owner, Location location, ArrayList<String> peopleJoined, ArrayList<String> peopleWaitlisted, LocalDateTime time, String type, String description, Boolean privacy, Integer capacity, ArrayList<String> peopleInvited) {
//                    return null;
//                }
//            },new CommonRestrictedEventFactory(), new CommonLocationFactory()), new CommonLocationFactory(),
//            new CommonUserFactory());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // TODO: change this to the real DAOs later
        InMemoryUsersDataAccessObject userDataAccessObject;
        userDataAccessObject = new InMemoryUsersDataAccessObject();
//        userDataAccessObject.save(new CommonUser("aa", "123", 1, "f", "contact"));


        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView.getRootPane(), loginView.viewName);
//        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
//        views.add(signupView.getRootPane(), signupView.viewName);
//        HomeView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel,loggedInViewModel,loginViewModel,userDataAccessObject);
//        views.add(loggedInView.getRootPane(), loggedInView.viewName);


        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
