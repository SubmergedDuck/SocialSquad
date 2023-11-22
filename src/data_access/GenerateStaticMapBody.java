package data_access;

import use_case.generate_static_map.GSMApiDataAccessInterface;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GenerateStaticMapBody implements GSMApiDataAccessInterface {
    private final String api_key = "An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K";
    private final int maxPins = 100;
    @Override
    public BufferedImage generateMap(String userCoordinate, Map<Integer, String> eventCoordinates) throws IOException {
        int totalEventPins = Math.min(maxPins - 1,eventCoordinates.size());
        String bodyHeader = "Content-Length:" + (totalEventPins + 1);
        ArrayList<String> pointsBody = new ArrayList<>();
        for (Integer key : eventCoordinates.keySet()){
            if (key < totalEventPins){
                String addedParameter = String.format("pp=%s;1;%d",eventCoordinates.get(key),key);
                pointsBody.add(addedParameter);
            }
        }

        return null;
    }
}
