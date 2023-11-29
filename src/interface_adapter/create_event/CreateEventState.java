package interface_adapter.create_event;

public class CreateEventState {
    private boolean useCaseSuccessStatus = true;
    private String error = "";
    private String eventName = "";
    private String location = "";
    private String startTime = "";
    private String endTime = "";
    private String date = "";
    private String eventType = "";
    private String description = "";
    private String capacity = "";
    private boolean displayed = false;

    public void setUseCaseSuccessStatus(boolean status) {
        useCaseSuccessStatus = status;
    }

    public boolean getUseCaseSuccessStatus() {
        return useCaseSuccessStatus;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setLocation(String coord) {
        this.location = coord;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public void setDisplayed(boolean setValue) {
        this.displayed = setValue;
    }

    public boolean getDisplayed() {
        return displayed;
    }
}
