package use_case.generate_static_map;

import data_access.GenerateStaticMapBody;
import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUser;
import entity.Users.User;
import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Used for testing the generate static map use case
 */
public class GSMInteractorTest {

    private static final InMemoryEventsDataAccessObject eventsDataAccessObject = new InMemoryEventsDataAccessObject();
    private static final InMemoryUsersDataAccessObject usersDataAccessObject = new InMemoryUsersDataAccessObject();
    private static final GenerateStaticMapBody apiDataAccessObject = new GenerateStaticMapBody();

    private static final LocationFactory locationFactory = new CommonLocationFactory();

    private static User testUser;

    /**
     * Initializes objects that will be used for the tests.
     * @throws IOException if an api error occurs
     */
    @Before
    public void init() throws IOException {
        testUser = new CommonUser("username", "123", 20, "m", "test@gmail.com");
        Location userLocation = locationFactory.makeLocation("(43.662137,-79.377021)");
        testUser.setLocation(userLocation);
        Location eventLocation1 = locationFactory.makeLocation("(43.68341,-79.76633)");
        Location eventLocation2 = locationFactory.makeLocation("(43.4669381322,-79.6857955901)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2022-04-08 12:30", formatter);
        Event event1 = new CommonEvent(1, "test event", "username", eventLocation1,
                new ArrayList<String>(), new ArrayList<String>(), dateTime, "type", "description", false, 10);
        Event event2 = new CommonEvent(2, "test event", "username", eventLocation2,
                new ArrayList<String>(), new ArrayList<String>(), dateTime, "type", "description", false, 10);

        usersDataAccessObject.save(testUser);
        eventsDataAccessObject.save(event1);
        eventsDataAccessObject.save(event2);
    }

    /**
     * Tests if the presenter receives an actual image from the interactor.
     */
    @Test
    public void receiveMapTest() throws IOException {
        GSMOutputBoundary mockPresenter = new GSMOutputBoundary() {
            @Override
            public void prepareView(GSMOutputData outputData) {
                assert!(outputData.getGeneratedMap() == null);
            }
        };
        GSMInputData inputData = new GSMInputData(testUser.getLocation().getCoordinates(), 3,300,300);
        GSMInteractor testInteractor = new GSMInteractor(apiDataAccessObject,usersDataAccessObject,
                eventsDataAccessObject,mockPresenter);
        testInteractor.execute(inputData);
    }

    /**
     * Used for showing that the interactor properly provides the presenter with a static map with pins
     * @param args args for psvm
     * @throws IOException if there is an api error
     */
    public static void main(String[] args) throws IOException {
        GSMOutputBoundary mockPresenter = new GSMOutputBoundary() {
            @Override
            public void prepareView(GSMOutputData outputData) {
                BufferedImage image = outputData.getGeneratedMap();
                JFrame frame = new JFrame();
                JLabel label = new JLabel(new ImageIcon(image));
                frame.getContentPane().add(label);
                frame.pack();
                frame.setVisible(true);
            }
        };
        GSMInputData inputData = new GSMInputData(testUser.getLocation().getCoordinates(), 3,300,300);
        GSMInteractor testInteractor = new GSMInteractor(apiDataAccessObject,usersDataAccessObject,
                eventsDataAccessObject,mockPresenter);
        testInteractor.execute(inputData);
    }
}