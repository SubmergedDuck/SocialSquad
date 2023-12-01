package use_case.leave_event;

import use_case.leave_event.LeaveEventUserDataAccessInterface;
import use_case.leave_event.LeaveEventEventDataAccessInterface;
import use_case.leave_event.LeaveEventOutputBoundary;

import java.util.ArrayList;

/**
 * The interactor for the leave event use case.
 */

public class LeaveEventInteractor {
    private final LeaveEventOutputBoundary leaveEventPresenter;
    private final LeaveEventUserDataAccessInterface userDataAccessObject;
    private final LeaveEventEventDataAccessInterface eventsDataAccessObject;

    /**
     * Constructor for LeaveEventInteractor.
     *
     * @param leaveEventPresenter    the presenter for the leave event use case
     * @param userDataAccessObject   the user data access object for the leave event use case
     * @param eventsDataAccessObject the event data access object for the leave event use case
     */
    public LeaveEventInteractor(LeaveEventOutputBoundary leaveEventPresenter,
                                LeaveEventUserDataAccessInterface userDataAccessObject,
                                LeaveEventEventDataAccessInterface eventsDataAccessObject) {
        this.leaveEventPresenter = leaveEventPresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.eventsDataAccessObject = eventsDataAccessObject;
    }

    /**
     * Executes the leave event use case.
     *
     * @param leaveEventInputData The input data for the use case.
     *                            Removes the event from the user's joinedEvents list and removes the user from the event's peopleJoined list.
     */

    public void execute(LeaveEventInputData leaveEventInputData) {
        // Remove user from event in both DAOs
        userDataAccessObject.userLeaveEvent(leaveEventInputData.getUsername(), leaveEventInputData.getEventID());
        eventsDataAccessObject.userLeaveEvent(leaveEventInputData.getUsername(), leaveEventInputData.getEventID());

        // Get the updated peopleJoined list from the event DAO
        ArrayList<String> peopleJoined = eventsDataAccessObject.getPeopleJoined(leaveEventInputData.getEventID());
        LeaveEventOutputData leaveEventOutputData = new LeaveEventOutputData(peopleJoined);

        // Prepare the success view
        leaveEventPresenter.prepareSuccessView(leaveEventOutputData);
    }

}






