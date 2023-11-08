package entity.Users;

import entity.Events.Event;
import entity.Location.Location;

import java.util.ArrayList;

public interface UserFactory {

    User create(String username, String password, int age, String sex, String realName, String contact);
}
