package view;

import data_access.InMemoryEventsDataAccessObject;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.CoordinatesFromIP;
import entity.Location.Location;
import interface_adapter.ViewManagerModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.join_event.JoinEventPresenter;
import interface_adapter.search_nearby.SearchNearbyController;
import interface_adapter.search_nearby.SearchNearbyPresenter;
import interface_adapter.search_nearby.SearchNearbyState;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import use_case.join_event.JoinEventOutputBoundary;
import use_case.search_nearby.SearchNearbyInteractor;
import use_case.search_nearby.SearchNearbyOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public class SearchNearbyBasicView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "search nearby";
    private final SearchNearbyViewModel searchNearbyViewModel;

    private final SearchNearbyController searchNearbyController;

    // private final GoBackController TODO: GoBack should be implemented as a use case
    private final JoinEventController joinEventController;
    private EventLoader eventLoader = new EventLoader(this); // This JPane will give the View event details and a list of events searched

    final JButton back; // This button will

    final JButton search; // This button will trigger the program to search again.
    public SearchNearbyBasicView(SearchNearbyViewModel searchNearbyViewModel, SearchNearbyController searchNearbyController,
                            JoinEventController joinEventController) {
        this.searchNearbyViewModel = searchNearbyViewModel;
        this.searchNearbyController = searchNearbyController;
        //TODO BackButtonController should be added to here

        this.joinEventController = joinEventController;

        eventLoader.setVisible(false);

        this.searchNearbyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Search nearby event");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton("Back");
        search = new JButton("Search");

        buttons.add(back);
        buttons.add(search);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(back)) {
                            // TODO: Back use case is required
                        }
                    }
                }
        );

        search.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String[] coordinates = CoordinatesFromIP.getCoordinates();
                            CommonLocationFactory locationFactory = new CommonLocationFactory();
                            Location location = locationFactory.makeLocation("("+coordinates+")");
                            searchNearbyController.execute(location);
                        } catch (IOException ex) {
                            throw new RuntimeException("Failed to obtain user's coordinate.");
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
        );

        this.add(title);
        this.add(buttons);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ; // NO need to do anything since we have specific ActionListeners for each button.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            SearchNearbyState state = (SearchNearbyState) evt.getNewValue();
            if (state.getNoEventsFound()) {
                JOptionPane.showMessageDialog(this, "No events are found within 2KM from you.");

            } else {
                ArrayList<Event> eventsFound = state.getEventsSearched();
                /**
                 * Swing tutorial on Oracle
                 * JList: Writing a Custom Cell Renderer
                 * Display as a single string first
                 * Make View has EventSearched JPanel that displays JList of events, and JButton ViewDetails, set invisible
                 * populateJlist(Arraylist of Event)
                 * have a class A that extends JPanel that has .populateJlist()
                 * have an instance of A (itself a JPanel), when it's called to popualteJlist, it becomes a JList
                 * with ViewDetails button and Jlist loaded with events. Set it visible.
                 */
                eventLoader.load(eventsFound);
                eventLoader.setVisible(true);
            }
        } else if (evt.getPropertyName().equals("View event details")) { //When an event is selected to view the details
            // TODO: add event detail controller
            // eventDetailController.execute(evt.getNewValue()); // Execute EventDetails use case

        }

    }

    public static void main(String[] args) {
        SearchNearbyViewModel viewModel = new SearchNearbyViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        InMemoryEventsDataAccessObject inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();
        SearchNearbyOutputBoundary presenter = new SearchNearbyPresenter(viewModel, viewManagerModel);
        SearchNearbyInteractor interactor = new SearchNearbyInteractor(inMemoryEventsDataAccessObject, presenter);
        SearchNearbyController searchNearbyController = new SearchNearbyController(interactor);

        // JoinEventOutputBoundary presenter2 = new JoinEventPresenter();

        // SearchNearbyView view = new SearchNearbyView(viewModel, searchNearbyController, joinEventController);
    }
}
