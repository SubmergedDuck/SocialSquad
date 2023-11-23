package data_access;

import use_case.generate_static_map.GSMApiDataAccessInterface;

import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.DataOutputStream;
import java.util.Map;
import java.net.URL;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * Used for generating a static map using a POST request.
 */
public class GenerateStaticMapBody implements GSMApiDataAccessInterface{
    private static String api_key = "An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K";
    private static int maxPins = 100; //Maximum pins allowed
    @Override
    public BufferedImage generateMap(String userCoordinate, Map<Integer, String> eventCoordinates, String size) throws IOException {
        try {
            String apiUrl = String.format("https://dev.virtualearth.net/REST/v1/Imagery/Map/Road/?mapSize=%s&dcl=1&key=%s", size, api_key);
            int totalEventPins = Math.min(maxPins - 1, eventCoordinates.size());
            String currentBody = String.format("pp=%s;45;you\n", userCoordinate);
            for (Integer key : eventCoordinates.keySet()) { //Creating each line for the POST body request
                String addedParameter = "";
                if (key == totalEventPins) {
                    addedParameter = String.format("pp=%s;;%d", eventCoordinates.get(key), key);
                } else {
                    addedParameter = String.format("pp=%s;;%d\n", eventCoordinates.get(key), key);
                }
                currentBody = currentBody + addedParameter;
            }
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //Setting up properties for the POST request
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(totalEventPins + 1));
            //Providing the POST body.
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(currentBody);
            wr.flush();
            wr.close();

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                BufferedImage image = ImageIO.read(inputStream); //Generated static image.
                return image;
            }
        } catch (IOException e){
            return null; //View needs to know how to deal with the situation when no image is returned.
        }
        return null;
    }
}

