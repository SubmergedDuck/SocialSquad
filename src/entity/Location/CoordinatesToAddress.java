package entity.Location;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class CoordinatesToAddress implements CoordinatesToAddressInterface{

    private final String latitude;
    private final String longitude;
    private final String apiKey = "An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K";

    public CoordinatesToAddress(String[] coordinates){
        this.latitude = coordinates[0];
        this.longitude = coordinates[1];
    }

    /**
     * Returns the address that latitude,longitude) is located in.
     * @return the address that the coordinates are located in.
     * @throws IOException error with the api call
     */
    @Override
    public String getAddress() throws IOException {
        HashMap<String, Object> allApiValues = apiCaller();
        HashMap<String, String> addressInfo = extract(allApiValues);
        return addressInfo.get("formattedAddress");
    }

    /**
     * Returns the country that (latitude,longitude) is located in.
     * @return the country that the coordinates are located in.
     * @throws IOException error with the api call
     */
    @Override
    public String getCountry() throws IOException {
        HashMap<String, Object> allApiValues = apiCaller();
        HashMap<String, String> addressInfo = extract(allApiValues);
        return addressInfo.get("countryRegion");
    }

    private HashMap<String, Object> apiCaller() throws IOException {
        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            String apiURL = String.format("http://dev.virtualearth.net/REST/v1/Locations/%s,%s?o=json&key=%s",
                    latitude, longitude, apiKey);
            URL url = new URL(apiURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                resultMap = JSONStringToHashmap.convert(response.toString());
            }
        } catch (IOException e){
            //Might need to do something here in the view if an exception occurs, such as an invalid address being inputted.
            e.printStackTrace();
        }
        return resultMap;
    }
    private static HashMap<String, String> extract(HashMap<String, Object> output){
        JSONArray resourceSets = (JSONArray) output.get("resourceSets");
        JSONObject resources = (JSONObject) resourceSets.get(0);
        JSONArray resourceValues = (JSONArray) resources.get("resources");
        JSONObject resourceValuesJSON = (JSONObject) resourceValues.get(0);
        JSONObject addressInfo = (JSONObject) resourceValuesJSON.get("address");
        HashMap<String, String> addressDetails = new HashMap<>();
        for (String key : addressInfo.keySet()){
            addressDetails.put(key, addressInfo.get(key).toString());
        }
        return addressDetails;
    }
}


