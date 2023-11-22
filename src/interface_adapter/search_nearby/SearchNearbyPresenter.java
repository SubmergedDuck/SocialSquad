package interface_adapter.search_nearby;

import entity.Events.Event;
import interface_adapter.ViewManagerModel;
import use_case.search_nearby.SearchNearbyOutputBoundary;
import use_case.search_nearby.SearchNearbyOutputData;

import java.util.ArrayList;

public class SearchNearbyPresenter implements SearchNearbyOutputBoundary {
    private final SearchNearbyViewModel searchNearbyViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchNearbyPresenter(SearchNearbyViewModel searchNearbyViewModel, ViewManagerModel viewManagerModel) {
        this.searchNearbyViewModel = searchNearbyViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchNearbyOutputData outputData) {
        // On success, present the events in a list
        ArrayList<Event> eventSearched = outputData.getEvents();

        SearchNearbyState searchNearbyState = searchNearbyViewModel.getState();
        searchNearbyState.setEventsSearched(eventSearched);
        this.searchNearbyViewModel.setState(searchNearbyState);
        searchNearbyViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
        // On failure, the presenter will prepare a pop up box
        SearchNearbyState searchNearbyState = searchNearbyViewModel.getState();
        searchNearbyState.setNoEventsFound(); // Set State to indicate no event has found in search.
        this.searchNearbyViewModel.setState(searchNearbyState);
        searchNearbyViewModel.firePropertyChanged();

    }
}
