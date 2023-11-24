package use_case.join_event;

import interface_adapter.join_event.JoinEventState;

public class JoinEventOutputData {
    public final boolean usecaseSuccess = false; // Output Data is only needed when the use case failed
    private final String failureReason;

    public JoinEventOutputData(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getFailureReason() {
        return failureReason;
    }

}
