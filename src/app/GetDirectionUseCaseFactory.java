package app;

import data_access.GenerateRoute;
import interface_adapter.get_direction.GetDirectionController;
import interface_adapter.get_direction.GetDirectionPresenter;
import interface_adapter.get_direction.GetDirectionViewModel;
import use_case.common_interfaces.MapUserDataAccessInterface;
import use_case.get_direction.GetDirectionEventDataAccessInterface;
import use_case.get_direction.GetDirectionInteractor;

public class GetDirectionUseCaseFactory {
    public static GetDirectionController createGetDirectionUseCase(GetDirectionViewModel getDirectionViewModel, GetDirectionEventDataAccessInterface eventDataAccessObject,
                                                                   MapUserDataAccessInterface userDataAccessObject){
        GetDirectionPresenter presenter = new GetDirectionPresenter(getDirectionViewModel);
        GetDirectionInteractor getDirectionInteractor = new GetDirectionInteractor(presenter,eventDataAccessObject,userDataAccessObject, new GenerateRoute());
        GetDirectionController getDirectionController = new GetDirectionController(getDirectionInteractor);
        return getDirectionController;
    }
}