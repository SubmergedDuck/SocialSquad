package entity.Location;

import entity.Events.Event;

public interface DistanceCalculatorInterface {
    public boolean within2KM(String[] strCoord, Event event) throws Exception;
}
