package interface_adapter.get_current_user;

import use_case.get_current_user.GetCurrentUserOutputBoundary;
import use_case.get_current_user.GetCurrentUserOutputData;

public class GetCurrentUserPresenter implements GetCurrentUserOutputBoundary {

    private final GetCurrentUserViewModel getCurrentUserViewModel;

    public GetCurrentUserPresenter(GetCurrentUserViewModel getCurrentUserViewModel){
        this.getCurrentUserViewModel = getCurrentUserViewModel;
    }
    @Override
    public void prepareView(GetCurrentUserOutputData outputData) {
        GetCurrentUserState state = getCurrentUserViewModel.getState();
        state.setUsername(outputData.getCurrentUser());
        getCurrentUserViewModel.setState(state);
        getCurrentUserViewModel.firePropertyChanged();
    }
}
