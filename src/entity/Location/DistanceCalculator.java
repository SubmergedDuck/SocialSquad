package entity.Location;

import entity.Events.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

public class DistanceCalculator implements DistanceCalculatorInterface {
    public DistanceCalculator() {
        ;
    }

    @Override
    public boolean within2KM(String[] strCoord, Event event) throws IOException {
        return calculateDistance(strCoord, event) <= 2;
    }

    public double calculateDistance(String[] startCoord, Event event) throws IOException {
        double startLat = Double.valueOf(Arrays.stream(startCoord).toList().get(0));
        double startLon = Double.valueOf(Arrays.stream(startCoord).toList().get(1));
        String[] endCoord = event.getLocation().getCoordinates();
        double endLat = Double.valueOf(Arrays.stream(endCoord).toList().get(0));
        double endLon = Double.valueOf(Arrays.stream(endCoord).toList().get(1));

        String allApiValues = apiCaller(startLat, startLon, endLat, endLon);
        return extractDistance(allApiValues);
    }


    private String apiCaller(double startLat, double startLon, double endLat, double endLon) throws IOException{
        try {
                String apiKey = "An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K";
                String bingMapsApiUrl = "https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix" +
                        "?origins=" + startLat + "," + startLon +
                        "&destinations=" + endLat + "," + endLon +
                        "&travelMode=driving&startTime=2023-10-30T08:00:00&timeUnit=minute&key=" + apiKey;
                // Example URL: dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?origins=37.7749,-122.4194;34.0522,-118.2437;40.7128,-74.0060&destinations=40.7128,-74.0060;41.8781,-87.6298;34.0522,-118.2437&travelMode=driving&startTime=2023-10-30T08:00:00&timeUnit=minute&key=YourBingMapsKey

                URL url = new URL(bingMapsApiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    return response.toString();
                }
            } catch (IOException e) {
                throw new RuntimeException("API call failed");
            }
        return null;
    }


    private double extractDistance(String allApiValues) {
        JSONObject jsonResponse = new JSONObject(allApiValues);
        JSONArray resourceSets = jsonResponse.getJSONArray("resourceSets");
        JSONObject resourceSet = resourceSets.getJSONObject(0);
        JSONArray resources = resourceSet.getJSONArray("resources");
        JSONObject results = resources.getJSONObject(0);
        JSONArray result = results.getJSONArray("results");
        JSONObject details = result.getJSONObject(0);
        Number distance = (Number) details.get("travelDistance");

        return distance.doubleValue();
    }

    public static void main(String[] args) throws Exception {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        String jsonResponse = distanceCalculator.apiCaller(43.665510,-79.387280,43.645531,-79.380348);
        double distance = distanceCalculator.extractDistance(jsonResponse);
        System.out.println(distance);
    }
}
