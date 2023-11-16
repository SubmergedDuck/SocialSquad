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

    /**
     * Constructor for GetEventDetailsOutputData
     * @param ownerUser the username of the event organizer
     * @param eventName the name of the event
     * @param eventAddress the address of the event
     * @param date the date when the event occurs
     * @param description the event description.
     */
    public GetEventDetailsOutputData(String ownerUser, String eventName, String eventAddress, String date, String description){
        this.ownerUser = ownerUser;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.date = date;
        this.description = description;
    }

    String getOwnerUser(){return this.ownerUser;}

    String getEventName(){return this.eventName;}

    String getEventAddress(){return this.eventAddress;}

    String getDate(){return this.date;}

    String getDescription(){return this.description;}
}
