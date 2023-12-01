package interface_adapter.create_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateEventViewModel extends ViewModel {

    private CreateEventState state = new CreateEventState();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public CreateEventViewModel() {
        super("create event");
    }

    public CreateEventState getState(){return this.state;}

    public void setState(CreateEventState state){this.state = state;}

    @Override
    public void firePropertyChanged() {
        observable.firePropertyChange("create event",null,this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);
    }
}
