package interface_adapter.transfer_ownership;

import use_case.transfer_ownership_use_case.TransferOwnershipOutputBoundary;
import use_case.transfer_ownership_use_case.TransferOwnershipOutputData;

public class TransferOwnershipPresenter implements TransferOwnershipOutputBoundary {
    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView(TransferOwnershipOutputData failReason) {

    }
}
