package interface_adapter.create_event;

import use_case.create_event.CreateEventOutputBoundary;
import use_case.create_event.CreateEventOutputData;

public class CreateEventPresenter implements CreateEventOutputBoundary {
    @Override
    public void prepareFailView(String error) {

    }

    @Override
    public void prepareSuccessView(CreateEventOutputData output) {

    }
}
