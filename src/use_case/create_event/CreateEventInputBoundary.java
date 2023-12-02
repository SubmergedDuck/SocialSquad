package use_case.create_event;

import java.io.IOException;

/**
 * The input boundary for the create event use case.
 */
public interface CreateEventInputBoundary {
    void execute(CreateEventInputData createEventInputData) throws IOException;
}