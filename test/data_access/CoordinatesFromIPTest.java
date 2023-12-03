package data_access;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CoordinatesFromIPTest {
    @Test
    public void getCoordinates() throws IOException {
        String[] coordinates = CoordinatesFromIP.getCoordinates();
        assertEquals(2, coordinates.length);
    }
}