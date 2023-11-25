package use_case.search_nearby;

public class SearchNearbyInputData {
    private final String[] coordinates;

    public SearchNearbyInputData(String[] coordinates) {
        this.coordinates = coordinates;
    }

    public String[] getCoordinates() {
        return this.coordinates;
    }
}
