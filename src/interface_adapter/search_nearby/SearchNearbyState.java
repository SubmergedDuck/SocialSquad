package interface_adapter.search_nearby;

import entity.Events.Event;

import java.sql.Array;
import java.util.ArrayList;

public class SearchNearbyState {
    private ArrayList<Event> eventsSearched = new ArrayList<Event>();
    private boolean noEventsFound = false;

    public SearchNearbyState(SearchNearbyState copy) {
        eventsSearched = copy.eventsSearched;
        noEventsFound = copy.noEventsFound;
    }

    public SearchNearbyState() {
    }

    public ArrayList<Event> getEventsSearched() {
        return eventsSearched;
    }

    public void setEventsSearched(ArrayList<Event> eventsSearched) {
        this.eventsSearched = eventsSearched;
    }

    public boolean getNoEventsFound() {
        return noEventsFound;
    }

    public void setNoEventsFound() {
        this.noEventsFound = true;
    }
}
