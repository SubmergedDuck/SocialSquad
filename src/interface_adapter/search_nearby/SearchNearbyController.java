package interface_adapter.search_nearby;

import entity.Location.Location;
import use_case.search_nearby.SearchNearbyInputBoundary;
import use_case.search_nearby.SearchNearbyInputData;

public class SearchNearbyController {
    final SearchNearbyInputBoundary interactor;

    public SearchNearbyController(SearchNearbyInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     *
     * @param location The location of the user.
     */
    public void execute(Location location) throws Exception {
        String[] strCoord = location.getCoordinates();
        SearchNearbyInputData inputData = new SearchNearbyInputData(strCoord);
        interactor.execute(inputData);
    }
}
