package use_case.create_event;

public class CreateEventInputData {
    private final String ownerUsername;
    private final String eventName;
    private final String location;
    private final String time;
    private final String type;
    private final String description;
    //For privacy and inviteOnly, I was thinking of using a checkbox in the create event view to tick these options off.
    private final Boolean privacy;
    private final Boolean inviteOnly;
    private final Integer capacity; //Text box for capacity will only allow numbers to be inputted.
    private final Integer ageRestriction;
    private final String sexRestriction;

    private CreateEventInputData(String owner, String eventName, String location, String time, String type, String description,
                                Boolean privacy, String capacity, Boolean inviteOnly, Integer ageRestriction,
                                 String sexRestriction){
        //Default constructor for all events.
        this.ownerUsername = owner;
        this.eventName = eventName;
        this.location = location;
        this.time = time;
        this.type = type;
        this.description = description;
        this.privacy = privacy;
        this.inviteOnly = inviteOnly;
        this.ageRestriction = ageRestriction;
        this.sexRestriction = sexRestriction;
        this.capacity = Integer.valueOf(capacity);
        //NOTE: For the inputs for capacity and age restriction, I was thinking of making it so that the input box would only take in numbers, so parsing wouldn't result in an error.
    }
    public CreateEventInputData(String owner, String eventName, String location, String time, String type, String description,
                                Boolean privacy, String capacity){
        //For normal events.
        this(owner, eventName, location, time, type, description, privacy, capacity, false, 0, "");
    }
    public CreateEventInputData(String owner, String eventName, String location, String time, String type, String description,
                                Boolean privacy, String capacity, String ageRestriction, String sexRestriction){
        //For restricted events.
        this(owner, eventName, location, time, type, description, privacy, capacity, false,
                Integer.valueOf(ageRestriction), sexRestriction);
    }

    public CreateEventInputData(String owner, String eventName, String location, String time, String type, String description,
                                Boolean privacy, String capacity, Boolean inviteOnly){
        this(owner, eventName, location, time, type, description, privacy, capacity,inviteOnly, 0, "");
    }

    String getOwner(){return this.ownerUsername;}

    String getEventName(){return this.eventName;}

    String getTime(){return this.time;}

    String getDescription(){return this.description;}

    String getLocation(){return this.location;}

    String getType(){return this.type;}

    Boolean getInviteStatus(){return this.inviteOnly;}

    Integer getCapacity(){return this.capacity;}

    Boolean getPrivacy(){return this.privacy;}

    Integer getAgeRestriction(){return this.ageRestriction;}

    String getSexRestriction(){return this.sexRestriction;}


}
