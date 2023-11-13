package use_case.loggedIn;

public class LoggedInInteractor implements LoggedInInputBoundary {
    final LoggedInOutputBoundary loggedInPresenter;

    public LoggedInInteractor(LoggedInOutputBoundary LoggedInOutputBoundary) {
        this.loggedInPresenter = LoggedInOutputBoundary;
    }

    @Override
    public void execute(LoggedInInputData LoggedInInputData) {
        String username = LoggedInInputData.getUsername();
        LoggedInOutputData loggedInOutputData = new LoggedInOutputData(username, false);
        loggedInPresenter.prepareSuccessView(loggedInOutputData);
    }
}