package use_case.leave_event;

/**
 * The output boundary interface for the LeaveEvent use case, used by the interactor and presenter.
 */

public interface LeaveEventOutputBoundary {
    /**
     * A public method that lets the presenter generates a success view after the interactor returning the search result,
     * in the form of the outputData.
     * @param outputData The result returned by the interactor, it contains updated event information (e.g. #/capacity).
     */
    public void prepareSuccessView(LeaveEventOutputData outputData);
}
