package use_case.create_event;

/**
 * The input boundary for the create event use case.
 */
public interface CreateEventInputBoundary {
    void execute(CreateEventInputData createEventInputData);
}
