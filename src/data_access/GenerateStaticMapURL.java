package data_access;

import use_case.generate_static_map.GSMApiDataAccessInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class GenerateStaticMapURL implements GSMApiDataAccessInterface {
    private final int maxPins = 18; //Maximum pin amount allowed for an HTTP GET method.
    private final String api_key = "An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K";

    @Override
    public BufferedImage generateMap(String userCoordinate, Map<Integer, String> eventCoordinates) throws IOException {
        try {
            String currentLink = "https://dev.virtualearth.net/REST/v1/Imagery/Map/AerialWithLabels?";
            String userParameter = String.format("pp=%s&", userCoordinate);
            currentLink = currentLink + userParameter;
            for (Integer pinNum : eventCoordinates.keySet()) {
                if (pinNum < maxPins - 1) { //Want a maximum of 17 pinpoints for events. The remaining one will go to the user.
                    String coordinates = eventCoordinates.get(pinNum);
                    String addedParameter = String.format("pp=%s;;%d&", coordinates, pinNum);
                    currentLink = currentLink + addedParameter;
                }
            }
            String finalPart = String.format("dcl=1&key=%s", api_key);
            currentLink = currentLink + finalPart;
            URL url = new URL(currentLink);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                BufferedImage image = ImageIO.read(inputStream);
                return image;
            }
        } catch (IOException e) {
            //If null is returned, the view model needs to appropriately deal with this situation and tell the user that something went wrong.
            return null;
        }
        return null;
    }
}
