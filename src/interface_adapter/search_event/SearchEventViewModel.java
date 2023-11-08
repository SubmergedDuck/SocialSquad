package interface_adapter.search_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class SearchEventViewModel extends ViewModel {
    public SearchEventViewModel(String viewName) {
        super(viewName);
    }

    public SearchEventState getState(){
        return null;
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
