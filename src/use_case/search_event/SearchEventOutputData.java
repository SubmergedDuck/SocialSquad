package use_case.search_event;

import entity.Events.Event;

import java.util.ArrayList;

public class SearchEventOutputData {
    private final ArrayList<Event> completeMatch;
    private final ArrayList<Event> partialMatch;

    public SearchEventOutputData(ArrayList<Event> completeMatch, ArrayList<Event> partialMatch) {
        this.completeMatch = completeMatch;
        this.partialMatch = partialMatch;
    }

    public ArrayList<Event> getCompleteMatch(){
        return completeMatch;
    }

    public ArrayList<Event> getPartialMatch() {
        return partialMatch;
    }
}
