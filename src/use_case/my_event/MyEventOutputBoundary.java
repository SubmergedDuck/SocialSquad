package use_case.my_event;

import use_case.search_event.SearchEventOutputData;

public interface MyEventOutputBoundary {
    /**
     * The output boundary interface for the MyEvent use case, used by the interactor and presenter.
     */

    /**
     * A public method that lets the presenter generates a success view after the interactor returning the search result,
     * in the form of the outputData.
     *
     * @param outputData The result returned by the interactor, it contains events that are fully or partially matched
     *                   with the search request.
     */
    public void prepareSuccessView(MyEventOutputData outputData);

    /**
     * A public method that lets the presenter generates a fail view after the interactor is unable to return any matching
     * events. Because no events are returned, this method does not require a parameter to work.
     */
    public void prepareFailView(); //When no event matches with the search request
}
