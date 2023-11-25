package use_case.search_nearby;

import entity.Events.Event;

import java.util.ArrayList;

public interface SearchNearbyDataAccessInterface {
    public ArrayList<Event> getNearbyEvent(SearchNearbyInputData inputData) throws Exception;
}
