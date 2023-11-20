package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {
    final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor){
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }
    public void execute(String username, String realName, String password1, String password2, String sex, String age, String contact){
        SignupInputData signupInputData = new SignupInputData(username, realName, password1, password2, age, sex, contact);
        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
