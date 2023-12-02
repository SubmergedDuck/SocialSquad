package unused_usecases___.usecases.view_participants;

import java.util.ArrayList;

/**
 * Interactor for the view participants use case.
 */
public class ViewParticipantsInteractor implements ViewParticipantsInputBoundary{
    private final ViewParticipantsDataAccessInterface eventDataAccessObject;
    private final ViewParticipantsOutputBoundary viewParticipantsPresenter;

    /**
     * Constructor for ViewParticipantsInteractor
     * @param eventDataAccessObject the event DAO
     * @param viewParticipantsPresenter the presenter for the view participants use case
     */
    public ViewParticipantsInteractor(ViewParticipantsDataAccessInterface eventDataAccessObject, ViewParticipantsOutputBoundary viewParticipantsPresenter){
        this.eventDataAccessObject = eventDataAccessObject;
        this.viewParticipantsPresenter = viewParticipantsPresenter;
    }

    /**
     * Gets the participants from the selected event in the DAO and outputs it to the presenter.
     * @param inputData input data from the controller
     */
    @Override
    public void execute(ViewParticipantsInputData inputData) {
        ArrayList<String> participants = (ArrayList<String>) eventDataAccessObject.getParticipants(inputData.getEventID());
        ViewParticipantsOutputData outputData = new ViewParticipantsOutputData(participants);
        viewParticipantsPresenter.prepareView(outputData);
    }
}
