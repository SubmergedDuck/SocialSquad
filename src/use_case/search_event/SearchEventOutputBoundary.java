package use_case.search_event;

public interface SearchEventOutputBoundary {
    public void prepareSuccessView(SearchEventOutputData outputData);

    public void prepareFailView(SearchEventOutputData outputData);
}
