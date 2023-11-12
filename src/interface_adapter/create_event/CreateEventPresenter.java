package interface_adapter.create_event;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_event.SearchEventState;
import interface_adapter.search_event.SearchEventViewModel;
import use_case.create_event.CreateEventOutputBoundary;
import use_case.create_event.CreateEventOutputData;

public class CreateEventPresenter implements CreateEventOutputBoundary {
    private final CreateEventViewModel createEventViewModel;

    private final SearchEventViewModel searchEventViewModel;
    private ViewManagerModel viewManagerModel;
    public CreateEventPresenter(CreateEventViewModel createEventViewModel, ViewManagerModel viewManagerModel,
                                SearchEventViewModel searchEventViewModel){
        this.createEventViewModel = createEventViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchEventViewModel = searchEventViewModel;
    }

    @Override
    public void prepareFailView(String error) {
        CreateEventState state = createEventViewModel.getState();
        state.setInputError(error);
        createEventViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(CreateEventOutputData output) {
        //Goes back to the search event page once event is created.
        SearchEventState searchEventState = searchEventViewModel.getState();
        //Need to wait for searcheventview to be implemented to know how to properly "activate" it.
    }
}
