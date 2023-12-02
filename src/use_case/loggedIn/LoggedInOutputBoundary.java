package use_case.loggedIn;


import interface_adapter.ViewModel;

public interface LoggedInOutputBoundary {
    void prepareSuccessView(LoggedInOutputData user);

    void prepareLogOutView(LoggedInOutputData response);

    void prepareFailView(String error);

    void prepareLinkView(ViewModel viewModel);
}

