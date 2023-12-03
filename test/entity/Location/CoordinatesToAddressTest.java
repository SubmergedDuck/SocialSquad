package entity.Location;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;


public class CoordinatesToAddressTest {
    private LocationFactory locationFactory = new CommonLocationFactory();
    private Location location;
    @Before
    public void init() throws IOException {
         location = locationFactory.makeLocation("(47.64054,-122.12934)");
    }
    /**
     * Tests if the correct country was added to the location object
     */
    @Test
    public void correctCountry(){
        assertEquals(location.getCountry(), "United States");
    }


    /**
     * Tests if the correct address was added to the location object.
     */
    @Test
    public void correctAddress(){
        assertEquals(location.getAddress(), "15800 NE One Microsoft Way, Redmond, WA 98052, United States");
    }
}