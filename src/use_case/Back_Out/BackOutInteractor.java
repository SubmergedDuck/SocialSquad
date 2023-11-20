package use_case.Back_Out;

import interface_adapter.ViewManagerModel;

public class BackOutInteractor implements BackOutInputBoundary {
    private BackOutOutputBoundary outputBoundary;
    private final ViewManagerModel viewManagerModel;

    public BackOutInteractor(BackOutOutputBoundary outputBoundary, ViewManagerModel viewManagerModel) {
        this.outputBoundary = outputBoundary;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void execute() {
        String previousView = viewManagerModel.getPreviousView();

        if (previousView != null) {
            // Set the previous view as the active view
            viewManagerModel.setActiveView(previousView);

            // Create and send output data (if needed)
            BackOutOutputData outputData = new BackOutOutputData("Back action successful", false);
            outputBoundary.prepareSuccessView(outputData);
        } else {
            // Handle the case where there is no previous view
            outputBoundary.prepareFailView("No previous view available");
        }
    }
}
