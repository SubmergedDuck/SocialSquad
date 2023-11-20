package interface_adapter;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

public class ViewManagerModel {
    private String activeViewName;
    private final Stack<String> viewHistory = new Stack<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        if (!viewHistory.isEmpty()) {
            return viewHistory.peek();
        }
        return null;
    }
    public String getPreviousView() {
        // Get the previous view from the history stack
        if (viewHistory.size() > 1) {
            viewHistory.pop(); // Pop the current view
            return viewHistory.pop(); // Return the previous view
        }
        return null;
    }

    public void setActiveView(String activeView) {
        String oldView = getActiveView();
        if (oldView != null) {
            viewHistory.push(activeView);
            support.firePropertyChange("activeView", oldView, activeView);
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
