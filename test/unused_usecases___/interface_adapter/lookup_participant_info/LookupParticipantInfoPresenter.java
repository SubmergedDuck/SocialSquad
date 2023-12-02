package unused_usecases___.interface_adapter.lookup_participant_info;

import interface_adapter.ViewManagerModel;
import unused_usecases___.usecases.look_up_participant_info.LookupParticipantInfoOutputBoundary;
import unused_usecases___.usecases.look_up_participant_info.LookupParticipantInfoOutputData;

public class LookupParticipantInfoPresenter implements LookupParticipantInfoOutputBoundary {
    /**
     * The pressenter class for the LookupParticipantInfo use case and implements the associated output boundary.
     */

    private final LookupParticipantInfoViewModel lookupParticipantInfoViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * The constructor call for a LookupParticipantInfoPresenter object.
     * @param lookupParticipantInfoViewModel The view model associated with this use case.
     * @param viewManagerModel The view manager model that manages view models of all use cases.
     */
    public LookupParticipantInfoPresenter(LookupParticipantInfoViewModel lookupParticipantInfoViewModel, ViewManagerModel viewManagerModel) {
        this.lookupParticipantInfoViewModel = lookupParticipantInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * A public method called by interactor after completing its part on the use case, the Presenter will prepare the view
     * with the output data provided by the interactor.
     * @param outputData Represents the username, age, sex, contact, and location of a user whose information is looked up
     *                   by the use case.
     */
    @Override
    public void prepareSuccessView(LookupParticipantInfoOutputData outputData) {
        ;;
    }
}
