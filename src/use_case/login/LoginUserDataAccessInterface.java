package use_case.login;

import entity.Users.CommonUser;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(CommonUser commonUser);

    CommonUser get(String username);
}
