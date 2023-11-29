package interface_adapter.create_event;

import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInputData;

import java.io.IOException;
import java.util.ArrayList;

public class CreateEventController {
    final CreateEventInputBoundary interactor;

    public CreateEventController(CreateEventInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void execute(String user, String eventName, String location, String startTime, String type, String description, String capacity,) throws IOException {
        CreateEventInputData createEventInputData = new CreateEventInputData(user, eventName, location, startTime, type, description, false, capacity);
        interactor.execute(createEventInputData);
    }
}
