package use_case.get_direction;

import java.awt.image.BufferedImage;

/**
 * Output data for GetDirection.
 */
public class GetDirectionOutputData {
    private final BufferedImage directionImage;

    /**
     * Constructor for GetDirectionOutputData
     * @param directionImage the image of the route between the user and event.
     */
    public GetDirectionOutputData(BufferedImage directionImage){
        this.directionImage = directionImage;
    }

    /**
     * The generated direction map from the API
     * @return provides the map from the API call.
     */
    public BufferedImage getDirectionImage(){return this.directionImage;}
}
