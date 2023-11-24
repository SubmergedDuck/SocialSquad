package use_case.loggedIn;

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
        LoggedInOutputData loggedInOutputData = new LoggedInOutputData(username,false);
        loggedInPresenter.prepareSuccessView(loggedInOutputData);
    }
}
