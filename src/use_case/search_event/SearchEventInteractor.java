package use_case.search_event;

import entity.Events.Event;

import java.util.ArrayList;

public class SearchEventInteractor implements SearchEventInputBoundary{
    /**
     * The interactor class for the SearchEvent use case.
     */
    final SearchEventDataAccessInterface searchEventDAO;
    final SearchEventOutputBoundary presenter;

    /**
     * The constructor method for a SearchEventInteractor object.
     * @param searchEventDAO The data access object that the interactor will call to complete its task.
     * @param presenter The output data boundary object that the interactor will send its result to.
     */

    public SearchEventInteractor(SearchEventDataAccessInterface searchEventDAO, SearchEventOutputBoundary presenter) {
        this.searchEventDAO = searchEventDAO;
        this.presenter = presenter;
    }

    /**
     * A public method that calls the interactor to execute with the search request. The interactor will gather Events
     * that fully or partially match with the search request through the DAO. After this it will determine whether the
     * search is success. A success search finds matching events (either fully matched or partially). If no matching
     * events are find, the search is deemed failed. In each of the both cases, it will call on different methods of the
     * presenter.
     *
     * @param inputData represents the search request.
     */
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
