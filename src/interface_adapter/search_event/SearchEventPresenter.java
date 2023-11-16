package interface_adapter.search_event;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import use_case.search_event.SearchEventOutputBoundary;
import use_case.search_event.SearchEventOutputData;

public class SearchEventPresenter implements SearchEventOutputBoundary {
    /**
     * The presenter class for the SearchEvent use case.
     */
    public final SearchEventViewModel searchEventViewModel;
    public final ShowEventViewModel showEventViewModel;

    private ViewManagerModel viewManagerModel;

    /**
     * The constructor call for a SearchEventPresenter object.
     * @param viewManagerModel The associated ViewManagerModel object.
     * @param searchEventViewModel The ViewModel directly connects to this Search Event use case.
     * @param showEventViewModel THe ViewModel called when a search is successful, this VM is associated to a separate view
     *                           that will display the matching events.
     */
    public SearchEventPresenter(ViewManagerModel viewManagerModel,
                                SearchEventViewModel searchEventViewModel,
                                ShowEventViewModel showEventViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchEventViewModel = searchEventViewModel;
        this.showEventViewModel = showEventViewModel;
    }

    /**
     * A public method that lets the presenter generates a success view after the interactor returning the search result,
     * in the form of the outputData.
     *
     * @param outputData The result returned by the interactor, it contains events that are fully or partially matched
     *                   with the search request.
     */
    @Override
    public void prepareSuccessView(SearchEventOutputData outputData) {
        // ON success, switch to show event state that presents all search results
        ShowEventState showEventState = showEventViewModel.getState();
        showEventState.setEventDisplayed(outputData.getCompleteMatch(), outputData.getPartialMatch());
        this.showEventViewModel.setState(showEventState);
        showEventViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(showEventViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    /**
     * A public method that lets the presenter generates a fail view after the interactor is unable to return any matching
     * events. Because no events are returned, this method does not require a parameter to work.
     */
    @Override
    public void prepareFailView() {
        SearchEventState searchEventState = searchEventViewModel.getState();
        searchEventState.setNoEventFound();
        searchEventViewModel.firePropertyChanged();

    }
}
