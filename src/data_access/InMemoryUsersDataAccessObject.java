package data_access;

import entity.Events.Event;
import entity.Users.User;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryUsersDataAccessObject implements GetDirectionDataAccessInterface,
        SearchEventDataAccessInterface,
        JoinEventDataAccessInterface {

    private final HashMap<String, User> usernameToUser = new HashMap();
}
