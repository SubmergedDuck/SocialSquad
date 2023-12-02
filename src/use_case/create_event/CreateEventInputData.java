package use_case.create_event;

/**
 * The input data for creating event that will be passed into the create event interactor.
 */
public class CreateEventInputData {
    private final String ownerUsername;
    private final String eventName;
    private final String location;
    private final String time;
    private final String type;
    private final String description;
    private final String capacity;

    public CreateEventInputData(String owner, String eventName, String location, String time, String type, String description,
                                String capacity){
        //Default constructor for all events.
        this.ownerUsername = owner;
        this.eventName = eventName;
        this.location = location;
        this.time = time;
        this.type = type;
        this.description = description;
        this.capacity = capacity;
    }

    String getOwner(){return this.ownerUsername;}

    String getEventName(){return this.eventName;}

    String getTime(){return this.time;}

    String getDescription(){return this.description;}

    String getLocation(){return this.location;}

    String getType(){return this.type;}

    String getCapacity(){return this.capacity;}
}