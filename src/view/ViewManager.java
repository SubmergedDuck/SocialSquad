package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Stack;

public class ViewManager implements PropertyChangeListener {
    private Stack<String> viewHistory = new Stack<>();
    private final CardLayout cardLayout;
    private final JPanel views;

    private String currentView;

    private String previousView;

    private ViewManagerModel viewManagerModel;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }
    public void switchToPreviousView() {
        if(!viewHistory.isEmpty()){
            String previousView = viewHistory.pop();
            // Implement logic to switch to the previous view, e.g., update UI, etc.
            // You may also need to store the current view before switching to the previous view.

            currentView = previousView;
        }
        // Implement logic to switch to the previous view
    }
    public String getCurrentView(){
        return currentView;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }
    }
}
