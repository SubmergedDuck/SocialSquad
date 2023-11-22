package use_case.search_nearby;

import entity.Events.Event;

import java.sql.Array;
import java.util.ArrayList;

public class SearchNearbyInteractor implements SearchNearbyInputBoundary{
    SearchNearbyDataAccessInterface dataAccessObject;
    SearchNearbyOutputBoundary presenter;

    public SearchNearbyInteractor(SearchNearbyDataAccessInterface dataAccessObject, SearchNearbyOutputBoundary presenter) {
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
    }
    @Override
    public void execute(SearchNearbyInputData inputData) {
        ArrayList<Event> result = dataAccessObject.getNearbyEvent(inputData);
        if (result.isEmpty()) {
            presenter.prepareFailView();
        } else {
            SearchNearbyOutputData outputData = new SearchNearbyOutputData(false, result);
            presenter.prepareSuccessView(outputData);
        }
    }
}
