package unused_usecases___.interface_adapter.search_event;

import entity.Events.Event;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ShowEventViewModel extends ViewModel {

    public static final String TITLE_LABEL = "List of events found";

    private ArrayList<Event> completeMatches;
    private ArrayList<Event> partialMatches;
    private ShowEventState state = new ShowEventState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ShowEventViewModel(ArrayList<Event> completeMatches, ArrayList<Event> partialMatches) {
        super("Show events found");
        this.completeMatches = completeMatches;
        this.partialMatches = partialMatches;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("show events", null, this.state);



    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }

    public ShowEventState getState() {
        return this.state;
    }

    public void setState(ShowEventState showEventState) {
        this.state = showEventState;
    }
}
