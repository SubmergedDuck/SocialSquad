package use_case.join_event;

/**
 * The output boundary interface for the JoinEvent use case, used by the interactor and presenter.
 */

public interface JoinEventOutputBoundary {
    /**
     * A public method that lets the presenter generates a success view after the interactor returning the search result,
     * in the form of the outputData.
     * @param outputData The result returned by the interactor, it contains updated event information (e.g. capacity).
     */
    public void prepareSuccessView(JoinEventOutputData outputData);
    // Output data is needed: the user successfully joined the desired event, needs to update (# of People / Capacity) info.
    /**
     * A public method that lets the presenter generates a fail view after the interactor is unable to let user join the event.
     * Case: Event is full (e.g. 8/8).
     */
    public void prepareFailView(JoinEventOutputData outputData);
    // Need output data to specify the reason of failure-- likely it's because of event reached capacity.
}
