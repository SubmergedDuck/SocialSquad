package data_access;

import entity.Events.Event;
import use_case.remove_participant.RemoveParticipantDataAccessInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryEventsDataAccessObject implements RemoveParticipantDataAccessInterface {
    private final Map<Integer, Event> EventstoID = new HashMap<>();
    //NOTE: Switched the key to being the event ID since that's how we identify events.
    public Map<Integer,Event> getEventMap(){
        return this.EventstoID;
    }

    /**
     * Removes a user from an event's arraylist of joined users.
     * @param username username of the deleted user
     * @param eventID the id of the event that the user is being removed from
     */
    @Override
    public void removeUser(String username, Integer eventID) {
        Event event = EventstoID.get(eventID);
        ArrayList<String> joinedUsernames = event.getPeopleJoined();
        joinedUsernames.remove(username);
    }
}