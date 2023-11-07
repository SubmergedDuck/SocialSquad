package use_case.search_event;

import entity.Location.Location;

import java.time.LocalDateTime;

public interface SearchEventDataAccessInterface {
    Integer returnByName(String name);
    Integer returnByLocation(Location location);
    Integer returnByTime(LocalDateTime start, LocalDateTime end);
    Integer returnByType(String type);
}
