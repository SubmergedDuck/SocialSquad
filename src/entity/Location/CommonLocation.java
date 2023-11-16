package entity.Location;

import java.util.List;

public class CommonLocation implements Location {

    private final String[] coordinates;
    private final String address;
    private final String country;


    public CommonLocation(String[] coordinates, String address, String country){
        this.coordinates = coordinates;
        this.address = address;
        this.country = country;

    }

    @Override
    public String[] getCoordinates() {
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

    public String toString(){
        return "Country: " + getCountry() + "Province/City: " + getAddress(); //TODO: let API to return an approximate address instead of a specific detailed one
    }
}
