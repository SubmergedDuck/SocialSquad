package use_case.loggedIn;

import entity.Users.User;

public interface LoggedInUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
