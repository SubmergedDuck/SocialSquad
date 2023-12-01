package use_case.loggedIn;

import entity.Users.User;

public interface LoggedInUserDataAccessInterface {

    void save(User user);

    User get(String username);
}
