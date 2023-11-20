package interface_adapter.Back_Out;

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
