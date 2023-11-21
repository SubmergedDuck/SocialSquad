package use_case.get_direction;

import data_access.GenerateRoute;
import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEvent;
import entity.Events.CommonEventFactory;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUser;
import entity.Users.User;
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

public class GetDirectionInteractorTest {
    public static GetDirectionInteractor getDirectionInteractor;
    public static String username;
    public static int eventID;

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
        GetDirectionAPIDataAccessInterface apiDataAccess = new GenerateRoute();

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
        InMemoryUsersDataAccessObject usersDataAccessObject = new InMemoryUsersDataAccessObject();
        usersDataAccessObject.save(user);
        InMemoryEventsDataAccessObject eventsDataAccessObject = new InMemoryEventsDataAccessObject();
        eventsDataAccessObject.save(event);
        getDirectionInteractor = new GetDirectionInteractor(mockPresenter, eventsDataAccessObject, usersDataAccessObject,
                apiDataAccess);


        GetDirectionInputData inputData = new GetDirectionInputData(eventID, username);
        getDirectionInteractor.execute(inputData);
    }
}