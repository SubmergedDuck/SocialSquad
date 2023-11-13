package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {
    private String viewName;

    private String lastViewName;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }
    public String getLastViewName(){return this.lastViewName;}

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}
