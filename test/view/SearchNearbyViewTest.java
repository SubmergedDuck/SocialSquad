package view;

import data_access.GenerateRoute;
import data_access.InMemoryCurrentUserDAO;
import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUser;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewManagerModelAdapter;
import interface_adapter.back_out.BackOutController;
import interface_adapter.back_out.BackOutPresenter;
import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserPresenter;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_direction.GetDirectionController;
import interface_adapter.get_direction.GetDirectionPresenter;
import interface_adapter.get_direction.GetDirectionViewModel;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.join_event.JoinEventPresenter;
import interface_adapter.join_event.JoinEventViewModel;
import interface_adapter.search_nearby.SearchNearbyPresenter;
import interface_adapter.search_nearby.SearchNearbyState;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.back_out.BackOutInteractor;
import use_case.get_current_user.GetCurrentUserInteractor;
import use_case.get_direction.GetDirectionInteractor;
import use_case.get_event_details.GetEventDetailsInteractor;
import use_case.join_event.JoinEventInteractor;
import use_case.search_nearby.SearchNearbyInteractor;
import use_case.search_nearby.SearchNearbyOutputData;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearchNearbyViewTest {
    private SearchNearbyView view;

    @Before
    public void setUp() throws Exception {
        JFrame application = new JFrame("Search nearby - Event detail demo");
        SearchNearbyState state = new SearchNearbyState();
        SearchNearbyViewModel searchNearbyViewModel = new SearchNearbyViewModel();
        InMemoryEventsDataAccessObject inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();
        InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
        InMemoryCurrentUserDAO inMemoryCurrentUserDAO = new InMemoryCurrentUserDAO();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CommonUserFactory userFactory = new CommonUserFactory();
        User tempUser = userFactory.create("username","123",20,"m","contact");
        inMemoryCurrentUserDAO.changeUser(tempUser);

        SearchNearbyInteractor interactor = new SearchNearbyInteractor(inMemoryEventsDataAccessObject, new SearchNearbyPresenter(searchNearbyViewModel, viewManagerModel));

        GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();
        GetEventDetailsPresenter getEventDetailsPresenter = new GetEventDetailsPresenter(getEventDetailsViewModel, viewManagerModel);
        GetEventDetailsInteractor interactor1 = new GetEventDetailsInteractor(getEventDetailsPresenter, inMemoryEventsDataAccessObject);
        GetEventDetailsController getEventDetailsController = new GetEventDetailsController(interactor1);

        ViewManagerModelAdapter viewManagerModelAdapter = new ViewManagerModelAdapter(viewManagerModel);
        BackOutPresenter backOutPresenter = new BackOutPresenter(viewManagerModelAdapter);
        BackOutInteractor backOutInteractor = new BackOutInteractor(backOutPresenter);
        BackOutController backOutController = new BackOutController(backOutInteractor);

        SearchNearbyView view = new SearchNearbyView(searchNearbyViewModel, getEventDetailsController, backOutController);
        JoinEventViewModel joinEventViewModel = new JoinEventViewModel("join event");
        JoinEventPresenter joinEventPresenter = new JoinEventPresenter(joinEventViewModel);
        JoinEventInteractor joinEventInteractor = new JoinEventInteractor(joinEventPresenter,inMemoryUsersDataAccessObject,
                inMemoryEventsDataAccessObject,inMemoryCurrentUserDAO);
        JoinEventController joinEventController = new JoinEventController(joinEventInteractor);

        GetDirectionViewModel getDirectionViewModel1 = new GetDirectionViewModel();
        GetDirectionPresenter getDirectionPresenter = new GetDirectionPresenter(getDirectionViewModel1);
        GetDirectionInteractor getDirectionInteractor = new GetDirectionInteractor(getDirectionPresenter,inMemoryEventsDataAccessObject,inMemoryUsersDataAccessObject,
                new GenerateRoute());
        GetDirectionController getDirectionController1 = new GetDirectionController(getDirectionInteractor);

        GetCurrentUserViewModel getCurrentUserViewModel1 = new GetCurrentUserViewModel();
        GetCurrentUserPresenter getCurrentUserPresenter = new GetCurrentUserPresenter(getCurrentUserViewModel1);
        GetCurrentUserInteractor getCurrentUserInteractor = new GetCurrentUserInteractor(getCurrentUserPresenter,inMemoryCurrentUserDAO);
        GetCurrentUserController getCurrentUserController1 = new GetCurrentUserController(getCurrentUserInteractor);

        EventDetailsView eventDetailsView = new EventDetailsView(getEventDetailsViewModel, joinEventController,joinEventViewModel,backOutController,
                getDirectionController1,getDirectionViewModel1,getCurrentUserViewModel1, getCurrentUserController1);

        searchNearbyViewModel.addPropertyChangeListener(view);
        getEventDetailsViewModel.addPropertyChangeListener(view); //TODO here
        getEventDetailsViewModel.addPropertyChangeListener(eventDetailsView); //TODO here

        view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        views.add(view.getRootPane(), "search nearby");
        views.add(eventDetailsView.getRootPane(), "get event details");
        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);
        viewManagerModel.addPropertyChangeListener(viewManager);

        // test run: received events nearby --> view event details --> join event
        try {
            User user = new CommonUser("owner", "password", 20, "f", "contact");

            LocationFactory factory = new CommonLocationFactory();
            Location location = factory.makeLocation("(43.665510,-79.387280)"); // Home, within 2KM
            Location location2 = factory.makeLocation("(43.645531,-79.380348)"); // Union Station (3KM)
            entity.Events.Event event = new CommonEvent(1, "badminton", "owner", location, new ArrayList<>(),
                    new ArrayList<>(), LocalDateTime.now(), "type", "description", false,
                    10); // This event should be returned
            entity.Events.Event event2 = new CommonEvent(2, "group trip", "owner", location2, new ArrayList<>(),
                    new ArrayList<>(), LocalDateTime.now(), "type", "description", false, 10);

            ArrayList<entity.Events.Event> eventArrayList = new ArrayList<>();
            eventArrayList.add(event);
            eventArrayList.add(event2);

            user.setCreatedEvents(eventArrayList); // Let the user create these events

            // Save the objects to inMemoryDAOs for use
            inMemoryUsersDataAccessObject.save(user);
            for (Event event1: eventArrayList) {
                inMemoryEventsDataAccessObject.save(event1);
            }

            state.setEventsSearched(eventArrayList);
            searchNearbyViewModel.setState(state);
            SearchNearbyPresenter presenter = new SearchNearbyPresenter(searchNearbyViewModel, viewManagerModel);
            presenter.prepareSuccessView(new SearchNearbyOutputData(false, eventArrayList));

            getEventDetailsController.execute(event.getEventID(),true);
            viewManagerModel.setActiveView(view.viewName);
            viewManagerModel.firePropertyChanged();

            application.pack();
            application.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.view = view;
    }

    @Test
    public void testMain() {
        view.setVisible(true);
        SearchNearbyView.main(new String[]{});
    }
}
