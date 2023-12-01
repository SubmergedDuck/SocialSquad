package use_case.get_ids;

/**
 * Input boundary for the GetIDs use case.
 */
public interface GetIDsInputBoundary {

    /**
     * Gets the IDs from the DAO and passes it to the presenter.
     * @param inputData the input data of the GetIDs use case, consisting of the username and the type of IDs we want to get.
     */
    void execute(GetIDsInputData inputData);
}
