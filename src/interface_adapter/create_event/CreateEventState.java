package interface_adapter.create_event;

public class CreateEventState {
    private boolean isDisplayed = false;
    private String inputError = null;
    private boolean eventCreated = false;
    private String eventName = "";
    private String eventTime = "";
    private String eventType = "";
    private String coordinates = "";
    private String date = "";
    private String capacity = "";
    private String description = "";

    public CreateEventState(CreateEventState copy){
        eventName = copy.eventName;
        eventTime = copy.eventTime;
        eventType = copy.eventType;
        coordinates = copy.coordinates;
        date = copy.date;
        capacity = copy.capacity;
        description = copy.description;
    }

    public CreateEventState(){}

    public boolean getIsDisplayed() {
        return isDisplayed;
    }

    public void setIsDisplayed(boolean displayed) {
        this.isDisplayed = displayed;
    }

    public String getEventName(){return this.eventName;}

    public boolean isEventCreated(){return eventCreated;}

    public String getInputError(){return this.inputError;}

    public String getEventTime(){return this.eventTime;}

    public String getEventType(){return this.eventType;}

    public String getCoordinates(){return this.coordinates;}

    public String getDate(){return this.date;}

    public String getCapacity(){return this.capacity;}

    public String getDescription(){return this.description;}

    public void setEventName(String eventName){this.eventName = eventName;}

    public void setEventTime(String eventTime){this.eventTime = eventTime;}

    public void setEventType(String eventType){this.eventType = eventType;}

    public void setCoordinates(String coordinates){this.coordinates = coordinates;}

    public void setDate(String date){this.date = date;}

    public void setCapacity(String capacity){this.capacity = capacity;}

    public void setDescription(String description){this.description = description;}

    public void setInputError(String inputError){this.inputError = inputError;}

    public void setEventCreated(boolean eventCreated){this.eventCreated = eventCreated;}
}
