package interface_adapter.get_event_details;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetEventDetailsViewModel extends ViewModel {

    private GetEventDetailsState state = new GetEventDetailsState();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public GetEventDetailsViewModel() {
        super("get event details");
    }

    public void setState(GetEventDetailsState state) {
        this.state = state;
    }

    public GetEventDetailsState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        observable.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);

    }
}
