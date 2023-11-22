package use_case.generate_static_map;

import entity.Events.Event;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class GSMInteractor implements GSMInputBoundary{
    private final GSMApiDataAccessInterface APIDataAccessObject;
    private final GSMUserDataAccessInterface userDataAccessObject;
    private final GSMEventDataAccessInterface eventDataAccessInterface;
    private final GSMOutputBoundary presenter;

    public GSMInteractor(GSMApiDataAccessInterface APIDataAccessObject,
                         GSMUserDataAccessInterface userDataAccessInterface, GSMEventDataAccessInterface eventDataAccessInterface,
                         GSMOutputBoundary presenter){
        this.APIDataAccessObject = APIDataAccessObject;
        this.userDataAccessObject = userDataAccessInterface;
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.presenter = presenter;
    }

    @Override
    public void execute(GSMInputData inputData) throws IOException {
        String[] userCoordinates = userDataAccessObject.getUserCoordinates(inputData.getUsername());
        String formattedCoordinates = userCoordinates[0] + "," + userCoordinates[1];
        HashMap<Integer, Event> numToEvent = eventDataAccessInterface.getEvents(inputData.getTotalPins());
        HashMap<Integer, String> numToCoordinates = numToCoordinates(numToEvent);
        BufferedImage generatedImage = APIDataAccessObject.generateMap(formattedCoordinates, numToCoordinates);
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
