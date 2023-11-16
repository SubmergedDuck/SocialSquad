package entity.Location;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;


public class CoordinatesToAddressTest {
    private LocationFactory locationFactory = new CommonLocationFactory();

    /**
     * Tests if the correct country was added to the location object
     * @throws IOException api call error
     */
    @Test
    public void correctCountry() throws IOException {
        Location location = locationFactory.makeLocation("(47.64054,-122.12934)");
        assertEquals(location.getCountry(), "United States");
    }


    /**
     * Tests if the correct address was added to the location object.
     * @throws IOException api call error
     */
    @Test
    public void correctAddress() throws IOException{
        Location location = locationFactory.makeLocation("(47.64054,-122.12934)");
        assertEquals(location.getAddress(), "15800 NE One Microsoft Way, Redmond, WA 98052, United States");
    }
}