package interface_adapter.create_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class CreateEventViewModel extends ViewModel {
    public CreateEventViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
