package use_case.create_event;

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
import java.io.IOException;
import java.io.PrintStream;

/**
 * Used for testing the create event interactor.
 */
public class CreateEventInteractorTest {
    private CreateEventInteractor createEventInteractor;
    private CreateEventDataAccessInterface inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
    private CreateEventDataAccessInterface inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Initializes instances that will be used for the test.
     */
    @Before
    public void init() {
        //Creating all the instances for the event interactor
        LocationFactory locationFactory = new CommonLocationFactory();
        EventFactory eventFactory = new CommonEventFactory();
        CreateEventOutputBoundary mockPresenter = new MockCreateEventPresenter();
        InMemoryUsersDataAccessObject userDAO = (InMemoryUsersDataAccessObject)inMemoryUsersDataAccessObject;
        //Adding the event creator to the user in memory DAO.
        UserFactory userFactory = new CommonUserFactory();
        User testCreator = userFactory.create("Bob", "123", 20, "m", "bob@gmail.com");
        userDAO.save(testCreator);

        this.createEventInteractor = new CreateEventInteractor(inMemoryEventsDataAccessObject,
                inMemoryUsersDataAccessObject, mockPresenter, eventFactory, locationFactory);
    }

    /**
     * Sets up a print stream before the test (used for checking printed outputs)
     */
    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restores the stream after the test (used for checking printed outputs)
     */
    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    /**
     * Tests if the presenter correctly indicates if an invalid input was passed into the interactor.
     */
    @Test
    public void invalidInput() throws IOException {
        //Detects if an invalid input for create event was done.

        //Creates regular event with an invalid input. Here, there is no owner.
        CreateEventInputData testInput = new CreateEventInputData("Bob", "", "(47.64054,-122.12934)", "2016-03-04 11:30",
                "Movie night", "Have fun!", "10");
        createEventInteractor.execute(testInput);
        String printedOutput = outContent.toString();
        assertEquals("error\n", printedOutput);
    }

    /**
     * Tests if the presenter correctly indicates if a valid input was passed into the interactor
     */
    @Test
    public void validInput() throws IOException {
        //Creates a restricted event where the age restriction is 5 and there is no sex restriction.
        CreateEventInputData testInput = new CreateEventInputData("Bob", "Movie", "(47.64054,-122.12934)", "2016-03-04 11:30",
                "Movie night", "Have fun!", "10");
        createEventInteractor.execute(testInput);
        String printedOutput = outContent.toString();
        assertEquals("success\n", printedOutput);
    }

    /**
     * Tests if an event was added to the event DAO.
     */
    @Test
    public void addsEvent() throws IOException {
        //Checks if the event was added to the DAO.
        CreateEventInputData testInput = new CreateEventInputData("Bob", "Test", "(47.64054,-122.12934)", "2016-03-04 11:30",
                "Movie night", "Have fun!", "10");
        createEventInteractor.execute(testInput);
        InMemoryEventsDataAccessObject eventDAO = (InMemoryEventsDataAccessObject)inMemoryEventsDataAccessObject;
        assertEquals(1, eventDAO.getEventMap().size());
    }

    /**
     * Tests if a user's created events was updated after they create the event
     */
    @Test
    public void updatesUser() throws IOException {
        CreateEventInputData testInput = new CreateEventInputData("Bob", "Movie", "(47.64054,-122.12934)", "2016-03-04 11:30",
                "Movie night", "Have fun!", "10");
        createEventInteractor.execute(testInput);
        InMemoryUsersDataAccessObject userDAO =  (InMemoryUsersDataAccessObject)inMemoryUsersDataAccessObject;
        User userTest = userDAO.getUser("Bob");
        assertEquals(1, userTest.getCreatedEvents().size());
    }

}