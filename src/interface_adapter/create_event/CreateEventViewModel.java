package interface_adapter.create_event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateEventViewModel extends ViewModel {
    public static final String CREATE_EVENT_BUTTON_LABEL = "Create Event";
    public static final String RETURN_BUTTON_LABEL = "Return";
    public static final String TITLE_LABEL = "Event Creator";
    public static final String EVENT_NAME_LABEL = "Choose the event name";
    public static final String EVENT_TIME_LABEL = "Choose the event time";
    public static final String DESCRIPTION_LABEL = "Event description";
    public static final String PRIVATE_LABEL = "Private?";
    public static final String AGE_RESTRICTION_LABEL = "Age requirement";
    public static final String GENDER_RESTRICTION_LABEL = "Gender requirement";
    public static final String CAPACITY_LABEL = "Capacity restriction";

    private CreateEventState state = new CreateEventState();

    public CreateEventViewModel(String viewName) {
        super(viewName);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateEventState getState(){return state;}
}
