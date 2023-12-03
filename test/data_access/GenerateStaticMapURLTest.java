package data_access;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class GenerateStaticMapURLTest {
    @Test
    public void getMap() throws IOException {
        String[] coordinates = CoordinatesFromIP.getCoordinates();
        assert(coordinates.length == 2);
        String formattedCoordinates = coordinates[0] + "," + coordinates[1];
        GenerateStaticMapURL newGenerator = new GenerateStaticMapURL();
        BufferedImage image = newGenerator.generateMap(formattedCoordinates, new HashMap<Integer, String>(), "500,500");
        assert(!(image == null));
    }
}