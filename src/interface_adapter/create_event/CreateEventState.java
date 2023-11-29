package interface_adapter.create_event;

public class CreateEventState {
    private boolean useCaseSuccessStatus = true;
    private String error = "";
    public void setUseCaseSuccessStatus(boolean status) {
        useCaseSuccessStatus = status;
    }

    public boolean getUseCaseSuccessStatus() {
        return useCaseSuccessStatus;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
