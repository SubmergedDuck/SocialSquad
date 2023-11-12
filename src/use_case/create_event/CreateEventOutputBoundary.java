package use_case.create_event;

/**
 * The output boundary for the create event use case which tells the createventstate if an input error occurred or if the event was successfully created.
 */
public interface CreateEventOutputBoundary {
    void prepareFailView(String error); //Pop up message will occur with the error
    void prepareSuccessView(CreateEventOutputData output); //Not set in stone, but perhaps a popup message then exit out of create event view.
}
