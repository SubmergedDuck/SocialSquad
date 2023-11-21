package use_case.get_direction;
import java.awt.image.BufferedImage;

public interface GetDirectionAPIDataAccessInterface {
    BufferedImage generateRouteImage(String coordinates1, String coordinates2);
}
