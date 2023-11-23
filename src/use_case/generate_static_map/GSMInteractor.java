package use_case.generate_static_map;

import entity.Events.Event;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class GSMInteractor implements GSMInputBoundary{
    private final GSMApiDataAccessInterface APIDataAccessObject;
    private final GSMUserDataAccessInterface userDataAccessObject;
    private final GSMEventDataAccessInterface eventDataAccessObject;
    private final GSMOutputBoundary presenter;

    /**
     * Constructor for GSMInteractor
     * @param APIDataAccessObject data access object for the API call
     * @param userDataAccessObject user data access object
     * @param eventDataAccessObject event data access object
     * @param presenter presenter for the GSM use case.
     */
    public GSMInteractor(GSMApiDataAccessInterface APIDataAccessObject,
                         GSMUserDataAccessInterface userDataAccessObject, GSMEventDataAccessInterface eventDataAccessObject,
                         GSMOutputBoundary presenter){
        this.APIDataAccessObject = APIDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.eventDataAccessObject = eventDataAccessObject;
        this.presenter = presenter;
    }

    /**
     * Gathers the coordinate information from the event and user DAOs and makes an API call to generate a map image and send it to the presenter.
     * @param inputData data used when generating the image
     * @throws IOException if an error occurs with the API call
     */
    @Override
    public void execute(GSMInputData inputData) throws IOException {
        String[] userCoordinates = userDataAccessObject.getUserCoordinates(inputData.getUsername());
        String formattedCoordinates = userCoordinates[0] + "," + userCoordinates[1];
        HashMap<Integer, Event> numToEvent = eventDataAccessObject.getEvents(inputData.getTotalPins());
        HashMap<Integer, String> numToCoordinates = numToCoordinates(numToEvent);
        String imageSize = inputData.getWidth() + "," + inputData.getHeight();
        BufferedImage generatedImage = APIDataAccessObject.generateMap(formattedCoordinates, numToCoordinates, imageSize);
        GSMOutputData outputData = new GSMOutputData(generatedImage, numToEvent);
        presenter.prepareView(outputData);
    }
    private HashMap<Integer,String> numToCoordinates(HashMap<Integer, Event> numToEvent){
        HashMap<Integer, String> coordinateNum = new HashMap<>();
        for (Integer key : numToEvent.keySet()){
            String[] eventCoordinates = numToEvent.get(key).getLocation().getCoordinates();
            String coordinatesStr = eventCoordinates[0] + "," + eventCoordinates[1];
            coordinateNum.put(key, coordinatesStr);
        }
        return coordinateNum;
    }
}
