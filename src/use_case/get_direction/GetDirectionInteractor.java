package use_case.get_direction;

import java.awt.image.BufferedImage;

/**
 * Interactor for the get directions use case
 */
public class GetDirectionInteractor implements GetDirectionInputBoundary {
    private final GetDirectionOutputBoundary getDirectionPresenter;
    private final GetDirectionEventDataAccessInterface eventDataAccessObject;
    private final GetDirectionUserDataAccessInterface userDataAccessObject;
    private final GetDirectionAPIDataAccessInterface apiDataAccessObject;

    /**
     * Constructor for GetDirectionInteractor
     * @param getDirectionPresenter the presenter for the get directions use case
     * @param eventDataAccessObject event DAO, used for getting the location of a specified event
     * @param userDataAccessObject user DAO, used for getting the location of a specific user.
     * @param apiDataAccessObject calls the API to generate a route image.
     */
    public GetDirectionInteractor(GetDirectionOutputBoundary getDirectionPresenter,
                                  GetDirectionEventDataAccessInterface eventDataAccessObject,
                                  GetDirectionUserDataAccessInterface userDataAccessObject,
                                  GetDirectionAPIDataAccessInterface apiDataAccessObject){
        this.getDirectionPresenter = getDirectionPresenter;
        this.eventDataAccessObject = eventDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.apiDataAccessObject = apiDataAccessObject;
    }

    /**
     * Gets the location data from the DAO and calls the API to provide a route image. This route image is returned to the presenter.
     * @param inputData input data containing the selected username and event ID
     */
    @Override
    public void execute(GetDirectionInputData inputData) {
        String[] userCoordinates = userDataAccessObject.getCoordinates(inputData.getUsername());
        String[] eventCoordinates = eventDataAccessObject.getEventCoordinates(inputData.getEventID());
        String coordinates1 = userCoordinates[0] + "," + userCoordinates[1];
        String coordinates2 = eventCoordinates[0] + "," + eventCoordinates[1];
        String imageSize = inputData.getWidth() + "," + inputData.getHeight();
        BufferedImage directionImage = apiDataAccessObject.generateRouteImage(coordinates1,coordinates2,imageSize);
        GetDirectionOutputData outputData = new GetDirectionOutputData(directionImage);
        getDirectionPresenter.prepareView(outputData);
    }
}
