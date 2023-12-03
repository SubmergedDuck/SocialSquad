package use_case.create_event;

import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.*;
import entity.Location.CommonLocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

/**
 * Used for testing the create event interactor.
 */
public class CreateEventInteractorTest {
    private InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
    private InMemoryEventsDataAccessObject inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();
    /**
     * Initializes instances that will be used for the test.
     */
    @Before
    public void init() {
        UserFactory userFactory = new CommonUserFactory();
        User testCreator = userFactory.create("Bob", "123", 20, "m", "bob@gmail.com");
        inMemoryUsersDataAccessObject.save(testCreator);
    }

    /**
     * Tests if the presenter correctly indicates if an invalid input was passed into the interactor.
     */
    @Test
    public void invalidInput() throws IOException {
        //Detects if an invalid input for create event was done.
        CreateEventOutputBoundary mockPresenter = new CreateEventOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
            }

            @Override
            public void prepareSuccessView() {
                fail();
            }
        };
        //Creates regular event with an invalid input. Here, there is no owner.
        CreateEventInputData testInput = new CreateEventInputData("Bob", "", "fake coordinates", "2004 11:30",
                "", "Have fun!", "abc");
        CreateEventInteractor createEventInteractor = new CreateEventInteractor(inMemoryEventsDataAccessObject,
                inMemoryUsersDataAccessObject,mockPresenter,new CommonEventFactory(), new CommonLocationFactory());
        createEventInteractor.execute(testInput);
    }

    /**
     * Tests if the presenter correctly indicates if a valid input was passed into the interactor
     */
    @Test
    public void validInput() throws IOException {
        CreateEventOutputBoundary mockPresenter = new CreateEventOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                fail();
            }

            @Override
            public void prepareSuccessView() {
            }
        };
        CreateEventInputData testInput = new CreateEventInputData("Bob", "Movie", "(47.64054,-122.12934)", "2016-03-04 11:30",
                "Movie night", "Have fun!", "10");
        CreateEventInteractor createEventInteractor = new CreateEventInteractor(inMemoryEventsDataAccessObject,
                inMemoryUsersDataAccessObject,mockPresenter,new CommonEventFactory(), new CommonLocationFactory());
    }

    /**
     * Tests if an event was added to the event DAO.
     */
    @Test
    public void addsEvent() throws IOException {
        //Checks if the event was added to the DAO.
        CreateEventOutputBoundary mockPresenter = new CreateEventOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                fail();
            }

            @Override
            public void prepareSuccessView() {
                assertEquals(1, inMemoryEventsDataAccessObject.getEventMap().size());
            }
        };
        CreateEventInputData testInput = new CreateEventInputData("Bob", "Movie", "(47.64054,-122.12934)", "2016-03-04 11:30",
                "Movie night", "Have fun!", "10");
        CreateEventInteractor createEventInteractor = new CreateEventInteractor(inMemoryEventsDataAccessObject,
                inMemoryUsersDataAccessObject,mockPresenter,new CommonEventFactory(), new CommonLocationFactory());
        createEventInteractor.execute(testInput);
    }

    /**
     * Tests if a user's created events was updated after they create the event
     */
    @Test
    public void updatesUser() throws IOException {
        CreateEventOutputBoundary mockPresenter = new CreateEventOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                fail();
            }

            @Override
            public void prepareSuccessView() {
                User userTest = inMemoryUsersDataAccessObject.getUser("Bob");
                assertEquals(1, userTest.getCreatedEvents().size());
            }
        };
        CreateEventInputData testInput = new CreateEventInputData("Bob", "Movie", "(47.64054,-122.12934)", "2016-03-04 11:30",
                "Movie night", "Have fun!", "10");
        CreateEventInteractor createEventInteractor = new CreateEventInteractor(inMemoryEventsDataAccessObject,
                inMemoryUsersDataAccessObject,mockPresenter,new CommonEventFactory(), new CommonLocationFactory());
        createEventInteractor.execute(testInput);
        InMemoryUsersDataAccessObject userDAO =  (InMemoryUsersDataAccessObject)inMemoryUsersDataAccessObject;

    }

}