package use_case.get_direction;

import java.awt.image.BufferedImage;

public class GetDirectionInteractor implements GetDirectionInputBoundary {
    private final GetDirectionOutputBoundary getDirectionPresenter;
    private final GetDirectionEventDataAccessInterface eventDataAccessObject;
    private final GetDirectionUserDataAccessInterface userDataAccessObject;
    private final GetDirectionAPIDataAccessInterface apiDataAccessObject;

    public GetDirectionInteractor(GetDirectionOutputBoundary getDirectionPresenter,
                                  GetDirectionEventDataAccessInterface eventDataAccessObject,
                                  GetDirectionUserDataAccessInterface userDataAccessObject,
                                  GetDirectionAPIDataAccessInterface apiDataAccessObject){
        this.getDirectionPresenter = getDirectionPresenter;
        this.eventDataAccessObject = eventDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.apiDataAccessObject = apiDataAccessObject;
    }

    @Override
    public void execute(GetDirectionInputData inputData) {
        String[] userCoordinates = userDataAccessObject.getCoordinates(inputData.getUsername());
        String[] eventCoordinates = eventDataAccessObject.getEventCoordinates(inputData.getEventID());
        String coordinates1 = userCoordinates[0] + "," + userCoordinates[1];
        String coordinates2 = eventCoordinates[0] + "," + eventCoordinates[1];
        BufferedImage directionImage = apiDataAccessObject.generateRouteImage(coordinates1, coordinates2);
        GetDirectionOutputData outputData = new GetDirectionOutputData(directionImage);
        getDirectionPresenter.prepareView(outputData);
    }
}
