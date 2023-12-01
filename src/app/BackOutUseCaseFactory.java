package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewManagerModelAdapter;
import interface_adapter.back_out.BackOutController;
import interface_adapter.back_out.BackOutPresenter;
import use_case.back_out.BackOutInteractor;
import use_case.back_out.BackOutOutputBoundary;

public class BackOutUseCaseFactory {
    private BackOutUseCaseFactory(){}

    public static BackOutController createBackOutUseCase(ViewManagerModel viewManagerModel) {
        ViewManagerModelAdapter viewManagerModelAdapter = new ViewManagerModelAdapter(viewManagerModel);
        BackOutOutputBoundary backOutPresenter = new BackOutPresenter(viewManagerModelAdapter);
        BackOutInteractor backOutInteractor = new BackOutInteractor(backOutPresenter);
        return new BackOutController(backOutInteractor);
    }
}
