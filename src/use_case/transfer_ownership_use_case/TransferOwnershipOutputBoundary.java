package use_case.transfer_ownership_use_case;

public interface TransferOwnershipOutputBoundary {
    public void prepareSuccessView();
    public void prepareFailView(TransferOwnershipOutputData failReason);
}
