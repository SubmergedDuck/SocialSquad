package use_case.search_event;

import entity.Events.Event;

import java.util.ArrayList;

public class SearchEventOutputData {
    /**
     * The output data class for the SearchEvent use case.
     */
    private final ArrayList<Event> completeMatch;
    private final ArrayList<Event> partialMatch;

    /**
     * The constructor call for a SearchEventOutputData object.
     * @param completeMatch An ArrayList of events that completely match with the search request by name.
     * @param partialMatch An ArrayList of events that partially match with the search request by name or by type.
     */

    public SearchEventOutputData(ArrayList<Event> completeMatch, ArrayList<Event> partialMatch) {
        this.completeMatch = completeMatch;
        this.partialMatch = partialMatch;
    }

    /**
     * A public getter method for the complete matching events.
     * @return An ArrayList of events that are complete matches to the search request.
     */

    public ArrayList<Event> getCompleteMatch(){
        return completeMatch;
    }

    /**
     * A public getter method for the partially matching events.
     * @return An ArrayList of events that are partial matches to the search request.
     */

    public ArrayList<Event> getPartialMatch() {
        return partialMatch;
    }
}
