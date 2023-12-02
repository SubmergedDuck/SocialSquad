package unused_usecases___.interface_adapter.search_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

import java.beans.PropertyChangeSupport;

public class SearchEventViewModel extends ViewModel {
    public static final String SEARCH_EVENT_BUTTON_LABEL = "Search an event!";
    public static final String TITLE_LABEL = "Search Event View";
    public static final String SEARCH_LABEL = "Type in some thoughts to start with";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private SearchEventState state = new SearchEventState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SearchEventViewModel() {
        super("search event");
    }

    public void setState(SearchEventState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("search created", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }

    public SearchEventState getState() {
        return this.state;
    }
}
