package interface_adapter.Back_Out;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import use_case.Back_Out.BackOutOutputBoundary;
import use_case.Back_Out.BackOutOutputData;
import view.ViewManager;

public class BackOutPresenter implements BackOutOutputBoundary {
    private final BackOutViewModel backOutViewModel;

   private final ViewManager viewManager;

    public BackOutPresenter(ViewManager viewManager, BackOutViewModel backOutViewModel){
        this.viewManager = viewManager;
        this.backOutViewModel = backOutViewModel;
    }

    @Override
    public void prepareSuccessView(BackOutOutputData user) {
       viewManager.switchToPreviousView();


    }

    @Override
    public void prepareFailView(String error) {

    }
}
