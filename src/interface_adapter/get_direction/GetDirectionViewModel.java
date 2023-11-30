package interface_adapter.get_direction;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetDirectionViewModel extends ViewModel {

    private GetDirectionState state = new GetDirectionState();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public GetDirectionViewModel() {
        super("get direction");
    }

    public GetDirectionState getState(){return state;}

    public void setState(GetDirectionState state){this.state = state;}

    @Override
    public void firePropertyChanged() {
        observable.firePropertyChange("get direction",null,this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);
    }
}
