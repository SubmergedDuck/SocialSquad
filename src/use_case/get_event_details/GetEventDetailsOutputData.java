package use_case.get_event_details;

import java.util.Map;

/**
 * Output data for the get event details use case.
 */
public class GetEventDetailsOutputData {
    private final String ownerUser;
    private final String eventName;
    private final String eventAddress;
    private final String date;
    private final String description;
    private final String capacity;

    /**
     * Constructor for GetEventDetailsOutputData
     * @param ownerUser the username of the event organizer
     * @param eventName the name of the event
     * @param eventAddress the address of the event
     * @param date the date when the event occurs
     * @param description the event description.
     */
    public GetEventDetailsOutputData(String ownerUser, String eventName, String eventAddress, String date, String description, String capacity){
        this.ownerUser = ownerUser;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.date = date;
        this.description = description;
        this.capacity = capacity;
    }

    /**
     * Gets the username of the owner
     * @return the event organizer's username
     */
    public String getOwnerUser(){return this.ownerUser;}

    /**
     * Gets the event name
     * @return the event name
     */
    public String getEventName(){return this.eventName;}

    /**
     * Gets the address of the location where the event is located
     * @return the event location
     */
    public String getEventAddress(){return this.eventAddress;}

    /**
     * Gets the date of the event
     * @return the event date
     */
    public String getDate(){return this.date;}

    /**
     * The description of the event
     * @return event description
     */
    public String getDescription(){return this.description;}

    /**
     * The capacity of the event (Ex. 4/10)
     * @return returns the total participants out of the max capacity
     */
    public String getCapacity(){return this.capacity;}
}
