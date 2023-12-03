package use_case.login;

import data_access.CoordinatesFromIP;
import data_access.InMemoryUsersDataAccessObject;
import entity.Location.CommonLocation;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import interface_adapter.ViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.common_interfaces.GetCoordinatesIP;
import use_case.get_current_user.CurrentUserDataAccessInterface;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@link LoginInteractor}.
 */
public class LoginInteractorTest {
    private InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject;
    private LoginCurrentUserDataAccessInterface currentUserDataAccessInterface;

    private LocationFactory locationFactory = new CommonLocationFactory();

    private GetCoordinatesIP getCoordinatesIP = new CoordinatesFromIP();

    /**
     * Initialize the test environment.
     */
    @Before
    public void init() throws IOException {

        UserFactory userFactory = new CommonUserFactory();
        inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
        User testCreator = userFactory.create("aa", "123", 2, "", "");
        inMemoryUsersDataAccessObject.save(testCreator);


    }

    /**
     * Test a valid login scenario.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testValidLogin() throws IOException {
        LoginOutputBoundaryMock presenter = new LoginOutputBoundaryMock();

        LoginInputBoundary interactor = new LoginInteractor(inMemoryUsersDataAccessObject,presenter,currentUserDataAccessInterface,locationFactory,getCoordinatesIP);

        LoginInputData inputData = new LoginInputData("aa", "123", null);
        interactor.execute(inputData);

        assertEquals("sign in succeed", presenter.getSuccessMessage());
    }

    /**
     * Test an invalid password scenario.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testInvalidPassword() throws IOException {
        LoginOutputBoundaryMock presenter = new LoginOutputBoundaryMock();
        LoginInputBoundary interactor = new LoginInteractor(inMemoryUsersDataAccessObject, presenter,currentUserDataAccessInterface,locationFactory,getCoordinatesIP);

        LoginInputData inputData = new LoginInputData("aa", "wrong_password", null);
        interactor.execute(inputData);

        assertEquals("sign in failed: Incorrect password for aa.", presenter.getFailMessage());
    }

    /**
     * Test a scenario where the user does not exist.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testNonexistentUser() throws IOException {
        LoginOutputBoundaryMock presenter = new LoginOutputBoundaryMock();
        LoginInputBoundary interactor = new LoginInteractor(inMemoryUsersDataAccessObject, presenter,currentUserDataAccessInterface,locationFactory,getCoordinatesIP);

        LoginInputData inputData = new LoginInputData("nonexistent_user", "password", null);
        interactor.execute(inputData);

        assertEquals("sign in failed: nonexistent_user: Account does not exist.", presenter.getFailMessage());
    }

    /**
     * A mock implementation of {@link LoginOutputBoundary} for testing purposes.
     */

    private static class LoginOutputBoundaryMock implements LoginOutputBoundary {
        private String successMessage;
        private String failMessage;

        @Override
        public void prepareSuccessView(LoginOutputData user) {
            successMessage = "sign in succeed";
        }

        @Override
        public void prepareLinkView(ViewModel viewModel) {
            // No need for actual implementation in the mock
        }

        @Override
        public void prepareFailView(String error) {
            failMessage = "sign in failed: " + error;
        }

        /**
         * Get the success message.
         *
         * @return the success message
         */
        public String getSuccessMessage() {
            return "sign in succeed";
        }

        /**
         * Get the fail message.
         *
         * @return the fail message
         */
        public String getFailMessage() {
            return failMessage;
        }
    }
}


//public class LoginInteractorTest {
//    private InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
//
//    @Before
//    public void init() {
//        UserFactory userFactory = new CommonUserFactory();
//        User testCreator = userFactory.create("aa", "123", 20, "m", "bob@gmail.com");
//        inMemoryUsersDataAccessObject.save(testCreator);
//    }
//    /**
//     * Tests if the presenter correctly indicates if an invalid input was passed into the interactor.
//     */
//    @Test
//    public void LoginUsernameNullTest() throws IOException {
//        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
//            @Override
//            public void prepareSuccessView(LoginOutputData user) {
//
//            }
//
//            @Override
//            public void prepareLinkView(ViewModel viewModel) {
//
//            }
//
//            @Override
//            public void prepareFailView(String error) {fail();
//            }
//        };
//        LoginInputData testInput = new LoginInputData("b","a",null);
//        LoginInteractor loginInteractor = new LoginInteractor(inMemoryUsersDataAccessObject,loginOutputBoundary);
//        loginInteractor.execute(testInput);
//        assert inMemoryUsersDataAccessObject.existsByName("b");
//
//
//    }









