package entity.Location;


import java.util.List;

public class CommonLocationFactory implements LocationFactory {
    @Override
    public Location create(List coordinates, String address, String country){
        return new CommonLocation(coordinates, address, country);
    }

    @Override
    public Location makeLocation(String locationString) {
        //TODO: implement this. This method returns a location from a list of coordinates
        return null;
    }

}