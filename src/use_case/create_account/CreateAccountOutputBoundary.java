package use_case.create_account;

public interface CreateAccountOutputBoundary {
    void prepareSuccessView(CreateAccountOutputData user);
    void prepareFailView(String error);
}
