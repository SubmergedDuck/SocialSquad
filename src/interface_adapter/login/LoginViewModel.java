package interface_adapter.login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class LoginViewModel extends ViewModel {
    public LoginViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
