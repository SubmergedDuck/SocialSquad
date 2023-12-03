package use_case.generate_static_map;

import entity.Events.Event;

import java.awt.image.BufferedImage;
import java.util.Map;

public class GSMOutputData {
    private final BufferedImage generatedMap;

    /**
     * Constructor for GSM output data.
     * @param generatedMap the image of the generated map
     */
    public GSMOutputData(BufferedImage generatedMap){
        this.generatedMap = generatedMap;
    }

    /**
     * Provides the generated static map with all the pins
     * @return the map created by the API call.
     */
    public BufferedImage getGeneratedMap(){return this.generatedMap;}
}