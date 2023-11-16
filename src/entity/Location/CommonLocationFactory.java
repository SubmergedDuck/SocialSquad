package entity.Location;
import java.io.IOException;

public class CommonLocationFactory implements LocationFactory {

    /**
     * Creates a location
     * @param coordinates coordinates of the location
     * @param address address of the location
     * @param country country of the location
     * @return location object
     */
    @Override
    public Location create(String[] coordinates, String address, String country){
        return new CommonLocation(coordinates, address, country);
    }

    /**
     * Returns a location object from the given coordinates.
     * @param locationString coordinates
     * @return a created location object from the given coordinates
     * @throws IOException api error
     */
    @Override

    public Location makeLocation(String locationString) throws IOException {
        //locationString is in the format of "(latitude,longitude)"
        String[] strCoordinates = locationString.substring(1, locationString.length() - 1).split(",");
        CoordinatesToAddressInterface converter = new CoordinatesToAddress(strCoordinates);
        String country = converter.getCountry();
        String address = converter.getAddress();
        Location location = create(strCoordinates, address, country);
        return location;
    }
}
