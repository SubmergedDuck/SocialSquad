package interface_adapter.back_out;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewManagerModelAdapter;
import interface_adapter.ViewModel;
import use_case.back_out.BackOutOutputBoundary;

public class BackOutPresenter implements BackOutOutputBoundary {
    private ViewManagerModelAdapter viewManagerModelAdapter;

    public BackOutPresenter(ViewManagerModelAdapter viewManagerModelAdapter) {
        this.viewManagerModelAdapter = viewManagerModelAdapter;
    }
    @Override
    public void prepareSuccessView() {
        String lastViewName = viewManagerModelAdapter.getLastViewName();
        viewManagerModelAdapter.setActiveView(lastViewName);


    }
}
