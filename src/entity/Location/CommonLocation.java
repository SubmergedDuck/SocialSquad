package entity.Location;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CommonLocation implements Location {

    private final ArrayList coordinates;
    private final String address;
    private final String country;


    public CommonLocation(ArrayList coordinates, String address, String country){
        this.coordinates = coordinates;
        this.address = address;
        this.country = country;

    }

    @Override
    public List getCoordinates() {
        return coordinates;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getCountry() {
        return country;
    }
}
