package entity.Users;

public interface UserFactory {

    User create(String username, String password, int age, String sex, String contact);
}
