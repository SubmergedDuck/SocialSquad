package use_case.search_event;

public interface SearchEventInputBoundary {
    /**
     * The input interface for the SearchEvent use case, used by SearchEventController and SearchEventInteractor.
     */

    /**
     * A public method called by the controller, asking the interactor to execute.
     * @param inputData represents the search request.
     */
    public void execute(SearchEventInputData inputData);
}
