package interface_adapter.search_nearby;

import use_case.search_nearby.SearchNearbyInputBoundary;
import use_case.search_nearby.SearchNearbyInputData;

public class SearchNearbyController {
    final SearchNearbyInputBoundary interactor;

    public SearchNearbyController(SearchNearbyInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     *
     * @param coordinates The location of the user.
     */
    public void execute(String[] coordinates) throws Exception {
        SearchNearbyInputData inputData = new SearchNearbyInputData(coordinates);
        interactor.execute(inputData);
    }
}
