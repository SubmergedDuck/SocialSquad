package use_case.search_nearby;

import data_access.InMemoryEventsDataAccessObject;
import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import org.junit.jupiter.api.Test;
import use_case.search_event.SearchEventInputData;
import use_case.search_event.SearchEventOutputBoundary;
import use_case.search_event.SearchEventOutputData;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchNearbyInteractorTest {
    @Test
    void searchSuccess() {
        CommonLocationFactory factory = new CommonLocationFactory();
        try {
            Location location = factory.makeLocation("43.665510,-79.387280"); // Home
            Location location2 = factory.makeLocation("43.645531,-79.380348"); // Union Station (3KM)
            Event event = new CommonEvent(1, "badminton", "owner", location, new ArrayList<>(), new ArrayList<>(), LocalDateTime.now(), "type", "description", false, 10);
            Event event2 = new CommonEvent(2, "group trip", "owner", location2, new ArrayList<>(), new ArrayList<>(), LocalDateTime.now(), "type", "description", false, 10);

            SearchNearbyDataAccessInterface inmemoryEventDAO = new InMemoryEventsDataAccessObject();
            ((InMemoryEventsDataAccessObject) inmemoryEventDAO).save(event);
            ((InMemoryEventsDataAccessObject) inmemoryEventDAO).save(event2);
            SearchNearbyInputData inputData = new SearchNearbyInputData(new String[]{"43.664219", "-79.398621"}); // MP building

            SearchNearbyOutputBoundary sucessPresenter = new SearchNearbyOutputBoundary() {
                @Override
                public void prepareSuccessView(SearchNearbyOutputData outputData) {
                    assert !outputData.usecaseFailed;
                    assert outputData.getEvents().size() == 2;
                    assert outputData.getEvents().get(0) == event;
                    assert outputData.getEvents().get(1) == event2;
                }

                @Override
                public void prepareFailView() {
                    fail ("Use case fail is unexpected-- prepareFailView is called.");

                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void searchFailed() {
        CommonLocationFactory factory = new CommonLocationFactory();
        try {
            Location location2 = factory.makeLocation("43.645531,-79.380348"); // Union Station (3KM)
            Event event2 = new CommonEvent(2, "group trip", "owner", location2, new ArrayList<>(), new ArrayList<>(), LocalDateTime.now(), "type", "description", false, 10);
            SearchNearbyDataAccessInterface inmemoryEventDAO = new InMemoryEventsDataAccessObject();
            ((InMemoryEventsDataAccessObject) inmemoryEventDAO).save(event2);
            SearchNearbyInputData inputData = new SearchNearbyInputData(new String[]{"43.664219", "-79.398621"});

            SearchNearbyOutputBoundary sucessPresenter = new SearchNearbyOutputBoundary() {
                @Override
                public void prepareSuccessView(SearchNearbyOutputData outputData) {
                    fail ("usecase success is unexpected-- prepareSuccessView is called.");
                }

                @Override
                public void prepareFailView() {
                    ;

                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}