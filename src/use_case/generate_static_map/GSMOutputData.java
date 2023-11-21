package use_case.generate_static_map;

import entity.Events.Event;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GSMOutputData {
    private final BufferedImage generatedMap;
    private final HashMap<Integer, Event> pinToEvent;
    public GSMOutputData(BufferedImage generatedMap, HashMap<Integer, Event> pinToEvent){
        this.generatedMap = generatedMap;
        this.pinToEvent = pinToEvent;
    }
    BufferedImage getGeneratedMap(){return this.generatedMap;}

    HashMap<Integer, Event> pinToEvent(){return this.pinToEvent;}
}
