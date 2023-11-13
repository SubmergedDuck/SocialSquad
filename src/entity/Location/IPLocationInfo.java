package entity.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * This class is responsible for gathering the location information from one's ip using an API call.
 */
public class IPLocationInfo {

    /**
     * Uses a user's ip and finds the coordinates from the ip.
     * @return returns the coordinates of the location given by the user's ip.
     * @throws IOException error occurs with the api call. Here, empty coordinates would be returned.
     */
    public static String[] getCoordinates() throws IOException {
        String[] result = {};
        try {
            URL url = new URL("http://ip-api.com/csv/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    //Example Response "success,Canada,CA,ON,Ontario,Toronto,M5S,43.6638,-79.3999,America/Toronto,University of Toronto,University of Toronto,AS239 University of Toronto,138.51.77.88"
                    response.append(inputLine);
                }
                in.close();
                String[] locationInfo = response.toString().split(",");
                String latitude = locationInfo[7];
                String longitude = locationInfo[8];
                result = new String[]{latitude, longitude};
            }
        } catch (IOException e){
            return new String[]{}; //Empty list for now, might changing depending on how we decide to deal with exceptions.
        }
        return result;
    }
}
