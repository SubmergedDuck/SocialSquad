package interface_adapter.search_event;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import use_case.search_event.SearchEventOutputBoundary;
import use_case.search_event.SearchEventOutputData;

public class SearchEventPresenter implements SearchEventOutputBoundary {
    public final SearchEventViewModel searchEventViewModel;
    public final ShowEventViewModel showEventViewModel;

    private ViewManagerModel viewManagerModel;
    public SearchEventPresenter(ViewManagerModel viewManagerModel,
                                SearchEventViewModel searchEventViewModel,
                                ShowEventViewModel showEventViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchEventViewModel = searchEventViewModel;
        this.showEventViewModel = showEventViewModel;
    }
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

    @Override
    public void prepareFailView() {
        SearchEventState searchEventState = searchEventViewModel.getState();
        searchEventState.setNoEventFound();
        searchEventViewModel.firePropertyChanged();

    }
}
