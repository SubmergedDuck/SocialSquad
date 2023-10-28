package entity.Location;


import java.awt.*;

public interface LocationFactory {

    Location create(List coordinates, String address, String country);

}
