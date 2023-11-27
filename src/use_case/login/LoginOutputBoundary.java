package use_case.login;

public interface LoginOutputBoundary  {
    void prepareSuccessView(LoginOutputData user);

    void prepareLinkView(String viewName);

    void prepareFailView(String error);
}