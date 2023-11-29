package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {
    private String viewName;
    private String previousViewName;
    public String getPreviousViewName() {
        return previousViewName;
    }
    public void setPreviousViewName(String previousViewName) {
        this.previousViewName = previousViewName;
    }

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}
