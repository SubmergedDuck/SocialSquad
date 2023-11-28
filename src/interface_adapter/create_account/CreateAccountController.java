package interface_adapter.create_account;

import use_case.create_account.CreateAccountInputBoundary;
import use_case.create_account.CreateAccountInputData;

public class CreateAccountController {
    final CreateAccountInputBoundary createAccountInputBoundary;
    public CreateAccountController(CreateAccountInputBoundary createAccountInputBoundary){
        this.createAccountInputBoundary =createAccountInputBoundary;
    }

    public void execute(){
        CreateAccountInputData createAccountInputData = new CreateAccountInputData();
        createAccountInputBoundary.execute(createAccountInputData);

    }
}
