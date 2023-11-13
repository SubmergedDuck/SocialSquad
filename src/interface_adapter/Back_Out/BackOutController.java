package interface_adapter.Back_Out;

import use_case.Back_Out.BackOutInputBoundary;
import use_case.Back_Out.BackOutInputData;
import view.ViewManager;

public class BackOutController {
    private ViewManager viewManager;


    public BackOutController(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void handleBackButtonClick() {
        // Assuming this method is triggered by the UI button click
        viewManager.switchToPreviousView();
    }
}
