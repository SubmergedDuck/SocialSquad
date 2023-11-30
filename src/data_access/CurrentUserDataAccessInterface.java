package data_access;

import entity.Users.User;

public interface CurrentUserDataAccessInterface {
    void loginCurrentUser(User user);

    void logoutCurrentUser();
    User getCurrentUser();
}
