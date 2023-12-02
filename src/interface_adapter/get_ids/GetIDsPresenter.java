package interface_adapter.get_ids;

import use_case.get_ids.GetIDsOutputBoundary;
import use_case.get_ids.GetIDsOutputData;

/**
 * The presenter for the GetIDs use case.
 */
public class GetIDsPresenter implements GetIDsOutputBoundary {
    private final GetIDsViewModel getIDsViewModel;

    /**
     * Constructor for GetIDsPresenter
     * @param getIDsViewModel the view model for GetIDs
     */
    public GetIDsPresenter(GetIDsViewModel getIDsViewModel){
        this.getIDsViewModel = getIDsViewModel;
    }

    @Override
    public void prepareView(GetIDsOutputData outputData) {
        GetIDsState state = getIDsViewModel.getState();
        state.setAllIDs(outputData.getEventIDs());
        state.setIsCreated(outputData.isCreated());
        this.getIDsViewModel.setState(state);
        getIDsViewModel.firePropertyChanged();
    }
}
