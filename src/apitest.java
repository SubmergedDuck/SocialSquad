import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class apitest {

    public static void main(String[] args) {
        try {
            // Note: "47.64054,-122.12934" is the point in the map and "An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K" is the key
            String apiUrl = "http://dev.virtualearth.net/REST/v1/Locations/47.64054,-122.12934?o=json&key=An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K";

            // Create a URL object with the API endpoint
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the response code is 200 (OK)
            if (responseCode == 200) {
                // Read the response from the API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Print the API response
                System.out.println("API Response:\n" + response.toString());
            } else {
                System.out.println("API Request failed with response code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}