package interface_adapter.create_event;

import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInputData;

public class CreateEventController {
    private final CreateEventInputBoundary createEventInteractor;

    public CreateEventController(CreateEventInputBoundary createEventInteractor){
        this.createEventInteractor = createEventInteractor;
    }

    public void execute(String owner, String eventName, String location, String time, String type,
                        String description, Boolean privacy, String capacity){
        //For normal events
        CreateEventInputData inputData = new CreateEventInputData(owner, eventName, location, time, type,
                description, privacy, capacity);
        createEventInteractor.execute(inputData);
    }
    public void execute(String owner, String eventName, String location, String time, String type,
                        String description, Boolean privacy, String capacity, String ageRestriction, String sexRestriction){
        //For restricted events
        CreateEventInputData inputData = new CreateEventInputData(owner, eventName, location, time, type,
                description, privacy, capacity, ageRestriction, sexRestriction);
        createEventInteractor.execute(inputData);
    }
    public void execute(String owner, String eventName, String location, String time, String type,
                        String description, Boolean privacy, String capacity, Boolean inviteOnly){
        CreateEventInputData inputData = new CreateEventInputData(owner, eventName, location, time, type,
                description, privacy, capacity, inviteOnly);
        createEventInteractor.execute(inputData);
    }
}