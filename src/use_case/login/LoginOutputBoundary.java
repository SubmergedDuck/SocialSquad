package use_case.login;

import interface_adapter.ViewModel;

public interface LoginOutputBoundary  {
    void prepareSuccessView(LoginOutputData user);

    void prepareLinkView(LoginOutputData outputData, ViewModel viewModel);

    void prepareFailView(LoginOutputData outputData, String error);
}