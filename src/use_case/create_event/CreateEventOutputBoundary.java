package use_case.create_event;


public interface CreateEventOutputBoundary {
    void prepareFailView(String error); //Pop up message will occur with the error
    void prepareSuccessView(String message); //Not set in stone, but perhaps a popup message then exit out of create event view.
}
