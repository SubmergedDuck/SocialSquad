package use_case.search_nearby;

public interface SearchNearbyOutputBoundary {
    public void prepareSuccessView(SearchNearbyOutputData outputData);
    public void prepareFailView();
}
