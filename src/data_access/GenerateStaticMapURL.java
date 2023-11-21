package data_access;

import use_case.generate_static_map.GSMApiDataAccessInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/*
https://dev.virtualearth.net/REST/v1/Imagery/Map/AerialWithLabels?pp=40.804000,-74.464460;;1&pp=40.815180,-74.219250;;2&pp=40.881210,-74.168020;;3&pp=40.810830,-74.260250;;4&pp=47.64054,-122.12934;;5&dcl=1&key=An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K
 */
public class GenerateStaticMapURL implements GSMApiDataAccessInterface {
    private final String api_key = "An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K";
    @Override
    public BufferedImage generateMap(String userCoordinate, Map<Integer, String> eventCoordinates) throws IOException {
        try {
            String currentLink = "https://dev.virtualearth.net/REST/v1/Imagery/Map/AerialWithLabels?";
            for (Integer pinNum : eventCoordinates.keySet()) {
                String coordinates = eventCoordinates.get(pinNum);
                String addedParameter = String.format("pp=%s;;%d&", coordinates, pinNum);
                currentLink = currentLink + addedParameter;
            }
            String finalPart = String.format("dcl=1&key=%s", api_key);
            currentLink = currentLink + finalPart;
            URL url = new URL(currentLink);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200){
                InputStream inputStream = connection.getInputStream();
                BufferedImage image = ImageIO.read(inputStream);
                return image;
            }
        } catch (IOException e){
            return null;
        }
        return null;
    }
}
