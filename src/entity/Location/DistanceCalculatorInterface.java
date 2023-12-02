package entity.Location;

import entity.Events.Event;

import java.io.IOException;

public interface DistanceCalculatorInterface {
    public boolean within2KM(String[] strCoord, Event event) throws IOException;
}
