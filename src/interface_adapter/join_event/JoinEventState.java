package interface_adapter.join_event;

public class JoinEventState {
    private boolean success = false;
    private String error = "";

    public JoinEventState(JoinEventState copy) {
        success = copy.success;
        error = copy.error;
    }

    public JoinEventState(){

    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError(String failureReason) {
        this.error = failureReason;
    }
}
