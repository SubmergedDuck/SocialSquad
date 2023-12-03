package use_case.login;

import interface_adapter.ViewModel;

import java.io.IOException;

public interface LoginOutputBoundary  {
    void prepareSuccessView(LoginOutputData user) throws IOException;

    void prepareLinkView(ViewModel viewModel);

    void prepareFailView(String error);
}