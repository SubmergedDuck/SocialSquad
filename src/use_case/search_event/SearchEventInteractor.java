package use_case.search_event;

import entity.Events.Event;

import java.util.ArrayList;

public class SearchEventInteractor implements SearchEventInputBoundary{
    final SearchEventDataAccessInterface searchEventDAO;
    final SearchEventOutputBoundary presenter;

    public SearchEventInteractor(SearchEventDataAccessInterface searchEventDAO, SearchEventOutputBoundary presenter) {
        this.searchEventDAO = searchEventDAO;
        this.presenter = presenter;
    }

    @Override
    public void execute(SearchEventInputData inputData) {
        ArrayList<Event> fullMatch = searchEventDAO.getFullMatchEvents(inputData);
        ArrayList<Event> partialMartch = searchEventDAO.getPartialMatchEvents(inputData);
        SearchEventOutputData outputData = new SearchEventOutputData(fullMatch, partialMartch);
        if (!(fullMatch.isEmpty() && partialMartch.isEmpty())) { //If any events (fully matched or partially matched are returned by DAO, the search is success
            presenter.prepareSuccessView(outputData);
        } else {
            presenter.prepareFailView();
        }

    }
}
