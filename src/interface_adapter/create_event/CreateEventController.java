package interface_adapter.create_event;

import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInputData;

import java.util.ArrayList;

public class CreateEventController {

    private final String eventOwner;
    private final String eventName;
    private final String eventType;
    private final String coordinates;
    private final String date;
    private final String capacity;
    private final String description;
    private final CreateEventInputBoundary interactor;

    public CreateEventController(String eventOwner, String eventName, String eventType, String coordinates, String capacity,
                                 String description, String date, CreateEventInputBoundary interactor){
        this.eventOwner = eventOwner;
        this.eventName = eventName;
        this.eventType = eventType;
        this.coordinates = coordinates;
        this.date = date;
        this.capacity = capacity;
        this.description = description;
        this.interactor = interactor;
    }
    public void execute() {

    }
}
