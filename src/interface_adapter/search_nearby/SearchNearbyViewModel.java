package interface_adapter.search_nearby;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchNearbyViewModel extends ViewModel {
    public final String TITLE_LABEL = "Search Nearby View";
    public static final String BACK_BUTTON_LABEL = "Back";
    private SearchNearbyState state = new SearchNearbyState();

    public SearchNearbyViewModel() {
        super("search nearby");
    }

    public void setState(SearchNearbyState state) {
        this.state = state;
    }

    public SearchNearbyState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("search event", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }
}
