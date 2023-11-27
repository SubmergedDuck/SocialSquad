package interface_adapter.join_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class JoinEventViewModel extends ViewModel {
    private JoinEventState state = new JoinEventState();
    private PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public JoinEventViewModel(String viewName) {
        super(viewName);
    }

    public void setState(JoinEventState state) {
        this.state = state;
    }

    public JoinEventState getState() {
        return this.state;
    }

    @Override
    public void firePropertyChanged() {
        observable.firePropertyChange("join event", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);

    }
}
