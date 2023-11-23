package use_case.generate_static_map;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * Data access interface for the API call when generating a static map.
 */
public interface GSMApiDataAccessInterface {
    BufferedImage generateMap(String userCoordinate, Map<Integer, String> eventCoordinates, String size) throws IOException;
}
