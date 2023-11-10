package use_case;

import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.*;
import entity.Location.CommonLocationFactory;
import entity.Location.LocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import interface_adapter.create_event.MockCreateEventPresenter;
import use_case.create_event.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CreateEventInteractorTest {
    private CreateEventInteractor createEventInteractor;

    private CreateEventDataAccessInterface inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();

    private CreateEventDataAccessInterface inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void init() {
        //Creating all the instances for the event interactor
        LocationFactory locationFactory = new CommonLocationFactory();
        InviteOnlyEventFactory inviteOnlyEventFactory = new CommonInviteOnlyEventFactory();
        RestrictedEventFactory restrictedEventFactory = new CommonRestrictedEventFactory();
        EventFactory eventFactory = new CommonEventFactory();
        CreateEventOutputBoundary mockPresenter = new MockCreateEventPresenter();
        InMemoryUsersDataAccessObject userDAO = (InMemoryUsersDataAccessObject)inMemoryUsersDataAccessObject;
        //Adding the event creator to the user in memory DAO.
        UserFactory userFactory = new CommonUserFactory();
        User testCreator = userFactory.create("Bob", "123", 20, "m", "Bob", "bob@gmail.com");
        userDAO.save(testCreator);

        this.createEventInteractor = new CreateEventInteractor(inMemoryEventsDataAccessObject,
                inMemoryUsersDataAccessObject, mockPresenter, eventFactory, inviteOnlyEventFactory, restrictedEventFactory,
                locationFactory);
    }
    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    public void invalidInput(){
        //Detects if an invalid input for create event was done.

        //Creates regular event with an invalid input. Here, there is no owner.
        CreateEventInputData testInput = new CreateEventInputData("Bob", "", "(38.8951, -77.0364)", "2016-03-04 11:30",
                "Movie night", "Have fun!", false, "10");
        createEventInteractor.execute(testInput);
        String printedOutput = outContent.toString();
        assertEquals("error\n", printedOutput);
    }

    @Test
    public void validInput(){
        //Detects if a valid input for create event was done


        //Creates a restricted event where the age restriction is 5 and there is no sex restriction.
        CreateEventInputData testInput = new CreateEventInputData("Bob", "Movie", "(38.8951, -77.0364)", "2016-03-04 11:30",
                "Movie night", "Have fun!", false, "10", "5", "");
        createEventInteractor.execute(testInput);
        String printedOutput = outContent.toString();
        assertEquals("success\n", printedOutput);
    }

    @Test
    public void addsEvent(){
        //Checks if the event was added to the DAO.
        CreateEventInputData testInput = new CreateEventInputData("Bob", "Movie", "(38.8951, -77.0364)", "2016-03-04 11:30",
                "Movie night", "Have fun!", false, "10", "5", "");
        createEventInteractor.execute(testInput);
        InMemoryEventsDataAccessObject eventDAO = (InMemoryEventsDataAccessObject)inMemoryEventsDataAccessObject;
        assertEquals(1, eventDAO.getEventMap().size());
    }

    @Test
    public void updatesUser(){
        CreateEventInputData testInput = new CreateEventInputData("Bob", "Movie", "(38.8951, -77.0364)", "2016-03-04 11:30",
                "Movie night", "Have fun!", false, "10", "5", "");
        createEventInteractor.execute(testInput);
        InMemoryUsersDataAccessObject userDAO =  (InMemoryUsersDataAccessObject)inMemoryUsersDataAccessObject;
        User userTest = userDAO.getUser("Bob");
        assertEquals(1, userTest.getCreatedEvents().size());
    }

}