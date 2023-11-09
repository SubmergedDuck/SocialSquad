package use_case.search_event;

public class SearchEventInputData {
    private final String searchRequest;


    public SearchEventInputData(String searchRequest) {
        this.searchRequest = searchRequest;
    }

    public String getSearchRequest(){
        return searchRequest;
    }
}
