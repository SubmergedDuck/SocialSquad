package use_case.get_current_user;

import entity.Users.User;

/**
 * Interactor for the GetCurrentUser use case.
 */
public class GetCurrentUserInteractor implements GetCurrentUserInputBoundary {
    private final GetCurrentUserOutputBoundary presenter;
    private final CurrentUserDataAccessInterface currentUserDataAccessObject;

    /**
     * Constructor for GetCurrentUserInteractor.
     * @param presenter the presenter for the get current user use case
     * @param currentUserDataAccessObject the dao that stores info about the current user
     */
    public GetCurrentUserInteractor(GetCurrentUserOutputBoundary presenter, CurrentUserDataAccessInterface
            currentUserDataAccessObject){
        this.presenter = presenter;
        this.currentUserDataAccessObject = currentUserDataAccessObject;
    }

    /**
     * Gets the current user's username from the dao and sends it to the presenter.
     */
    @Override
    public void execute() {
        User user = currentUserDataAccessObject.getCurrentUser();
        String[] userCoordinates = user.getLocation().getCoordinates();
        if (user != null){
            String username = user.getUsername();
            GetCurrentUserOutputData outputData = new GetCurrentUserOutputData(username, userCoordinates);
            presenter.prepareView(outputData);
        }
    }
}
