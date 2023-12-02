package use_case.loggedIn;

import interface_adapter.ViewModel;

public class LoggedInInteractor implements LoggedInInputBoundary{
    final  LoggedInUserDataAccessInterface userDataAccessInterface;
    final LoggedInOutputBoundary loggedInPresenter;

    public LoggedInInteractor(LoggedInUserDataAccessInterface userDataAccessInterface, LoggedInOutputBoundary loggedInOutputBoundary){
        this.userDataAccessInterface = userDataAccessInterface;
        this.loggedInPresenter = loggedInOutputBoundary;
    }

    @Override
    public void execute(LoggedInInputData loggedInInputData){

        String username = loggedInInputData.getUsername();
        ViewModel viewModel = loggedInInputData.getViewModel();
        if (viewModel != null && viewModel.getViewName() !=null && !viewModel.getViewName().isEmpty()) {
            loggedInPresenter.prepareLinkView(viewModel);}
        else{
        LoggedInOutputData loggedInOutputData = new LoggedInOutputData(username,false);
        loggedInPresenter.prepareLogOutView(loggedInOutputData);}
    }
}
