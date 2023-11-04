package use_case.signup;
import entities

public interface SignupUserDataAccessInterface {

    boolean existsByName(String identifier);

    void save(User user);
}
