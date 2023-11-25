package use_case.join_event;

import use_case.join_event.JoinEventUserDataAccessInterface;
import use_case.join_event.JoinEventEventDataAccessInterface;
import use_case.join_event.JoinEventOutputBoundary;

import java.util.ArrayList;

/**
 * The interactor for the join event use case.
 */

public class JoinEventInteractor {

    private final JoinEventOutputBoundary joinEventPresenter;
    private final JoinEventUserDataAccessInterface userDataAccessObject;
    private final JoinEventEventDataAccessInterface eventsDataAccessObject;

    /**
     * Constructor for JoinEventInteractor.
     * @param joinEventPresenter the presenter for the join event use case
     * @param userDataAccessObject the user data access object for the join event use case
     * @param eventsDataAccessObject the events data access object for the join event use case
     */

    public JoinEventInteractor(JoinEventOutputBoundary joinEventPresenter,
                               JoinEventUserDataAccessInterface userDataAccessObject,
                               JoinEventEventDataAccessInterface eventsDataAccessObject) {
        this.joinEventPresenter = joinEventPresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.eventsDataAccessObject = eventsDataAccessObject;
    }

    /**
     * Executes the join event use case.
     * @param joinEventInputData The input data for the use case.
     * If capacity is not full, the user will be added to the event's joined users list and the event will be added
     * to the user's joined events list.
     */

    public void execute(JoinEventInputData joinEventInputData) {
        String username = joinEventInputData.getUsername();
        Integer eventID = joinEventInputData.getEventID();
        String capacity = eventsDataAccessObject.getCapacity(eventID);

        ArrayList<String> getPeopleJoined = eventsDataAccessObject.getPeopleJoined(eventID);

        // Count number of people already joined
        int current_number_joined = 0;
        for (String user : getPeopleJoined) {
            current_number_joined++;
        }

        // If current_number_joined is equal to capacity then prepareFailView else join event and prepareSuccessView
        if (current_number_joined == Integer.parseInt(capacity)) {
            joinEventPresenter.prepareFailView();
        } else {
            userDataAccessObject.userJoinEvent(username, eventID);
            eventsDataAccessObject.userJoinEvent(username, eventID);

            // Update getPeopleJoined from eventsDataAccessObject
            getPeopleJoined = eventsDataAccessObject.getPeopleJoined(eventID);

            JoinEventOutputData joinEventOutputData = new JoinEventOutputData(getPeopleJoined);
            joinEventPresenter.prepareSuccessView(joinEventOutputData);
        }

    }

}
