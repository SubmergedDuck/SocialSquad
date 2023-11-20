package interface_adapter.Back_Out;

import interface_adapter.ViewModel;
import interface_adapter.logged_in.LoggedInState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BackOutViewModel {
    private BackOutState state = new BackOutState();
    public void setState(BackOutState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
