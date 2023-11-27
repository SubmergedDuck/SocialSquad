package data_access;

import use_case.get_direction.GetDirectionAPIDataAccessInterface;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

public class GenerateRoute implements GetDirectionAPIDataAccessInterface {
    private final String apiKey = "An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K";
    @Override
    public BufferedImage generateRouteImage(String coordinates1, String coordinates2, String imageSize) {
        try {
            String apiURL = String.format("https://dev.virtualearth.net/REST/v1/Imagery/Map/Road/Routes?wp.0=%s;64;1&wp.1=%s;66;2&mapSize=%s&key=%s",
                    coordinates1, coordinates2, imageSize, apiKey);
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200){
                InputStream inputStream = connection.getInputStream();
                BufferedImage image = ImageIO.read(inputStream);
                return image;
            }
        } catch (IOException e){
            return new BufferedImage();
        }
        return new BufferedImage();
    }
}
