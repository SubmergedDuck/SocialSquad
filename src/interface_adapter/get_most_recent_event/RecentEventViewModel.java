package interface_adapter.get_most_recent_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecentEventViewModel extends ViewModel {

    private RecentEventState state = new RecentEventState();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public RecentEventViewModel() {
        super("recent event");
    }

    public void setState(RecentEventState state){this.state = state;}

    public RecentEventState getState(){return state;}

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
