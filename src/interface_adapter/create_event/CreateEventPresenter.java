package interface_adapter.create_event;

import interface_adapter.ViewManagerModel;
import use_case.create_event.CreateEventOutputBoundary;
import use_case.create_event.CreateEventOutputData;

public class CreateEventPresenter implements CreateEventOutputBoundary {
    private final CreateEventViewModel createEventViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateEventPresenter(CreateEventViewModel createEventViewModel, ViewManagerModel viewManagerModel) {
        this.createEventViewModel = createEventViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareFailView(String error) {
        CreateEventState createEventState = createEventViewModel.getState();
        createEventState.setUseCaseSuccessStatus(false);
        createEventState.setError(error);
        createEventViewModel.setState(createEventState);
        createEventViewModel.firePropertyChanged();

    }

    @Override
    public void prepareSuccessView(CreateEventOutputData output) {
        CreateEventState createEventState = createEventViewModel.getState();
        createEventViewModel.setState(createEventState);
        createEventViewModel.firePropertyChanged();

    }
}
