package entity.Users;

import entity.Location.Location;

public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String username, String password, int age, String sex, String contact) {
        return new CommonUser(username, password, age, sex, contact);
    }
}
