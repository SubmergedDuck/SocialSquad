package use_case.get_current_user;

import entity.Users.User;

public interface CurrentUserDataAccessInterface {
    void loginCurrentUser(User user);

    void logoutCurrentUser();
    User getCurrentUser();
}
