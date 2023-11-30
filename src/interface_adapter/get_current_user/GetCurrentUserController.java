package interface_adapter.get_current_user;

import use_case.get_current_user.GetCurrentUserInputBoundary;
import use_case.get_current_user.GetCurrentUserInteractor;

/**
 * Controller for the GetCurrentUser use case.
 */
public class GetCurrentUserController {
    final GetCurrentUserInputBoundary interactor;

    /**
     * Constructor for GetCurrentUserController
     * @param interactor the interactor used for the use case.
     */
    public GetCurrentUserController(GetCurrentUserInteractor interactor){
        this.interactor = interactor;
    }

    /**
     * Tells the interactor to execute since there's no input for this use case.
     */
    public void execute(){
        interactor.execute();
    }
}
