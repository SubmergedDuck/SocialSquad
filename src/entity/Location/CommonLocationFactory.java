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

        //Temporary for testing (NOTE: locaitonString are the coordinates for here):
        String address = "123 Street";
        String[] strCoordinates = locationString.substring(1, locationString.length() - 1).split(",");
        Double[] coordinates = {Double.valueOf(strCoordinates[0]), Double.valueOf(strCoordinates[1])};
        String country = "Canada";
        return create(List.of(coordinates), address, country);
    }

}
