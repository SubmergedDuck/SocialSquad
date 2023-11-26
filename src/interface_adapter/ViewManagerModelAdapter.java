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
    }

    public String getLastViewName() {
        if (lastViews.isEmpty()) {
            return null; // There is no previous view.
        } else {
            String lastViewName = lastViews.pop(); // store it
            lastViews.add(lastViewName); // put it back
            return lastViewName;

        }
    }

    /**
     * Set the last active view to be the current view
     *
     * @param lastViewName the last active view
     */
    public void setActiveView(String lastViewName) {
        previousViewName = lastViews.pop(); // the most top last view is now active, so pop
//        this.lastViewName = lastViews.pop(); // the next view to go back is the second top on the stack
//        lastViews.add(this.lastViewName); // put it back for now
        if (previousViewName.equals(lastViewName)) {
            previousViewName = lastViews.pop();
        }
        viewManagerModel.setActiveView(lastViewName);
        viewManagerModel.firePropertyChanged();
    }

    // The Adapter listens to property change at the ViewManagerModel whenever the ViewManagerModel
    // set a new active view. In this case the Adapter will first be notified to store the ViewModel
    // from which the user is to leave (lastViewName), then let the ViewManagerModel proceeds to do the switch.

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("store current view name")) {
            if (!evt.getNewValue().equals("")) {
                String currentView = (String) evt.getNewValue();
                visitingSequence.add(currentView);
                if (!visitingSequence.contains(previousViewName)) { // means this is the case that previousView = ""
                    previousViewName = currentView;
                    lastViews.add((String) evt.getNewValue());
                } else if (//visitingSequence.indexOf(currentView) <= visitingSequence.indexOf(previousViewName)
                        !lastViews.contains(currentView) // it is not popped out: it is not a previous view
                ){ // the current view to store is already stored i.e. we are heading back now
                    previousViewName = currentView;
                    lastViews.add((String) evt.getNewValue());

                }
//                if (visitingSequence.indexOf(previousViewName) > visitingSequence.indexOf(currentView)) { // decide if needed to add into last views
//                    previousViewName = (String) evt.getNewValue();
//                    lastViews.add((String) evt.getNewValue());
//                }
//                previousViewName = (String) evt.getNewValue();
//                lastViews.add((String) evt.getNewValue());
                // Add the current view to the visiting sequence first, for possible later comparison
//                if (isEmpty) {
//                    previousViewName = (String) evt.getNewValue();
//                    //visitingSequence.add((String) evt.getNewValue());
//                    lastViews.add((String) evt.getNewValue()); // store the current view name before letting view manager model to go back
                //}
            }

//        } else if (!(visitingSequence.contains(evt.getNewValue()) && visitingSequence.contains(previousViewName))) {
//            previousViewName = (String) evt.getNewValue();
//            visitingSequence.add((String) evt.getNewValue());
//            lastViews.add((String) evt.getNewValue());
//
//        } else { // Need to compare if the new view switched into is a new view or a previous view
//            int currentViewIndex = visitingSequence.indexOf(evt.getNewValue());
//            int previousViewIndex = visitingSequence.indexOf(previousViewName);
//            if (previousViewIndex <= currentViewIndex) {
//                // do nothing, we are heading out, so no point to store this view
//            } else {
//                previousViewName = (String) evt.getNewValue();
//            }

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
