package use_case.search_event;

public class SearchEventInputData {
    /**
     * The input data class for the SearchEvent use case.
     */
    private final String searchRequest;

    /**
     * Constructor for an SearchEventData object.
     * @param searchRequest A line of String that a user typed in to start the search.
     */
    public SearchEventInputData(String searchRequest) {
        this.searchRequest = searchRequest;
    }

    /**
     * A public method that returns the search request that the user typed in.
     * @return A String the user typed in during search, as the search request.
     */
    public String getSearchRequest(){
        return searchRequest;
    }
}
