package use_case.create_account;

public class CreateAccountInteractor implements CreateAccountInputBoundary{
    final CreateAccountOutputBoundary createAccountOutputBoundary;
    public CreateAccountInteractor(CreateAccountOutputBoundary createAccountOutputBoundary){
        this.createAccountOutputBoundary = createAccountOutputBoundary;
    }


    @Override
    public void execute(CreateAccountInputData createAccountInputData) {

    }
}
