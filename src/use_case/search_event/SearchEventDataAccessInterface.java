package use_case.search_event;

import entity.Events.Event;

import java.util.ArrayList;

public interface SearchEventDataAccessInterface {
    public ArrayList<Event> getFullMatchEvents (SearchEventInputData inputData);
    public ArrayList<Event> getPartialMatchEvents (SearchEventInputData inputData);
}
