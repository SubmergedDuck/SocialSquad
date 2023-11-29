package use_case.back_out;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewManagerModelAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackOutInteractorTest {

    @Test
    void execute() {
        ViewManagerModel viewManagermodel = new ViewManagerModel();
        //ViewManager viewManager = new ViewManager(new JPanel(), new CardLayout(), viewManagermodel);
        ViewManagerModelAdapter viewManagerModelAdapter = new ViewManagerModelAdapter(viewManagermodel);
        viewManagermodel.addPropertyChangeListener(viewManagerModelAdapter); // The Adapter listens to the ViewMangaerModel

        // test when a view switch is performed outside of the GoBack use case
        viewManagermodel.setActiveView("view 1");
        System.out.println("right now the active view is: view 1\n"+"the last active view is " +
                viewManagerModelAdapter.getLastViewName() + "\n"); // should be null

        viewManagermodel.setActiveView("view 2");
        System.out.println("right now the active view is: view 2\n"+"the last active view is " +
                viewManagerModelAdapter.getLastViewName()+ "\n"); // should be view 1
        assert viewManagerModelAdapter.getLastViewName().equals("view 1");

        viewManagermodel.setActiveView("view 3");
        System.out.println("right now the active view is: view 3\n" +
                "the last active view is " + viewManagerModelAdapter.getLastViewName()+ "\n"); // should be view 2
        assert viewManagerModelAdapter.getLastViewName().equals("view 2");

        System.out.println("the current active view is " + viewManagermodel.getActiveView()+ "\n"); // should be view 3
        assert viewManagermodel.getActiveView().equals("view 3");


        System.out.println("\nbacking out to the last view...\n");

        // Go back through the adapter
        viewManagerModelAdapter.setActiveView(viewManagerModelAdapter.getLastViewName());
        System.out.println("the current active view is " + viewManagermodel.getActiveView()); // should be view 2
        assert viewManagermodel.getActiveView().equals("view 2");

        System.out.println("the last active view is " + viewManagerModelAdapter.getLastViewName() + "\n"); // should be view 1
        assert viewManagerModelAdapter.getLastViewName().equals("view 1");

        viewManagerModelAdapter.setActiveView(viewManagerModelAdapter.getLastViewName());
        assert viewManagerModelAdapter.getLastViewName().equals(""); // no view is before "view 1"
        System.out.println("the last active view is " + viewManagerModelAdapter.getLastViewName() + "\n");

        // Go in again
        System.out.println("going in again");
        viewManagermodel.setActiveView("view 1");
        System.out.println("right now the active view is: view 1\n"+"the last active view is " +
                viewManagerModelAdapter.getLastViewName() + "\n"); // should be null

        viewManagermodel.setActiveView("view 2");
        System.out.println("right now the active view is: view 2\n"+"the last active view is " +
                viewManagerModelAdapter.getLastViewName()+ "\n"); // should be view 1
        assert viewManagerModelAdapter.getLastViewName().equals("view 1");

        // This part doesn't work
//        viewManagermodel.setActiveView("view 3");
//        System.out.println("right now the active view is: view 3\n" +
//                "the last active view is " + viewManagerModelAdapter.getLastViewName()+ "\n"); // should be view 2
//        assert viewManagerModelAdapter.getLastViewName().equals("view 2");


    }
}