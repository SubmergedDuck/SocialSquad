package interface_adapter.get_event_details;

public class GetEventDetailsState {
    private String eventName = "";
    private String eventAddress = "";
    private String eventDate = "";
    private String eventDescription = "";
    private String eventCapacity = "";

    public GetEventDetailsState(GetEventDetailsState copy) {
        eventName = copy.eventName;
        eventAddress = copy.eventAddress;
        eventDate= copy.eventDate;
        eventDescription = copy.eventDescription;
    }

    public GetEventDetailsState() {
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventCapacity() {
        return eventCapacity;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public void setEventDate(String eventDate) {
        this.eventDate= eventDate;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventCapacity(String eventCapacity) {
        this.eventCapacity = eventCapacity;
    }

}
