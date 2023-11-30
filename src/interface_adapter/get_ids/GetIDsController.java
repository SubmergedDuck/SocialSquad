package interface_adapter.get_ids;

import use_case.get_ids.GetIDsInputBoundary;
import use_case.get_ids.GetIDsInputData;

/**
 * Controller for GetIDs.
 */
public class GetIDsController {
    final GetIDsInputBoundary interactor;

    /**
     * Constructor for GetIDsController
     * @param interactor the interactor for the GetIDs use case.
     */
    public GetIDsController(GetIDsInputBoundary interactor){
        this.interactor = interactor;
    }

    /**
     *
     * @param username the username
     * @param isCreated
     */
    public void execute(String username, boolean isCreated){
        GetIDsInputData inputData = new GetIDsInputData(username, isCreated);
        interactor.execute(inputData);
    }
}
