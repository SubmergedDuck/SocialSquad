package use_case.get_ids;

/**
 * Output boundary for the GetIDs use case.
 */
public interface GetIDsOutputBoundary {

    /**
     * Appropriately updates the view model to have the output data.
     * @param outputData all the event ids we want.
     */
    void prepareView(GetIDsOutputData outputData);
}
