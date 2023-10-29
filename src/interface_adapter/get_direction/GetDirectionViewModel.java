package interface_adapter.get_direction;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class GetDirectionViewModel extends ViewModel {
    public GetDirectionViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
