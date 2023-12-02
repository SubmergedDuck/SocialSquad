package interface_adapter.back_out;

import use_case.back_out.BackOutInputBoundary;
import use_case.back_out.BackOutInteractor;

public class BackOutController {
    final BackOutInputBoundary interactor;

    public BackOutController(BackOutInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        this.interactor.execute();
    }
}
