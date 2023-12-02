package interface_adapter;

import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class ViewManagerModelAdapter implements PropertyChangeListener {
    private ViewManagerModel viewManagerModel;
    private String previousViewName = " ";
    private Stack<String> lastViews = new Stack<>();
    private ArrayList<String> visitingSequence = new ArrayList<>();

    public ViewManagerModelAdapter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        lastViews.add("home"); // home is the default last view
    }

    public String getLastViewName() {
            return previousViewName;
    }

    /**
     * Set the last active view to be the current view
     *
     * @param lastViewName the last active view
     */
    public void setActiveView(String lastViewName) {
        System.out.println("VMM adapter\nsetActiveView\n");
        System.out.println("last views: " + String.valueOf(lastViews));
        if (!lastViews.isEmpty()) {
            previousViewName = lastViews.pop(); // the most top last view is now active, so pop
            System.out.println("previous view name: " + previousViewName);
            if (previousViewName.equals(lastViewName)) { // we are going back now
                previousViewName = lastViews.pop();
            }
            viewManagerModel.setActiveView(lastViewName, "going back");
            viewManagerModel.firePropertyChanged();
        } else { // no views to go back
            previousViewName = "";
        }
    }

    // The Adapter listens to property change at the ViewManagerModel whenever the ViewManagerModel
    // set a new active view. In this case the Adapter will first be notified to store the ViewModel
    // from which the user is to leave (lastViewName), then let the ViewManagerModel proceeds to do the switch.

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("store current view name")) {
            if (!evt.getNewValue().equals("")) { // not switching from "", which means nothing to store
                String currentView = (String) evt.getNewValue();
                visitingSequence.add(currentView);
                if (!lastViews.contains(currentView)) { // this current view is a new view to the stack, needs to be added
                    previousViewName = currentView;
                    lastViews.add(currentView);
                }
            }
        }
    }

    /**
     * Test that the Adapter listens to the ViewManagerModel correctly.
     * @param args
     */
    public static void main(String[] args) {
        ViewManagerModel viewManagermodel = new ViewManagerModel();
        ViewManagerModelAdapter viewManagerModelAdapter = new ViewManagerModelAdapter(viewManagermodel);
        viewManagermodel.addPropertyChangeListener(viewManagerModelAdapter); // The Adapter listens to the ViewManagerModel

        // test when a view switch is performed outside of the GoBack use case
        viewManagermodel.setActiveView("view 1");
        System.out.println("right now the active view is: view 1\n"+"the last active view is " +
                viewManagerModelAdapter.getLastViewName() + "\n"); // should be null

        viewManagermodel.setActiveView("view 2");
        System.out.println("right now the active view is: view 2\n"+"the last active view is " +
                viewManagerModelAdapter.getLastViewName()+ "\n"); // should be view 1

        viewManagermodel.setActiveView("view 3");
        System.out.println("right now the active view is: view 3\n" +
                "the last active view is " + viewManagerModelAdapter.getLastViewName()+ "\n"); // should be view 2

        System.out.println("the current active view is " + viewManagermodel.getActiveView()+ "\n"); // should be view 3

        System.out.println("\nbacking out to the last view...\n");

        // Go back through the adapter
        viewManagerModelAdapter.setActiveView(viewManagerModelAdapter.getLastViewName());
        System.out.println("the current active view is " + viewManagermodel.getActiveView()); // should be view 2
        System.out.println("the last active view is " + viewManagerModelAdapter.getLastViewName()); // should be view 1

    }
}
