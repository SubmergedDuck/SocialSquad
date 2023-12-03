package use_case.loggedIn;

import data_access.InMemoryUsersDataAccessObject;
import interface_adapter.ViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoggedInInteractorTest {
    private InMemoryUsersDataAccessObject inMemoryLoggedInUserDataAccessObject;

    /**
     * Initializes instances that will be used for the test.
     */
    @Before
    public void init() {
        inMemoryLoggedInUserDataAccessObject = new InMemoryUsersDataAccessObject();
    }

    /**
     * Tests if the presenter correctly indicates a link view request.
     */
    @Test
    public void linkViewRequest() {
        LoggedInOutputBoundary mockPresenter = new LoggedInOutputBoundary() {
            @Override
            public void prepareLinkView(ViewModel viewModel) {
                assertEquals("Home", viewModel.getViewName());
            }

            @Override
            public void prepareSuccessView(LoggedInOutputData user) {


            }

            @Override
            public void prepareLogOutView(LoggedInOutputData outputData) {
                fail("Should not prepare logout view on link view request");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Should not prepare logout view on link view request");

            }
        };

        LoggedInInteractor loggedInInteractor = new LoggedInInteractor(inMemoryLoggedInUserDataAccessObject, mockPresenter);
        LoggedInInputData testInput = new LoggedInInputData("user1", new LoggedInViewModel());
        loggedInInteractor.execute(testInput);
    }

    /**
     * Tests if the presenter correctly indicates a logout view request.
     */
    @Test
    public void logOutRequest() {
        LoggedInOutputBoundary mockPresenter = new LoggedInOutputBoundary() {
            @Override
            public void prepareLinkView(ViewModel viewModel) {
                fail("Should not prepare link view on logout view request");
            }

            @Override
            public void prepareSuccessView(LoggedInOutputData user) {

            }

            @Override
            public void prepareLogOutView(LoggedInOutputData outputData) {
                assertEquals("user1", outputData.getUsername());

            }

            @Override
            public void prepareFailView(String error) {

            }
        };

        LoggedInInteractor loggedInInteractor = new LoggedInInteractor(inMemoryLoggedInUserDataAccessObject, mockPresenter);
        LoggedInInputData testInput = new LoggedInInputData("user1", null);
        loggedInInteractor.execute(testInput);
    }
}
