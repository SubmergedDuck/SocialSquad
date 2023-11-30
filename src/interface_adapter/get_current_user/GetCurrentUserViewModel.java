package interface_adapter.get_current_user;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model for the GetCurrentUser use case.
 */
public class GetCurrentUserViewModel extends ViewModel {

    private GetCurrentUserState state = new GetCurrentUserState();

    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);


    public void setState(GetCurrentUserState state){this.state = state;}

    public GetCurrentUserState getState(){return state;}

    public GetCurrentUserViewModel(){super("get current user");}
    @Override
    public void firePropertyChanged() {
        observable.firePropertyChange("state",null,this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);
    }
}
