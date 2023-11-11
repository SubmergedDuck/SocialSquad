package interface_adapter.lookup_participant_info;

import interface_adapter.ViewManagerModel;
import use_case.look_up_participant_info.LookupParticipantInfoOutputBoundary;
import use_case.look_up_participant_info.LookupParticipantInfoOutputData;

public class LookupParticipantInfoPresenter implements LookupParticipantInfoOutputBoundary {

    private final LookupParticipantInfoViewModel lookupParticipantInfoViewModel;
    private ViewManagerModel viewManagerModel;

    public LookupParticipantInfoPresenter(LookupParticipantInfoViewModel lookupParticipantInfoViewModel, ViewManagerModel viewManagerModel) {
        this.lookupParticipantInfoViewModel = lookupParticipantInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(LookupParticipantInfoOutputData outputData) {
        ;;
    }
}
