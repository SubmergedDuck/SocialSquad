package entity.Location;

import entity.Events.Event;

public interface DistanceCalculatorInterface {
    public boolean within2KM(Event event);
}
