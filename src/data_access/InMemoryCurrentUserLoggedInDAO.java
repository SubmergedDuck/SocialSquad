package data_access;

import entity.Users.User;

// TODO: Class should implement interfaces in:
//  LoginUseCase, LogoutUseCase, JoinEventUseCase, LeaveEventUseCase, CreateEventUseCase, & GetDirectionUseCase
public class InMemoryCurrentUserLoggedInDAO {
    private User currentUser;

    /**
     * A public method that saves the current user that is logged in.
     * @param user The user that is currently logged in.
     */
    public void loginCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * A public method that logs out the current user.
     */
    public void logoutCurrentUser() {
        this.currentUser = null;
    }

    /**
     * A public method that returns the current user that is logged in.
     * @return The current user that is logged in.
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

}
