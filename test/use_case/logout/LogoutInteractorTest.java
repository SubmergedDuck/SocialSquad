package use_case.logout;

import entity.Users.CommonUser;
import entity.Users.User;
import data_access.InMemoryCurrentUserDAO;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Unit test for LogoutInteractor.
 */

public class LogoutInteractorTest {

        private LogoutInteractor logoutInteractor;
        InMemoryCurrentUserDAO inMemoryCurrentUserDAO = new InMemoryCurrentUserDAO();


        @Before
        public void setUp() {
            User testUser =  new CommonUser("username", "123", 20, "m", "test@gmail.com");
            inMemoryCurrentUserDAO.loginCurrentUser(testUser);

        }

        @Test
        public void testExecute() {
            LogoutOutputBoundary logoutOutputBoundary = new LogoutOutputBoundary() {
                @Override // Mock Presenter
                public void prepareView() {

                }
            };

            LogoutCurrentUserDataAccessInterface LogoutCurrentUserDAO = inMemoryCurrentUserDAO;
            logoutInteractor = new LogoutInteractor(logoutOutputBoundary, LogoutCurrentUserDAO);

            // Tests that there is a current user logged in.
            assertNotNull(inMemoryCurrentUserDAO.getCurrentUser());

            // Tests the logoutInteractor logouts the current user.
            logoutInteractor.execute();
            assertNull(inMemoryCurrentUserDAO.getCurrentUser());

        }

}
