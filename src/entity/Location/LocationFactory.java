package entity.Location;


import java.awt.*;

public interface LocationFactory {

    Location create(List coordinates, String address, String country);

    Location makeLocation(String locationString);
    // This method returns a location from a list of coordinates
}