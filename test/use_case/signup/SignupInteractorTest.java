package use_case.signup;

import data_access.InMemoryUsersDataAccessObject;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SignupInteractorTest {
    private InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject;

    /**
     * Initializes instances that will be used for the test.
     */
    @Before
    public void init() {
        UserFactory userFactory = new CommonUserFactory();
        inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
        User testCreator = userFactory.create("aa", "123", 2, "", "");
        inMemoryUsersDataAccessObject.save(testCreator);

    }
    /**
     * Tests if the presenter correctly indicates a successful signup.
     */
    @Test
    public void successfulSignup() throws IOException {
        SignupOutputBoundary mockPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                fail(error);
            }

            @Override
            public void prepareSuccessView(SignupOutputData output) {
                assertEquals("user1", output.getUsername());
            }

            @Override
            public void preparePreviousView() {
                fail("Should not prepare previous view on successful signup");
            }
        };

        SignupInteractor signupInteractor = new SignupInteractor(inMemoryUsersDataAccessObject, mockPresenter, new CommonUserFactory());
        SignupInputData testInput = new SignupInputData("user1", "password", "123", "123", "1", "f", "contact", false);
        signupInteractor.execute(testInput);
    }

    /**
     * Tests if the presenter correctly indicates a failed signup due to an existing username.
     */
    @Test
    public void existingUsername() throws IOException {
        SignupOutputBoundary mockPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals("Username already exists", error);
            }

            @Override
            public void prepareSuccessView(SignupOutputData output) {
                fail("Should not prepare success view on failed signup");
            }

            @Override
            public void preparePreviousView() {
                fail("Should not prepare previous view on failed signup");
            }
        };

        SignupInteractor signupInteractor = new SignupInteractor(inMemoryUsersDataAccessObject, mockPresenter, new CommonUserFactory());
        SignupInputData testInput = new SignupInputData("aa", "password", "123", "123", "wrong number", "f", "contact", false);
        signupInteractor.execute(testInput);

//        // Attempting to signup with the same username again
//        SignupInputData testInput2 = new SignupInputData("user1", "anotherpassword", "456", "456", "2", "m", "anothercontact", false);
//        signupInteractor.execute(testInput2);
    }

    /**
     * Tests if the presenter correctly indicates a failed signup due to password mismatch.
     */
    @Test
    public void passwordMismatch() throws IOException {
        SignupOutputBoundary mockPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords do not match", error);
            }

            @Override
            public void prepareSuccessView(SignupOutputData output) {
                fail("Should not prepare success view on failed signup");
            }

            @Override
            public void preparePreviousView() {
                fail("Should not prepare previous view on failed signup");
            }
        };

        SignupInteractor signupInteractor = new SignupInteractor(inMemoryUsersDataAccessObject, mockPresenter, new CommonUserFactory());
        SignupInputData testInput = new SignupInputData("1", "password", "123", "456", "1", "f", "contact", false);
        signupInteractor.execute(testInput);
    }
}
