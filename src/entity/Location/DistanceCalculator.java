package entity.Location;

import entity.Events.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class DistanceCalculator implements DistanceCalculatorInterface {
    public DistanceCalculator() {
        ;
    }

    @Override
    public boolean within2KM(String[] strCoord, Event event) throws Exception {
        if (calculateDistance(strCoord, event) <= 2) {
            return true;
        } else {
            return false;
        }
    }

    public double calculateDistance(String[] startCoord, Event event) throws Exception {
        double startLat = Double.valueOf(Arrays.stream(startCoord).toList().get(0));
        double startLon = Double.valueOf(Arrays.stream(startCoord).toList().get(1));
        String[] endCoord = event.getLocation().getCoordinates();
        double endLat = Double.valueOf(Arrays.stream(endCoord).toList().get(0));
        double endLon = Double.valueOf(Arrays.stream(endCoord).toList().get(1));

        String allApiValues = apiCaller(startLat, startLon, endLat, endLon);
        double distance = extractDistance(allApiValues);
        return distance;
    }


    private String apiCaller(double startLat, double startLon, double endLat, double endLon) throws Exception{
        HashMap<String, Object> result = new HashMap<>();
        try {
                String apiKey = "An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K";
                String bingMapsApiUrl = "https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix" +
                        "?origins=" + startLat + "," + startLon +
                        "&destinations=" + endLat + "," + endLon +
                        "&travelMode=driving&startTime=2023-10-30T08:00:00&timeUnit=minute&key=" + apiKey;
                //Example URL: dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?origins=37.7749,-122.4194;34.0522,-118.2437;40.7128,-74.0060&destinations=40.7128,-74.0060;41.8781,-87.6298;34.0522,-118.2437&travelMode=driving&startTime=2023-10-30T08:00:00&timeUnit=minute&key=YourBingMapsKey

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
//        JSONArray resourceSets = (JSONArray) output.get("resourceSets");
//        JSONObject resources = (JSONObject) resourceSets.get(0);
//        JSONArray resourceValues = (JSONArray) resources.get("resources");
//        JSONObject resourceValuesJSON = (JSONObject) resourceValues.get(0);
//        JSONObject addressInfo = (JSONObject) resourceValuesJSON.get("address");
//        HashMap<String, String> addressDetails = new HashMap<>();
//        for (String key : addressInfo.keySet()){
//            addressDetails.put(key, addressInfo.get(key).toString());
//        }
//        return addressDetails;
        JSONObject jsonResponse = new JSONObject(allApiValues);
        JSONArray resultSet = (JSONArray) jsonResponse.get("results");
        JSONObject resultDest1 = (JSONObject) resultSet.get(0);
        JSONObject distance = (JSONObject) resultDest1.get("travelDistance");
        return Double.valueOf(distance.toString());
    }
}
