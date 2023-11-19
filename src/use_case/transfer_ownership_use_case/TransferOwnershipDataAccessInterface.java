package use_case.transfer_ownership_use_case;

public interface TransferOwnershipDataAccessInterface {
    public boolean isParticipant(TransferOwnershipInputData inputData);
    public boolean outside24Hours(TransferOwnershipInputData inputData);
    public void transferOwnership(TransferOwnershipInputData inputData);

}
