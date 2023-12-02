package interface_adapter.get_direction;

import java.awt.image.BufferedImage;

public class GetDirectionState {
    private BufferedImage generatedImage;

    public GetDirectionState(GetDirectionState copy){
        this.generatedImage = copy.generatedImage;
    }

    public GetDirectionState(){}

    public BufferedImage getGeneratedImage(){return this.generatedImage;}

    public void setGeneratedImage(BufferedImage generatedImage){
        this.generatedImage = generatedImage;
    }
}
