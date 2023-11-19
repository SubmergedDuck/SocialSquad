package use_case.transfer_ownership_use_case;

public class TransferOwnershipOutputData {
    private final String failReason;

    public TransferOwnershipOutputData(String failReason) {
        this.failReason = failReason;
    }

    public String getFailReason() {
        return this.failReason;
    }
}
