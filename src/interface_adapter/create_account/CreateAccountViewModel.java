package interface_adapter.create_account;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateAccountViewModel extends ViewModel {
    public CreateAccountViewModel(){super("log in");}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public LoginState state = new LoginState();


    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
