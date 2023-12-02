package interface_adapter.get_event_details;

public class GetEventDetailsState {
    private String ownerUser = "";
    private String eventName = "";
    private String eventAddress = "";
    private String eventDate = "";
    private String eventDescription = "";
    private String eventCapacity = "";
    private boolean changeView;

    private int eventID;

    public GetEventDetailsState(GetEventDetailsState copy) {
        this.ownerUser = copy.ownerUser;
        eventName = copy.eventName;
        eventAddress = copy.eventAddress;
        eventDate= copy.eventDate;
        eventDescription = copy.eventDescription;
    }

    public GetEventDetailsState() {
    }

    public String getOwnerUser() {
        return ownerUser;
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

    public void setOwnerUser(String ownerUser) {
        this.ownerUser = ownerUser;
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

    public void setEventID(int eventID){this.eventID = eventID;}

    public int getEventID(){return eventID;}

    public boolean hasChangedView(){return this.changeView;}

    public void setChangeView(boolean changeView){
        this.changeView = changeView;
    }

}
