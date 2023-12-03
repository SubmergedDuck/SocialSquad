package use_case.join_event;

import entity.Events.Event;
import use_case.join_event.JoinEventUserDataAccessInterface;
import use_case.join_event.JoinEventEventDataAccessInterface;
import use_case.join_event.JoinEventOutputBoundary;

import java.util.ArrayList;

/**
 * The interactor for the join event use case.
 */

public class JoinEventInteractor implements JoinEventInputBoundary {

    private final JoinEventOutputBoundary joinEventPresenter;
    private final JoinEventUserDataAccessInterface userDataAccessObject;
    private final JoinEventEventDataAccessInterface eventsDataAccessObject;
    private final JoinEventCurrentUserDataAccessInterface currentUserDataAccessObject;

    /**
     * Constructor for JoinEventInteractor.
     * @param joinEventPresenter the presenter for the join event use case
     * @param userDataAccessObject the user data access object for the join event use case
     * @param eventsDataAccessObject the events data access object for the join event use case
     */

    public JoinEventInteractor(JoinEventOutputBoundary joinEventPresenter,
                               JoinEventUserDataAccessInterface userDataAccessObject,
                               JoinEventEventDataAccessInterface eventsDataAccessObject,
                               JoinEventCurrentUserDataAccessInterface inMemoryCurrentUserDAO) {
        this.joinEventPresenter = joinEventPresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.eventsDataAccessObject = eventsDataAccessObject;
        this.currentUserDataAccessObject = inMemoryCurrentUserDAO;
    }

    /**
     * Executes the join event use case.
     * @param joinEventInputData The input data for the use case.
     * If capacity is not full, the user will be added to the event's joined users list and the event will be added
     * to the user's joined events list.
     */

    public void execute(JoinEventInputData joinEventInputData) {
        String username = joinEventInputData.getUsername();
        int eventID = joinEventInputData.getEventID();
        Event event = eventsDataAccessObject.getEvent(eventID);
        String capacity = eventsDataAccessObject.getCapacity(event.getEventID());

        ArrayList<String> getPeopleJoined = eventsDataAccessObject.getPeopleJoined(event.getEventID());

        // Count number of people already joined
        int current_number_joined = 0;
        for (String user : getPeopleJoined) {
            current_number_joined++;
        }

        if (current_number_joined == Integer.parseInt(capacity)) { // If event is at full capacity
            JoinEventOutputData joinEventOutputDataFail = new JoinEventOutputData(getPeopleJoined, "Note: Event is at full capacity!");
            joinEventPresenter.prepareFailView(joinEventOutputDataFail);
        } else if (getPeopleJoined.contains(username)) { // If user has already joined the event
            JoinEventOutputData joinEventOutputDataFail = new JoinEventOutputData(getPeopleJoined, "Note: You have already joined this event!");
            joinEventPresenter.prepareFailView(joinEventOutputDataFail);
        } else {
            // Updates DAOs
            userDataAccessObject.userJoinEvent(username, event);
            eventsDataAccessObject.userJoinEvent(username, event.getEventID());
            currentUserDataAccessObject.currentUserJoinEvent(event.getEventID());

            // Update getPeopleJoined from eventsDataAccessObject
            getPeopleJoined = eventsDataAccessObject.getPeopleJoined(event.getEventID());

            // Output data will be used for getting the # of people joined to update #/capacity text
            JoinEventOutputData joinEventOutputData = new JoinEventOutputData(getPeopleJoined, null);
            joinEventPresenter.prepareSuccessView(joinEventOutputData);
        }

    }

}
