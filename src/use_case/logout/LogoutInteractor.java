package use_case.logout;

/**
 * Interactor for the logout use case.
 */

public class LogoutInteractor implements LogoutInputBoundary{

        private LogoutOutputBoundary outputBoundary;
        private LogoutCurrentUserDataAccessInterface dataAccessInterface;

        public LogoutInteractor(LogoutOutputBoundary outputBoundary,
                                LogoutCurrentUserDataAccessInterface dataAccessInterface) {

            this.outputBoundary = outputBoundary;
            this.dataAccessInterface = dataAccessInterface;
        }

        /**
        * Logs out the current user, and goes to LoginView.
        */
        public void execute() {
            dataAccessInterface.logoutCurrentUser();
            outputBoundary.prepareView();
        }

}
