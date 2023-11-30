package data_access;

import entity.Users.User;
import use_case.logout.LogoutCurrentUserDataAccessInterface;

// TODO: Class should implement interfaces in:
//  LoginUseCase, LogoutUseCase, JoinEventUseCase, LeaveEventUseCase, CreateEventUseCase, & GetDirectionUseCase
public class InMemoryCurrentUserDAO implements CurrentUserDataAccessInterface, LogoutCurrentUserDataAccessInterface {
    private User currentUser;

    /**
     * A public method that saves the current user that is logged in.
     * @param user The user that is currently logged in.
     */
    @Override
    public void loginCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * A public method that logs out the current user.
     */
    @Override
    public void logoutCurrentUser() {
        this.currentUser = null;
    }

    /**
     * A public method that returns the current user that is logged in.
     * @return The current user that is logged in.
     */
    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

}
