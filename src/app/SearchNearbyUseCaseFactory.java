package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.back_out.BackOutController;
import interface_adapter.get_event_details.GetEventDetailsController;
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
                                          SearchNearbyDataAccessInterface searchNearbyDataAccessobject,
                                          GetEventDetailsController getEventDetailsController,
                                          BackOutController backOutController) {
//        SearchNearbyController searchNearbyController = createSearchNearbyUseCase(viewManagerModel, searchNearbyViewModel,
//                searchNearbyDataAccessobject);
        return new SearchNearbyView(searchNearbyViewModel, getEventDetailsController, backOutController);
    }

    public static SearchNearbyController createSearchNearbyUseCase(ViewManagerModel viewManagerModel,
                                                                    SearchNearbyViewModel searchNearbyViewModel,
                                                                    SearchNearbyDataAccessInterface searchNearbyDataAccessobject) {
        SearchNearbyOutputBoundary searchNearbyPresenter = new SearchNearbyPresenter(searchNearbyViewModel, viewManagerModel);
        SearchNearbyInputBoundary interactor = new SearchNearbyInteractor(searchNearbyDataAccessobject, searchNearbyPresenter);
        return new SearchNearbyController(interactor);
    }
}
