package use_case.login;

import interface_adapter.ViewModel;

public interface LoginOutputBoundary  {
    void prepareSuccessView(LoginOutputData user);

    void prepareLinkView(ViewModel viewModel);

    void prepareFailView(String error);
}