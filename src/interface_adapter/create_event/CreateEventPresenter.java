package interface_adapter.create_event;

import use_case.create_event.CreateEventOutputBoundary;
import use_case.create_event.CreateEventOutputData;

public class CreateEventPresenter implements CreateEventOutputBoundary {
    private final CreateEventViewModel createEventViewModel;

    public CreateEventPresenter(CreateEventViewModel createEventViewModel){
        this.createEventViewModel = createEventViewModel;
    }
    @Override
    public void prepareFailView(String error) {
        CreateEventState state = createEventViewModel.getState();
        state.setInputError(error);
        createEventViewModel.setState(state);
        createEventViewModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView() {
        CreateEventState state = createEventViewModel.getState();
        state.setEventCreated(true);
        createEventViewModel.setState(state);
        createEventViewModel.firePropertyChanged();
    }
}
