package use_case.generate_static_map;

import java.awt.image.BufferedImage;
import java.util.Map;

/*
https://dev.virtualearth.net/REST/v1/Imagery/Map/AerialWithLabels?pp=40.804000,-74.464460;;1&pp=40.815180,-74.219250;;2&pp=40.881210,-74.168020;;3&pp=40.810830,-74.260250;;4&pp=47.64054,-122.12934;;5&dcl=1&key=An_Fn1bhTQPuLmUi8MsH-5_btdPIKgINhoecH-ayEq0rjUhrnFbXoHHVnwjAli_K
 */
public interface GSMApiDataAccessInterface {
    BufferedImage generateMap(String userCoordinate, Map<Integer, String> eventCoordinates);
}
