package interface_adapter.back_out;

import interface_adapter.ViewManagerModelAdapter;
import use_case.back_out.BackOutOutputBoundary;

public class BackOutPresenter implements BackOutOutputBoundary {
    private ViewManagerModelAdapter viewManagerModelAdapter;

    public BackOutPresenter(ViewManagerModelAdapter viewManagerModelAdapter) {
        this.viewManagerModelAdapter = viewManagerModelAdapter;
    }
    @Override
    public void prepareSuccessView(String viewName) {
        System.out.println("Presenter\nprepare success view, bringing up " + viewName);
        //String lastViewName = viewManagerModelAdapter.getLastViewName();
        viewManagerModelAdapter.setActiveView(viewName);


    }
}
