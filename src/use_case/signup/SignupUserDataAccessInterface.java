package use_case.signup;
import entity.Users.User;

public interface SignupUserDataAccessInterface {

    boolean existsByName(String identifier);

    void save(User user);
}
