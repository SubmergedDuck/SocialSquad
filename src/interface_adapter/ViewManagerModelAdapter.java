package interface_adapter;

import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManagerModelAdapter implements PropertyChangeListener {
    private ViewManagerModel viewManagerModel;
    private String lastViewName;

    public ViewManagerModelAdapter(ViewManagerModel viewManagerModel, String lastViewName) {
        this.viewManagerModel = viewManagerModel;
        this.lastViewName = lastViewName;
    }
     public String getLastViewName() {
        return lastViewName;
     }

    public void setActiveView(String lastViewName) {
        viewManagerModel.setActiveView(lastViewName);
        viewManagerModel.firePropertyChanged();
    }

    // The Adapter listens to property change at the ViewManagerModel whenever the ViewManagerModel
    // set a new active view. In this case the Adapter will first be notified to store the ViewModel
    // from which the user is to leave (lastViewName), then let the ViewManagerModel proceeds to do the switch.

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("store last view name")) {
            this.lastViewName = (String) evt.getNewValue();
        }
    }

    /**
     * Test that the Adapter listens to the ViewManagerModel correctly.
     * @param args
     */
    public static void main(String[] args) {
        ViewManagerModel viewManagermodel = new ViewManagerModel();
        //ViewManager viewManager = new ViewManager(new JPanel(), new CardLayout(), viewManagermodel);
        ViewManagerModelAdapter viewManagerModelAdapter = new ViewManagerModelAdapter(viewManagermodel, null);
        viewManagermodel.addPropertyChangeListener(viewManagerModelAdapter); // The Adapter listens to the ViewMangaerModel

        // test when a view switch is performed
        viewManagerModelAdapter.setActiveView("view 1");
        System.out.println("the last active view is " + viewManagerModelAdapter.getLastViewName()); // should be null

        viewManagermodel.setActiveView("view 2");
        System.out.println("the last active view is " + viewManagerModelAdapter.getLastViewName()); // should be view 1

        viewManagermodel.setActiveView("view 3");
        System.out.println("the last active view is " + viewManagerModelAdapter.getLastViewName()); // should be view 2

        System.out.println("the current active view is " + viewManagermodel.getActiveView()); // should be view 3

        System.out.println("\nbacking out to the last view...\n");

        viewManagerModelAdapter.setActiveView(viewManagerModelAdapter.getLastViewName());
        System.out.println("the current active view is " + viewManagermodel.getActiveView()); // should be view 2
        System.out.println("the last active view is " + viewManagerModelAdapter.getLastViewName()); // should be view 3

    }
}
