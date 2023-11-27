package interface_adapter.generate_static_map;

import java.awt.image.BufferedImage;

public class GenerateStaticMapState {
    private BufferedImage generatedMap;

    public GenerateStaticMapState(GenerateStaticMapState copy){
        this.generatedMap = copy.generatedMap;
    }

    public GenerateStaticMapState(){}

    public BufferedImage getGeneratedMap(){return this.generatedMap;}

    public void setGeneratedMap(BufferedImage generatedMap){
        this.generatedMap = generatedMap;
    }
}
