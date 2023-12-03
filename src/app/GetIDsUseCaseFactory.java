package app;

import interface_adapter.get_ids.GetIDsController;
import interface_adapter.get_ids.GetIDsPresenter;
import interface_adapter.get_ids.GetIDsViewModel;
import use_case.get_ids.GetIDsDataAccessInterface;
import use_case.get_ids.GetIDsInteractor;

public class GetIDsUseCaseFactory {

    public static GetIDsController createGetIDsUseCase(GetIDsViewModel getIDsViewModel, GetIDsDataAccessInterface userDataAccessObject){
        GetIDsPresenter getIDsPresenter = new GetIDsPresenter(getIDsViewModel);
        GetIDsInteractor getIDsInteractor = new GetIDsInteractor(userDataAccessObject,getIDsPresenter);
        GetIDsController getIDsController = new GetIDsController(getIDsInteractor);
        return getIDsController;
    }
}
