package data_access;

import org.junit.Test;
import use_case.common_interfaces.GetCoordinatesIP;

import java.io.IOException;

import static org.junit.Assert.*;

public class CoordinatesFromIPTest {
    @Test
    public void getCoordinates() throws IOException {
        GetCoordinatesIP getCoordinatesIP = new CoordinatesFromIP();
        String[] coordinates = getCoordinatesIP.getCoordinates();
        assertEquals(2, coordinates.length);
    }
}