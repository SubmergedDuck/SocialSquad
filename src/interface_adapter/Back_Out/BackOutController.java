package interface_adapter.Back_Out;

import use_case.Back_Out.BackOutInputBoundary;
import use_case.Back_Out.BackOutInputData;

public class BackOutController {
    private BackOutInputBoundary backOutInputBoundary;

    public BackOutController(BackOutInputBoundary backOutInputBoundary) {
        this.backOutInputBoundary = backOutInputBoundary;
    }

    public void handleBackButtonClick() {
        // Assuming this method is triggered by the UI button click
        BackOutInputData inputData = new BackOutInputData();
        backOutInputBoundary.execute(inputData);
    }
}
