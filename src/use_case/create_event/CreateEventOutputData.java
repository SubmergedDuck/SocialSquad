package use_case.create_event;

import entity.Events.Event;

public class CreateEventOutputData {
    private final Event createdEvent; //May change depending on how the interface adapter is implemented and what outputs are needed.

    /**
     * Constructor for CreateEventOutputData
     * @param event the created event
     */
    public CreateEventOutputData(Event event){
        createdEvent = event;
    }
}
