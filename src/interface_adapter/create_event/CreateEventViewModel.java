package interface_adapter.create_event;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import view.CreateEventView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateEventViewModel extends ViewModel {
    public final String TITLE_LABEL = "Create Event View";
    private CreateEventState state = new CreateEventState();
    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public CreateEventViewModel() {
        super("create event");
    }

    public void setState(CreateEventState state) {
        this.state = state;
    }

    public CreateEventState getState() {
        return this.state;
    }

    @Override
    public void firePropertyChanged() {
        observable.firePropertyChange("create event", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);

    }
}
