package app;

import data_access.GenerateStaticMapBody;
import interface_adapter.generate_static_map.GenerateStaticMapController;
import interface_adapter.generate_static_map.GenerateStaticMapPresenter;
import interface_adapter.generate_static_map.GenerateStaticMapViewModel;
import use_case.common_interfaces.MapUserDataAccessInterface;
import use_case.generate_static_map.GSMEventDataAccessInterface;
import use_case.generate_static_map.GSMInteractor;

public class GenerateStaticMapUseCaseFactory {

    public static GenerateStaticMapController createGenerateStaticMapUseCase(GenerateStaticMapViewModel generateStaticMapViewModel,
                                                                             GSMEventDataAccessInterface eventDataAccessObject,
                                                                             MapUserDataAccessInterface userDataAccessObject){
        GenerateStaticMapPresenter generateStaticMapPresenter = new GenerateStaticMapPresenter(generateStaticMapViewModel);
        GSMInteractor gsmInteractor = new GSMInteractor(new GenerateStaticMapBody(),userDataAccessObject, eventDataAccessObject, generateStaticMapPresenter);
        GenerateStaticMapController generateStaticMapController = new GenerateStaticMapController(gsmInteractor);
        return generateStaticMapController;
    }
}