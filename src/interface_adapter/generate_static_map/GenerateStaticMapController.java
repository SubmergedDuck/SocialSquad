package interface_adapter.generate_static_map;

import entity.Users.User;
import use_case.generate_static_map.GSMInputBoundary;
import use_case.generate_static_map.GSMInputData;

import java.io.IOException;

public class GenerateStaticMapController {
    private final GSMInputBoundary interactor;

    public GenerateStaticMapController(GSMInputBoundary interactor){
        this.interactor = interactor;
    }

    public void execute(String[] coordinates, int maxPins, int width, int height) throws IOException {
        GSMInputData inputData = new GSMInputData(coordinates,maxPins,width,height);
        interactor.execute(inputData);
    }
}
