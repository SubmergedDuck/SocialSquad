package interface_adapter.logged_in;

import use_case.loggedIn.LoggedInInputBoundary;
import use_case.loggedIn.LoggedInInputData;

public class LoggedInController {
    final LoggedInInputBoundary loggedInUseCaseInteractor;
    public LoggedInController(LoggedInInputBoundary loggedInUseCaseInteractor) {
        this.loggedInUseCaseInteractor = loggedInUseCaseInteractor;
    }


    public void execute(String username) {
        LoggedInInputData loggedInInputData = new LoggedInInputData(
                username);

        loggedInUseCaseInteractor.execute(loggedInInputData);
    }
}
