package interface_adapter.join_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class JoinEventViewModel extends ViewModel {
    public JoinEventViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
