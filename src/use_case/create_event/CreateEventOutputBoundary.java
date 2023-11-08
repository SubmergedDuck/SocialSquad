package use_case.create_event;


public interface CreateEventOutputBoundary {
    void prepareFailView(String error);
    void prepareSuccessView(String message);
}
