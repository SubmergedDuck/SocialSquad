package data_access;

import entity.Events.Event;
import use_case.remove_participant.RemoveParticipantDataAccessInterface;

import entity.Location.Location;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;
import use_case.search_event.SearchEventInputData;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryEventsDataAccessObject implements SearchEventDataAccessInterface, RemoveParticipantDataAccessInterface{
    /**
     * This is an in-memory event DAO to allow testing with the SearchEvent use case interactor.
     */
    private final Map<String, Event> nameToEvents = new HashMap<>();
    private final Map<Integer, Event> EventstoID = new HashMap<>();


    public InMemoryEventsDataAccessObject() {
        // constructor implementation
        ;
    }

    /**
     * A public method that saves an event to the nameToEvent hashmap directory
     * @param event The event to be saved
     */
    public void save(Event event){
        nameToEvents.put(event.getEventName(), event);
        eventstoID.put(event.getEventID(), event);
    }
    
    
    /**
     * Provides the event id to event map of the DAO.
     * @return the event id to event map.
     */
    public Map<Integer,Event> getEventMap(){
        return this.EventstoID;
    }

    /**
     * A public method that compares search request with nameToEvent directory and returns events that completely match
     * with the search request
     * @param inputData The search request
     * @return An ArrayList of events whose names are completely the same with the search message
     */
    @Override
    public ArrayList<Event> getFullMatchEvents(SearchEventInputData inputData) {
        ArrayList<Event> returnList = new ArrayList<>();
        ArrayList<String> eventNames = new ArrayList<>(nameToEvents.keySet());
        for (String name: eventNames) {
            if (name.equals(inputData.getSearchRequest())) { //When the name of the event is a perfect match to the search request message
                returnList.add(nameToEvents.get(name));
            }
        }

        return returnList;
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

    /**
     * A public method that compares search request with nameToEvent directory and returns events that are a partial match
     * @param inputData The search request
     * @return An ArrayList of events that partially match with the search request either by name or by type.
     */
    @Override
    public ArrayList<Event> getPartialMatchEvents(SearchEventInputData inputData) {
        ArrayList<Event> returnList = new ArrayList<>();
        String[] keywords = inputData.getSearchRequest().split(" "); // Break down the search request into keywords to compare
        ArrayList<String> eventNames = new ArrayList<>(nameToEvents.keySet());
        for (String name: eventNames) {
            Event event = nameToEvents.get(name);
            for (String keyword: keywords) {
                if (name.equals(keyword) || event.getType().equals(keyword)){ // if name or type matches keyword by once, it counts as a partial match and will be returned
                    returnList.add(nameToEvents.get(name));
                }
                if (nameToEvents.get(name).getType().equals(keyword)){
                    returnList.add(nameToEvents.get(name));
                }
            }
        }
        // remove full match event from the list
        ArrayList<Event> fullMatchEvents = getFullMatchEvents(inputData);
        for (Event event: fullMatchEvents) {
            if (returnList.contains(event)) {
                returnList.remove(event);
            }
        }


        return returnList;
    }
}
