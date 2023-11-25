package use_case.get_direction;
import java.awt.image.BufferedImage;

/**
 * API data access interface for the GetDirection use case.
 */
public interface GetDirectionAPIDataAccessInterface {
    BufferedImage generateRouteImage(String coordinates1, String coordinates2);
}
