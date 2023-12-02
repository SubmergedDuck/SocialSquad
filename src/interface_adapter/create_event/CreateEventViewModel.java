package interface_adapter.create_event;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import view.CreateEventView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateEventViewModel extends ViewModel {
    public final String TITLE_LABEL = "Create Event View";
    private final ViewManagerModel viewManagerModel;
    private CreateEventState state = new CreateEventState();
    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    public CreateEventViewModel(ViewManagerModel viewManagerModel) {
        super("create event");
        this.viewManagerModel = viewManagerModel;
    }

    public void setState(CreateEventState state) {
        this.state = state;
    }

    public CreateEventState getState() {
        return this.state;
    }

    @Override
    public void firePropertyChanged() {
        if (state.getIsDisplayed() && !state.hasBeenDisplayed) { // Means Create Event button is clicked to set the view active
            System.out.println("VM");
            System.out.println("current state:" + String.valueOf(state.getIsDisplayed()) + "\n");
            System.out.println("create event VM: you are going to create event view\n");
            viewManagerModel.setActiveView("create event");
            viewManagerModel.firePropertyChanged();
            state.hasBeenDisplayed = true;
        } else {
            System.out.println("VM: creating an event\n");
            observable.firePropertyChange("create event", null, this.state);
        }

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);

    }
}
