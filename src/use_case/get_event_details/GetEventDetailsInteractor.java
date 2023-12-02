package use_case.get_event_details;

import entity.Events.Event;
import entity.Users.User;

public class GetEventDetailsInteractor implements GetEventDetailsInputBoundary{
    private final GetEventDetailsOutputBoundary getEventDetailsPresenter;
    private final GetEventDetailsDataAccessInterface eventsDataAccessObject;

    /**
     * Constructor for GetEventDetailsInteractor
     * @param getEventDetailsPresenter the presenter that receives the event details output.
     * @param eventDataAccessObject the event DAO used for finding the event details.
     */
    public GetEventDetailsInteractor(GetEventDetailsOutputBoundary getEventDetailsPresenter,
                                     GetEventDetailsDataAccessInterface eventDataAccessObject){
        this.getEventDetailsPresenter = getEventDetailsPresenter;
        this.eventsDataAccessObject = eventDataAccessObject;
    }

    /**
     * Gets the event details from the event object stored in the DAO and passes it to the presenter.
     * @param inputData input for identifying what event details are being retrieved.
     */
    @Override
    public void execute(GetEventDetailsInputData inputData) {
        int eventID = inputData.getEventID();
        Event chosenEvent = eventsDataAccessObject.getEvent(eventID);
        String ownerUser = chosenEvent.getOwnerUser();
        String eventName = chosenEvent.getEventName();
        String eventAddress = chosenEvent.getLocation().getAddress();
        String eventDate = chosenEvent.getTime().toString();
        String eventDescription = chosenEvent.getDescription();
        String eventCapacity = (chosenEvent.getPeopleJoined().size()) + "/" + chosenEvent.getCapacity().toString();
        GetEventDetailsOutputData outputData = new GetEventDetailsOutputData(ownerUser, eventName, eventAddress, eventDate,
                eventDescription, eventCapacity, eventID);
        getEventDetailsPresenter.prepareView(outputData);
    }
}
