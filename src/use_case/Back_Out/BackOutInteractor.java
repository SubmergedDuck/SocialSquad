package use_case.Back_Out;

public class BackOutInteractor implements BackOutInputBoundary {
    private BackOutOutputBoundary outputBoundary;

    public BackOutInteractor(BackOutOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(BackOutInputData backOutInputData) {
        // Implement your business logic here
        // This is where you would handle the back action
        // You can interact with BackOutOutputBoundary to send results to the UI
    }
}
