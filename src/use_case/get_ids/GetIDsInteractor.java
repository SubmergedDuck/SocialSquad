package use_case.get_ids;

import java.util.ArrayList;

/**
 * The interactor for the GetIDs use case.
 */
public class GetIDsInteractor implements  GetIDsInputBoundary{
    private final GetIDsDataAccessInterface userDataAccessObject;
    private final GetIDsOutputBoundary presenter;

    /**
     * Constructor for GetIDsInteractor
     * @param userDataAccessObject the user data access object
     * @param presenter the presenter
     */
    public GetIDsInteractor(GetIDsDataAccessInterface userDataAccessObject, GetIDsOutputBoundary presenter){
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(GetIDsInputData inputData) {
        ArrayList<Integer> allIDs = userDataAccessObject.getIds(inputData.getUsername(), inputData.isCreatedEvents());
        GetIDsOutputData outputData = new GetIDsOutputData(allIDs);
        presenter.prepareView(outputData);
    }
}
