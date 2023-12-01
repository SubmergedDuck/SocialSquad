package use_case.get_current_user;

/**
 * Output boundary for the get current user.
 */
public interface GetCurrentUserOutputBoundary {
    void prepareView(GetCurrentUserOutputData outputData);
}
