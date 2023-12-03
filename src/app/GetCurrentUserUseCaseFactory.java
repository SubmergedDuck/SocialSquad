package app;

import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserPresenter;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import use_case.get_current_user.CurrentUserDataAccessInterface;
import use_case.get_current_user.GetCurrentUserInteractor;

public class GetCurrentUserUseCaseFactory {

    public static GetCurrentUserController createGetCurrentUserUseCase(GetCurrentUserViewModel getCurrentUserViewModel, CurrentUserDataAccessInterface
                                          currentUserDataAccessObject){
        GetCurrentUserPresenter getCurrentUserPresenter = new GetCurrentUserPresenter(getCurrentUserViewModel);
        GetCurrentUserInteractor getCurrentUserInteractor = new GetCurrentUserInteractor(getCurrentUserPresenter, currentUserDataAccessObject);
        GetCurrentUserController getCurrentUserController = new GetCurrentUserController(getCurrentUserInteractor);
        return getCurrentUserController;
    }

    /*
    GetCurrentUserPresenter getCurrentUserPresenter = new GetCurrentUserPresenter(getCurrentUserViewModel);
        GetCurrentUserInteractor getCurrentUserInteractor = new GetCurrentUserInteractor(getCurrentUserPresenter, currentUserDAO);
        GetCurrentUserController getCurrentUserController1 = new GetCurrentUserController(getCurrentUserInteractor);
     */
}
