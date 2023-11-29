package use_case.logout;

/**
 * Output boundary for the logout use case.
 */

public interface LogoutOutputBoundary  {

    /**
     * Should go to the LoginView.
     */
    void prepareView();

}