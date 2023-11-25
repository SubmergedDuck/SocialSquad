package use_case.create_event;

import java.util.ArrayList;
import java.util.Arrays;

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
    private final Boolean privacy;
    private final Boolean inviteOnly;
    private final Integer capacity; //Text box for capacity will only allow numbers to be inputted.
    private final Integer ageRestriction;
    private final String sexRestriction;
    private final ArrayList<String> invitedPeople;

    private CreateEventInputData(String owner, String eventName, String location, String time, String type, String description,
                                 Boolean privacy, String capacity, Boolean inviteOnly, Integer ageRestriction,
                                 String sexRestriction, ArrayList<String> invitedPeople){
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
        this.invitedPeople = invitedPeople;
        //NOTE: For the inputs for capacity and age restriction, I was thinking of making it so that the input box would only take in numbers, so parsing wouldn't result in an error.
    }

    /**
     * Initializes the instance attributes when the inputs for a normal event are passed in.
     * @param owner the username of the owner
     * @param eventName the event name
     * @param location the coordinates of the event
     * @param time the date that the event will be held on
     * @param type the event type
     * @param description the event description
     * @param privacy the privacy status of the event
     * @param capacity the event's participant capacity
     */
    public CreateEventInputData(String owner, String eventName, String location, String time, String type, String description,
                                Boolean privacy, String capacity){
        //For normal events.
        this(owner, eventName, location, time, type, description, privacy, capacity, false, 0, "", new ArrayList<String>());
    }

    /**
     * Initializes the instance attributes when the inputs for a restricted event are passed in.
     * @param owner the username of the owner
     * @param eventName the event name
     * @param location the coordinates of the event
     * @param time the date that the event will be held on
     * @param type the event type
     * @param description the event description
     * @param privacy the privacy status of the event
     * @param capacity the event's participant capacity
     * @param ageRestriction age restriction for the event
     * @param sexRestriction sex restriction for the event
     */
    public CreateEventInputData(String owner, String eventName, String location, String time, String type, String description,
                                Boolean privacy, String capacity, String ageRestriction, String sexRestriction){
        //For restricted events.
        this(owner, eventName, location, time, type, description, privacy, capacity, false,
                Integer.valueOf(ageRestriction), sexRestriction, new ArrayList<String>());
    }

    /**
     * Initializes the instance attributes when the inputs for an invite only event are being passed in.
     * @param owner the username of the owner
     * @param eventName the event name
     * @param location the coordinates of the event
     * @param time the date that the event will be held on
     * @param type the event type
     * @param description the event description
     * @param privacy the privacy status of the event
     * @param capacity the event's participant capacity
     * @param inviteOnly invite only status of the event
     * @param invitedPeople invited users to the event
     */
    public CreateEventInputData(String owner, String eventName, String location, String time, String type, String description,
                                Boolean privacy, String capacity, Boolean inviteOnly, String invitedPeople){
        this(owner, eventName, location, time, type, description, privacy, capacity,inviteOnly, 0,
                "", new ArrayList<>(Arrays.asList(invitedPeople.split(" "))));
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

    ArrayList<String> getInvitedPeople(){return this.invitedPeople;}
}