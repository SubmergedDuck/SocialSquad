package use_case.generate_static_map;

import entity.Events.Event;

import java.awt.image.BufferedImage;
import java.util.Map;

public class GSMOutputData {
    private final BufferedImage generatedMap;
    private final Map<Integer, Event> pinToEvent;

    /**
     * Constructor for GSM output data.
     * @param generatedMap the image of the generated map
     * @param pinToEvent
     */
    public GSMOutputData(BufferedImage generatedMap, Map<Integer, Event> pinToEvent){
        this.generatedMap = generatedMap;
        this.pinToEvent = pinToEvent;
    }
    BufferedImage getGeneratedMap(){return this.generatedMap;}

    Map<Integer, Event> pinToEvent(){return this.pinToEvent;}
}
