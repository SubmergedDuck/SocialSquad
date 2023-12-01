package use_case.back_out;

public class BackOutInteractor implements BackOutInputBoundary{
    BackOutOutputBoundary presenter;
    public BackOutInteractor(BackOutOutputBoundary presenter) {
        this.presenter = presenter;
    }
    @Override
    public void execute() {
        presenter.prepareSuccessView();

    }
}
