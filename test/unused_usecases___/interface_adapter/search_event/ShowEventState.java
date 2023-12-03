package unused_usecases___.interface_adapter.search_event;

import entity.Events.Event;

import java.util.ArrayList;

public class ShowEventState {
    private ArrayList<Event> completeMatches = null;
    private ArrayList<Event> partialMatches = null;

    public ShowEventState() {
        ;
    }

    public void setEventDisplayed(ArrayList<Event> completeMatches, ArrayList<Event> partialMatches) {
        this.completeMatches = completeMatches;
        this.partialMatches = partialMatches;
    }
}
