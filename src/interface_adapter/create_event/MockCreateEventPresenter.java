package interface_adapter.create_event;

import use_case.create_event.CreateEventOutputBoundary;
import use_case.signup.SignupOutputBoundary;

public class MockCreateEventPresenter implements CreateEventOutputBoundary {
    @Override
    public void prepareFailView(String error) {
        System.out.println("error");
    }

    @Override
    public void prepareSuccessView(String message) {
        System.out.println("success");
    }
}