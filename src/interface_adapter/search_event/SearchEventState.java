package interface_adapter.search_event;

//import view.SearchEventView;

public class SearchEventState {
    private String searchRequest = "";
    private boolean noEventFound;

    public SearchEventState(){
        noEventFound = false;
    }
    public String getSearchRequest() {
        return searchRequest;
    }

    public void setSearchRequest(String searchRequest) {
        this.searchRequest = searchRequest;
    }

    public boolean confirmEventFound() {
        return !noEventFound;
    }

    public void setNoEventFound() {
        this.noEventFound = true;
    }
}
