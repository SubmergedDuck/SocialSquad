package interface_adapter.get_ids;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetIDsViewModel extends ViewModel {

    private GetIDsState state = new GetIDsState();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public GetIDsViewModel(){super("get ids");}

    public GetIDsState getState(){return this.state;}

    public void setState(GetIDsState state){this.state = state;}
    @Override
    public void firePropertyChanged() {
        observable.firePropertyChange("state", null, this.state);

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);
    }
}
