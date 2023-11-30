package interface_adapter.get_current_user;

import use_case.get_current_user.GetCurrentUserInputBoundary;
import use_case.get_current_user.GetCurrentUserInteractor;

/**
 * Controller for the GetCurrentUser use case.
 */
public class GetCurrentUserController {
    final GetCurrentUserInputBoundary interactor;

    public GetCurrentUserController(GetCurrentUserInteractor interactor){
        this.interactor = interactor;
    }

    public void execute(){
        interactor.execute();
    }
}
