package entity.Location;


import java.io.IOException;

public interface LocationFactory {

    Location create(String[] coordinates, String address, String country);

    Location makeLocation(String locationString) throws IOException;
    // This method returns a location from a list of coordinates
}
