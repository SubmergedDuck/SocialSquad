package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_nearby.SearchNearbyController;
import interface_adapter.search_nearby.SearchNearbyPresenter;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import use_case.search_nearby.SearchNearbyDataAccessInterface;
import use_case.search_nearby.SearchNearbyInputBoundary;
import use_case.search_nearby.SearchNearbyInteractor;
import use_case.search_nearby.SearchNearbyOutputBoundary;
import view.SearchNearbyView;

public class SearchNearbyUseCaseFactory {

    private SearchNearbyUseCaseFactory() {} // Prevent instantiation

    public static SearchNearbyView create(ViewManagerModel viewManagerModel, SearchNearbyViewModel searchNearbyViewModel,
                                          SearchNearbyDataAccessInterface searchNearbyDataAccessobject) {
        SearchNearbyController searchNearbyController = createSearchNearbyUseCase(viewManagerModel, searchNearbyViewModel,
                searchNearbyDataAccessobject);
        return new SearchNearbyView(searchNearbyViewModel, getEventDetailsController, backOutController);
    }

    private static SearchNearbyController createSearchNearbyUseCase(ViewManagerModel viewManagerModel,
                                                                    SearchNearbyViewModel searchNearbyViewModel,
                                                                    SearchNearbyDataAccessInterface searchNearbyDataAccessobject) {
        SearchNearbyOutputBoundary searchNearbyPresenter = new SearchNearbyPresenter(searchNearbyViewModel, viewManagerModel);
        SearchNearbyInputBoundary interactor = new SearchNearbyInteractor(searchNearbyDataAccessobject, searchNearbyPresenter);
        return new SearchNearbyController(interactor);
    }
}
