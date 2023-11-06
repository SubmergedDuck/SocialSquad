package use_case.search_event;

import entity.Events.Event;

import java.util.ArrayList;

public class SearchEventOutputData {
    private final boolean eventsExist;
    private final ArrayList<Event> eligibleEvents;

    public SearchEventOutputData (boolean eventsExist, ArrayList<Event> eligibleEvents){
        this.eventsExist = eventsExist;
        this.eligibleEvents = eligibleEvents;
    }

    public ArrayList<Event> getEligibleEvents(){
        return this.eligibleEvents;
    }
}
