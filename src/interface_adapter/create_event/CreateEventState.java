package interface_adapter.create_event;

public class CreateEventState {
    private String inputError = null;
    private String eventName = null;

    public CreateEventState(){}

    public CreateEventState(CreateEventState copy){
        inputError = copy.inputError;
        eventName = copy.eventName;
    }

    public String getInputError(){return inputError;}

    public void setInputError(String error){
        inputError = error;
    }
    public String getEventName(){return eventName;}
}
