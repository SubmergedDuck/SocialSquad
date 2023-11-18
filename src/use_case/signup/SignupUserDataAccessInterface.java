package use_case.signup;
import entity.Users.User;

public interface SignupUserDataAccessInterface {

    void save(User user);

    boolean existsByName(String identifier);
}
