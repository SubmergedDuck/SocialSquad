package interface_adapter;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {
    private String activeViewName = "";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        return activeViewName;
    }

    public void setActiveView(String newActiveView) {
        support.firePropertyChange("store current view name", null, this.activeViewName); // Calls the Adapter to store the current activeViewName as lastViewName.
        this.activeViewName = newActiveView;
    }

    public void setActiveView(String activeViewName, String description) {
        if (description.equals("going back")) {
            this.activeViewName = activeViewName; // don't record the view in the lastViews stack at the adapter
        }
    }

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
