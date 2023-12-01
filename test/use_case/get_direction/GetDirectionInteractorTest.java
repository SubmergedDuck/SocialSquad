package use_case.get_direction;

import data_access.GenerateRoute;
import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUser;
import entity.Users.User;
import interface_adapter.get_direction.GetDirectionPresenter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Test used to check if a route image is displayed
 */
public class GetDirectionInteractorTest {
    private static GetDirectionInteractor getDirectionInteractor;
    private final static InMemoryUsersDataAccessObject usersDataAccessObject = new InMemoryUsersDataAccessObject();
    private final static InMemoryEventsDataAccessObject eventsDataAccessObject = new InMemoryEventsDataAccessObject();
    private final static GetDirectionAPIDataAccessInterface apiDataAccess = new GenerateRoute();
    private static String username;
    private static int eventID;


    /**
     * Initializes the objects that will be used for the tests.
     * @throws IOException api error
     */
    @Before
    public void init() throws IOException {
        User user = new CommonUser("username", "123", 20, "m", "test@gmail.com");
        username = user.getUsername();
        LocationFactory locationFactory = new CommonLocationFactory();
        Location userLocation = locationFactory.makeLocation("(43.662137,-79.377021)");
        Location eventLocation = locationFactory.makeLocation("(47.64054,-122.12934)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2022-04-08 12:30", formatter);
        Event event = new CommonEvent(0, "test event", "username", eventLocation,
                new ArrayList<String>(), new ArrayList<String>(), dateTime, "type", "description", false, 10);
        user.setLocation(userLocation);
        usersDataAccessObject.save(user);
        eventsDataAccessObject.save(event);
    }

    @Test
    public void presenterReceivesImageTest(){
        GetDirectionOutputBoundary mockPresenter = new GetDirectionOutputBoundary() {
            @Override
            public void prepareView(GetDirectionOutputData outputData) {
                assert(!(outputData.getDirectionImage() == null));
            }
        };
        getDirectionInteractor = new GetDirectionInteractor(mockPresenter, eventsDataAccessObject, usersDataAccessObject,
                apiDataAccess);
        GetDirectionInputData inputData = new GetDirectionInputData(eventID, username, 250,250);
        getDirectionInteractor.execute(inputData);
    }

    public static void main(String[] args) throws IOException {
        GetDirectionOutputBoundary mockPresenter = new GetDirectionOutputBoundary() {
            @Override
            public void prepareView(GetDirectionOutputData outputData) {
                BufferedImage image = outputData.getDirectionImage();
                JFrame frame = new JFrame();
                JLabel label = new JLabel(new ImageIcon(image));
                frame.getContentPane().add(label);
                frame.pack();
                frame.setVisible(true);
            }
        };
        getDirectionInteractor = new GetDirectionInteractor(mockPresenter, eventsDataAccessObject, usersDataAccessObject,
                apiDataAccess);
        GetDirectionInputData inputData = new GetDirectionInputData(eventID, username, 250,250);
        getDirectionInteractor.execute(inputData);
    }
}