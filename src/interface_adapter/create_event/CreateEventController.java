package interface_adapter.create_event;

import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInputData;

import java.io.IOException;

/**
 * Controller for the create event use case.
 */
public class CreateEventController {
    private final CreateEventInputBoundary interactor;

    public CreateEventController(CreateEventInputBoundary interactor){
        this.interactor = interactor;
    }

    /**
     * Provides the interactor with the input data.
     * @throws IOException api call error
     */
    public void execute(String eventOwner, String eventName, String eventType, String coordinates, String capacity,
                        String description, String date) throws IOException {
        CreateEventInputData inputData = new CreateEventInputData(eventOwner,eventName,coordinates,date,eventType,description,capacity);
        interactor.execute(inputData);
    }
}
