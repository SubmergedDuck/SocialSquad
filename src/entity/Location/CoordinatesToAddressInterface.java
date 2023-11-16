package entity.Location;

import java.io.IOException;

public interface CoordinatesToAddressInterface {
    String getAddress() throws IOException;

    String getCountry() throws IOException;
}
