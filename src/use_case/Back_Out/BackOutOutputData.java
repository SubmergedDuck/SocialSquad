package use_case.Back_Out;

public class BackOutOutputData {
    private final String username;
    private boolean useCaseFailed;
    public BackOutOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }
}
