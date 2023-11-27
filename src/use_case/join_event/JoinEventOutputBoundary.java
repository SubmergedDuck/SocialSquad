package use_case.join_event;

public interface JoinEventOutputBoundary {
    public void prepareSuccessView(); // No output data is needed for this case: the user successfully joined the desired event

    public void prepareFailView(JoinEventOutputData outputData); // Need output data to specify the reason of failure-- likely it's because of event reached capacity.
}
