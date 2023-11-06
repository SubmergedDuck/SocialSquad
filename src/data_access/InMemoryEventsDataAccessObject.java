package data_access;

import entity.Location.CommonLocation;
import entity.Location.Location;
import use_case.search_event.SearchEventDataAccessInterface;
import use_case.search_event.SearchEventInteractor;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class InMemoryEventsDataAccessObject implements SearchEventDataAccessInterface {
    SearchEventInteractor interactor;

    @Override
    public boolean existByName(String name) {
        if (name.equals("name")){
            return true;
        }
        return false;
    }

    @Override
    public boolean existByLocation(Location location) {
        if (location.equals(new CommonLocation(new ArrayList<>(), "address", "country"))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean existByType(String type) {
        if (type.equals("type")){
            return true;
        }
        return false;
    }

    @Override
    public boolean existByTime(LocalDateTime start, LocalDateTime end) {
        if (start.isEqual(start) && end.equals(end)) {
            return true;
        }
        return false;
    }

    @Override
    public Integer returnByName(String name) {
        return 1;
    }

    @Override
    public Integer returnByLocation(Location location) {
        return 1;
    }

    @Override
    public Integer returnByTime(LocalDateTime start, LocalDateTime end) {
        return 1;
    }

    @Override
    public Integer returnByType(String type) {
        return 1;
    }
}
