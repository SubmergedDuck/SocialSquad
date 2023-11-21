package use_case.get_direction;

import java.awt.image.BufferedImage;

public class GetDirectionOutputData {
    private final BufferedImage directionImage;

    public GetDirectionOutputData(BufferedImage directionImage){
        this.directionImage = directionImage;
    }

    BufferedImage getDirectionImage(){return this.directionImage;}
}
