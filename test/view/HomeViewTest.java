package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.create_event.CreateEventViewModel;
import interface_adapter.generate_static_map.GenerateStaticMapController;
import interface_adapter.generate_static_map.GenerateStaticMapViewModel;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_ids.GetIDsViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.my_event.MyEventViewModel;
import interface_adapter.search_nearby.SearchNearbyController;
import interface_adapter.signup.SignupViewModel;
import org.junit.Test;
import use_case.create_event.CreateEventInputBoundary;
import use_case.generate_static_map.GSMInputBoundary;
import use_case.generate_static_map.GSMInteractor;
import use_case.loggedIn.LoggedInInputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.search_nearby.SearchNearbyInputBoundary;

import javax.swing.*;
import java.io.IOException;

public class HomeViewTest {

    @Test
    public void testHomeView() throws IOException {
        HomeView.main(new String[]{});

        LoggedInInputBoundary loggedInInputBoundary = null;
        SearchNearbyInputBoundary searchNearbyInputBoundary = null;
        CreateEventInputBoundary createEventInputBoundary = null;
        GSMInputBoundary gsmInputBoundary = null;
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        LoggedInController loggedInController = new LoggedInController(loggedInInputBoundary);
        SearchNearbyController searchNearbyController = new SearchNearbyController(searchNearbyInputBoundary);
        CreateEventController createEventController = new CreateEventController(createEventInputBoundary);
        CreateEventViewModel createEventViewModel = new CreateEventViewModel(viewManagerModel);
        GetCurrentUserViewModel getCurrentUserViewModel = new GetCurrentUserViewModel();
        GenerateStaticMapController generateStaticMapController = new GenerateStaticMapController(gsmInputBoundary);
        GenerateStaticMapViewModel generateStaticMapViewModel = new GenerateStaticMapViewModel();
        GetIDsViewModel getIDsViewModel = new GetIDsViewModel();

        JFrame homeView = new HomeView(loggedInViewModel, loggedInController, searchNearbyController, createEventController, createEventViewModel, getCurrentUserViewModel, generateStaticMapController, generateStaticMapViewModel, getIDsViewModel);
        homeView.setVisible(true);
    }
}
