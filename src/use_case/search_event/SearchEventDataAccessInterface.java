package use_case.search_event;

import entity.Events.Event;

import java.util.ArrayList;

public interface SearchEventDataAccessInterface {
    /**
     * The data access interface class for the SearchEvent use case.
     */

    /**
     * A public method that compares search request with nameToEvent directory and returns events that completely match
     * with the search request
     * @param inputData The search request
     * @return An ArrayList of events whose names are completely the same with the search message
     */
    public ArrayList<Event> getFullMatchEvents (SearchEventInputData inputData);

    /**
     * A public method that compares search request with nameToEvent directory and returns events that are a partial match
     * @param inputData The search request
     * @return An ArrayList of events that partially match with the search request either by name or by type.
     */
    public ArrayList<Event> getPartialMatchEvents (SearchEventInputData inputData);
}
