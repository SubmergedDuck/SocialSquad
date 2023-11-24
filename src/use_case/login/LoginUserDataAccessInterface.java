package use_case.login;

import entity.Users.CommonUser;
import entity.Users.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User User);

    User get(String username);
}
