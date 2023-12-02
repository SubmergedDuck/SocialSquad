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
        if (state.getIsDisplayed()) { // Means Create Event button is clicked to set the view active
            System.out.println("you are going to create event view");
            viewManagerModel.setActiveView("create event");
            viewManagerModel.firePropertyChanged();
        } else {
            System.out.println("creating an event");
            observable.firePropertyChange("create event", null, this.state);
        }

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        observable.addPropertyChangeListener(listener);

    }
}
