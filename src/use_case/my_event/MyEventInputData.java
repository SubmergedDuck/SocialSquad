package use_case.my_event;

public class MyEventInputData {
    private final String username;

    public MyEventInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
