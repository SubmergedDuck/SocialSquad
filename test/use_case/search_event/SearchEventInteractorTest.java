package use_case.search_event;

import data_access.InMemoryEventsDataAccessObject;
import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocation;
import entity.Location.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchEventInteractorTest {

    @Test
    void testHasFullMatch() {
        SearchEventInputData searchRequest = new SearchEventInputData("badminton on campus");
        SearchEventDataAccessInterface inmemoryEventDAO = new InMemoryEventsDataAccessObject();
        Event event = new CommonEvent(1, "Badminton on campus", 1, new CommonLocation(new ArrayList(), "UofT", "Canada"), new ArrayList<>(), new ArrayList(), "today", "type", "desciption", true, 1);
        ((InMemoryEventsDataAccessObject) inmemoryEventDAO).save(event);

        SearchEventOutputBoundary fullMatchPresenter = new SearchEventOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchEventOutputData outputData) {
                assert (outputData.getCompleteMatch().contains(event));
                assert (outputData.getPartialMatch().isEmpty());
            }

            @Override
            public void prepareFailView() {
                fail("Fail view is called, expecting success view to be called.");

            }
        };

        SearchEventInputBoundary interactor = new SearchEventInteractor(inmemoryEventDAO, fullMatchPresenter);
    }

    @Test
    void testHasPartialMatch() {
        SearchEventInputData searchRequest = new SearchEventInputData("badminton on campus");
        SearchEventDataAccessInterface inmemoryEventDAO = new InMemoryEventsDataAccessObject();
        Event event = new CommonEvent(1, "badminton", 1, new CommonLocation(new ArrayList(), "UofT", "Canada"), new ArrayList<>(), new ArrayList(), "today", "type", "desciption", true, 1);
        Event event2 = new CommonEvent(1, "something", 1, new CommonLocation(new ArrayList(), "UofT", "Canada"), new ArrayList<>(), new ArrayList(), "today", "badminton", "desciption", true, 1);
        Event event3 = new CommonEvent(1, "badminton on campus", 1, new CommonLocation(new ArrayList(), "UofT", "Canada"), new ArrayList<>(), new ArrayList(), "today", "type", "desciption", true, 1);
        ((InMemoryEventsDataAccessObject) inmemoryEventDAO).save(event);
        ((InMemoryEventsDataAccessObject) inmemoryEventDAO).save(event2);

        SearchEventOutputBoundary fullMatchPresenter = new SearchEventOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchEventOutputData outputData) {
                assert (outputData.getCompleteMatch().contains(event3));
                assert (outputData.getPartialMatch().contains(event));
                assert (outputData.getPartialMatch().contains(event2));
            }

            @Override
            public void prepareFailView() {
                fail("Fail view is called, expecting success view to be called.");

            }
        };

        SearchEventInputBoundary interactor = new SearchEventInteractor(inmemoryEventDAO, fullMatchPresenter);
    }

    @Test
    void testNoMatch(){
        Event event = new CommonEvent(1, "something", 1, new CommonLocation(new ArrayList(), "UofT", "Canada"), new ArrayList<>(), new ArrayList(), "today", "type", "desciption", true, 1);
        SearchEventInputData searchRequest = new SearchEventInputData("badminton on campus");
        SearchEventDataAccessInterface inmemoryEventDAO = new InMemoryEventsDataAccessObject();
        ((InMemoryEventsDataAccessObject) inmemoryEventDAO).save(event);
        SearchEventOutputBoundary fullMatchPresenter = new SearchEventOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchEventOutputData outputData) {
                fail ("Sucess view is called, it is expected that fail view is called");
            }

            @Override
            public void prepareFailView() {
                ;

            }
        };
        SearchEventInputBoundary interactor = new SearchEventInteractor(inmemoryEventDAO, fullMatchPresenter);
        interactor.execute(searchRequest);


    }
}