package entity.Location;


import java.util.*;

public interface LocationFactory {

    Location create(ArrayList coordinates, String address, String country);

    Location makeLocation(String locationString);
    // This method returns a location from a list of coordinates
}
