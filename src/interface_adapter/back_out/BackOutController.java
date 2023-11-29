package interface_adapter.back_out;

import use_case.back_out.BackOutInteractor;

public class BackOutController {
    final BackOutInteractor interactor;

    public BackOutController(BackOutInteractor interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        this.interactor.execute();
    }
}
