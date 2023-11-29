package use_case.logout;

/**
 * Interactor for the logout use case.
 */

public class LogoutInteractor implements LogoutInputBoundary{

        private LogoutOutputBoundary loginPresenter;
        private LogoutCurrentUserDataAccessInterface dataAccessInterface;

        public LogoutInteractor(LogoutOutputBoundary loginPresenter,
                                LogoutCurrentUserDataAccessInterface dataAccessInterface) {

            this.loginPresenter = loginPresenter;
            this.dataAccessInterface = dataAccessInterface;
        }

        /**
        * Logs out the current user, and goes to LoginView.
        */
        public void execute() {
            dataAccessInterface.logoutCurrentUser();
            loginPresenter.prepareView();
        }

}
