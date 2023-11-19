package use_case.transfer_ownership_use_case;

public class TransferOwnershipInteractor implements TransferOwnershipInputBoundary {
    TransferOwnershipDataAccessInterface dataAccessObject;
    TransferOwnershipOutputBoundary presenter;

    public TransferOwnershipInteractor(TransferOwnershipDataAccessInterface dataAccessObject, TransferOwnershipOutputBoundary presenter) {
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
    }
    @Override
    public void execute(TransferOwnershipInputData inputData) {
        if (!dataAccessObject.outside24Hours(inputData)) {
            TransferOwnershipOutputData outputData = new TransferOwnershipOutputData("within24Hours");
            presenter.prepareFailView(outputData);
        } else if (!dataAccessObject.isParticipant(inputData)) {
            TransferOwnershipOutputData outputData = new TransferOwnershipOutputData("invalidParticipantUsername");
            presenter.prepareFailView(outputData);
        } else {
            dataAccessObject.transferOwnership(inputData);
        }

    }
}
