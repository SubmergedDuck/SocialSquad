import interface_adapter.Back_Out.BackOutViewModel;
import interface_adapter.ViewManagerModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BackOutPresenter {
    private final ViewManagerModel viewManagerModel;
    private final BackOutViewModel backOutViewModel;

    public BackOutPresenter(ViewManagerModel viewManagerModel, BackOutViewModel backOutViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.backOutViewModel = backOutViewModel;

        // Subscribe to changes in the active view
        viewManagerModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("activeView".equals(evt.getPropertyName())) {
                    backOutViewModel.firePropertyChanged();
                }
            }
        });
    }

    public void handleBackOutButton() {
        // Implement the logic to go back to the previous view
        String previousView = viewManagerModel.getActiveView();
        if (previousView != null) {
            viewManagerModel.setActiveView(previousView);
        }
    }
}