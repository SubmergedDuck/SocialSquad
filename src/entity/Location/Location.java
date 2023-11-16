package entity.Location;


import java.util.List;

public interface Location {

        List getCoordinates(); //TODO: make sure the list returned can be used by the API to instantiate a Location. This information is needed in the database for storing and reading. When reading, the userDAO should be able to instantiate a Location using this information.
        String getAddress();
        String getCountry();
        String toString();

}
