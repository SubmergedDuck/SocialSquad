package interface_adapter.create_event;

import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInputData;

public class CreateEventController {
    private final CreateEventInputBoundary createEventInteractor;

    /**
     * Constructs the instance attributes.
     * @param createEventInteractor the use case interactor for the create event use case.
     */
    public CreateEventController(CreateEventInputBoundary createEventInteractor){
        this.createEventInteractor = createEventInteractor;
    }

    /**
     * This execute method is meant to be used for cases when the inputs for a regular event are passed in.
     * @param owner the username of the event creator
     * @param eventName the name of the event
     * @param location the coordinates of the event
     * @param time the date that the event is being hosted
     * @param type the event type
     * @param description event description
     * @param privacy privacy status of the event, if anyone is free to join or not without the event creator's consent.
     * @param capacity the event's participant capacity.
     */
    public void execute(String owner, String eventName, String location, String time, String type,
                        String description, Boolean privacy, String capacity){
        //For normal events
        CreateEventInputData inputData = new CreateEventInputData(owner, eventName, location, time, type,
                description, privacy, capacity);
        createEventInteractor.execute(inputData);
    }

    /**
     * This execute method is meant to be used for cases when the inputs for a restricted event are passed in.
     * @param owner the username of the event creator
     * @param eventName the name of the event
     * @param location the coordinates of the event
     * @param time the date that the event is being hosted
     * @param type the event type
     * @param description event description
     * @param privacy privacy status of the event, if anyone is free to join or not without the event creator's consent.
     * @param capacity the event's participant capacity.
     * @param ageRestriction the age restriction for the event
     * @param sexRestriction the sex restriction for the event
     */
    public void execute(String owner, String eventName, String location, String time, String type,
                        String description, Boolean privacy, String capacity, String ageRestriction, String sexRestriction){
        //For restricted events
        CreateEventInputData inputData = new CreateEventInputData(owner, eventName, location, time, type,
                description, privacy, capacity, ageRestriction, sexRestriction);
        createEventInteractor.execute(inputData);
    }

    /**
     * This execute method is meant to be used for cases when the inputs for an invite only event are passed in.
     * @param owner the username of the event creator
     * @param eventName the name of the event
     * @param location the coordinates of the event
     * @param time the date that the event is being hosted
     * @param type the event type
     * @param description event description
     * @param privacy privacy status of the event, if anyone is free to join or not without the event creator's consent.
     * @param capacity the event's participant capacity.
     * @param invitedUsers a string containing all the invited users for the event
     * @param inviteOnly the invite only status of the event
     */
    public void execute(String owner, String eventName, String location, String time, String type,
                        String description, Boolean privacy, String capacity, String invitedUsers, Boolean inviteOnly){
        CreateEventInputData inputData = new CreateEventInputData(owner, eventName, location, time, type,
                description, privacy, capacity, inviteOnly, invitedUsers);
        createEventInteractor.execute(inputData);
    }
}