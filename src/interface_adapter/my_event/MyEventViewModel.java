package interface_adapter.my_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MyEventViewModel extends ViewModel {
    private MyEventState state = new MyEventState();

    public  MyEventViewModel(){super("My Events");}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state",null,this.state);

    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);

    }

    public MyEventState getState() { return state;
    }
    public void setState(MyEventState state){this.state = state;}
}
