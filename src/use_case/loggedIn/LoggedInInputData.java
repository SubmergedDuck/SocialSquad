package use_case.loggedIn;

public class LoggedInInputData {

    final private String username;

    public LoggedInInputData(String username) {
        this.username = username;
    }

    String getUsername() {
        return username;
    }

}
