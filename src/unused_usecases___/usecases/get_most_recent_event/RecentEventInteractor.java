package unused_usecases___.usecases.get_most_recent_event;

/**
 * Interactor for the get recent event use case.
 */
public class RecentEventInteractor implements RecentEventInputBoundary{
    private final RecentEventOutputBoundary presenter;
    private final RecentEventDataAccessInterface recentEventDataAccessObject;

    public RecentEventInteractor(RecentEventOutputBoundary presenter, RecentEventDataAccessInterface recentEventDataAccessObject){
        this.presenter = presenter;
        this.recentEventDataAccessObject = recentEventDataAccessObject;
    }
    @Override
    public void execute() {
        int eventID = recentEventDataAccessObject.lastSavedID();
        RecentEventOutputData outputData = new RecentEventOutputData(eventID);
        presenter.prepareView(outputData);
    }
}
