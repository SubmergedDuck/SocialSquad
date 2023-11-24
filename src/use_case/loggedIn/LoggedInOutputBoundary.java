package use_case.loggedIn;


public interface LoggedInOutputBoundary {
    void prepareSuccessView(LoggedInOutputData user);

    void prepareFailView(String error);
}

