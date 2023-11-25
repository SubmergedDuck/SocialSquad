package use_case.search_nearby;

import entity.Events.Event;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchNearbyOutputData {
    public final boolean usecaseFailed;
    private final ArrayList<Event> events;

    public SearchNearbyOutputData(boolean usecaseFailed, ArrayList<Event> events) {
        this.usecaseFailed = usecaseFailed;
        this.events = events;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}
