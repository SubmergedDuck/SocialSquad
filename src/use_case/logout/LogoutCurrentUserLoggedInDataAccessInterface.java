package use_case.logout;

import entity.Users.User;

public interface LogoutCurrentUserLoggedInDataAccessInterface {

    /**
     * A public method that logs out the current user.
     */
    void logoutCurrentUser();

}
