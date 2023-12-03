package interface_adapter.get_current_user;

import use_case.get_current_user.GetCurrentUserOutputBoundary;
import use_case.get_current_user.GetCurrentUserOutputData;

/**
 * Presenter for the GetCurrentUser use case.
 */
public class GetCurrentUserPresenter implements GetCurrentUserOutputBoundary {

    private final GetCurrentUserViewModel getCurrentUserViewModel;

    public GetCurrentUserPresenter(GetCurrentUserViewModel getCurrentUserViewModel){
        this.getCurrentUserViewModel = getCurrentUserViewModel;
    }
    @Override
    public void prepareView(GetCurrentUserOutputData outputData) {
        GetCurrentUserState state = getCurrentUserViewModel.getState();
        state.setUsername(outputData.getCurrentUser());
        state.setUserCoordinates(outputData.getUserCoordinates());
        getCurrentUserViewModel.setState(state);
        getCurrentUserViewModel.firePropertyChanged();
    }
}
