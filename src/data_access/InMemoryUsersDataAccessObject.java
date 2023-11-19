package data_access;

import entity.Events.Event;
import entity.Users.User;
import interface_adapter.remove_participant.RemoveParticipantViewModel;
//import use_case.create_event.CreateEventDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.remove_participant.RemoveParticipantDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryUsersDataAccessObject implements RemoveParticipantDataAccessInterface {

    private final HashMap<String, User> usernameToUser = new HashMap();

    @Override
    public void removeUser(String username, Integer eventID) {
        User deletedUser = usernameToUser.get(username);
        ArrayList<Event> joinedEvents = deletedUser.getJoinedEvents();

        Event eventRemove = null;
        for (Event event : joinedEvents) {
            if (event.getEventID() == eventID) {
                eventRemove = event;
            }
        }
        joinedEvents.remove(eventRemove);
    }
}

// Commented out duplicate code:
//
//public class InMemoryUsersDataAccessObject implements GetDirectionDataAccessInterface,
//        SearchEventDataAccessInterface,
//        JoinEventDataAccessInterface, RemoveParticipantDataAccessInterface {
//
//    private final HashMap<String, User> usernameToUser = new HashMap();
//
//    @Override
//    public void removeUser(String username, Integer eventID) {
//        User deletedUser = usernameToUser.get(username);
//        ArrayList<Event> joinedEvents = deletedUser.getJoinedEvents();
//
//        Event eventRemove = null;
//        for (Event event : joinedEvents){
//            if (event.getEventID() == eventID){
//                eventRemove = event;
//            }
//        }
//        joinedEvents.remove(eventRemove);
//    }
//}
