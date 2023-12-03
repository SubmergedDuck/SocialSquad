package unused_usecases___.usecases.search_event;

import data_access.InMemoryEventsDataAccessObject;
import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEventInteractorTest {
    /**
     * A test class for the SearchEventInteractor.
     */

    /**
     * Test the instructor returns a fully matched event as the result of the search.
     */
    @Test
    void testHasFullMatch() {
        SearchEventInputData searchRequest = new SearchEventInputData("badminton on campus");
        SearchEventDataAccessInterface inmemoryEventDAO = new InMemoryEventsDataAccessObject();
        Event event = new CommonEvent(1, "Badminton on campus", "owner", new CommonLocation(new String[3], "UofT", "Canada"), new ArrayList<>(), new ArrayList(), LocalDateTime.now().plusHours(25), "type", "desciption", true, 1);
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

    /**
     * Test the instructor returns several partially matched events along with a fully matched event.
     */
    @Test
    void testHasPartialMatch() {
        SearchEventInputData searchRequest = new SearchEventInputData("badminton on campus");
        SearchEventDataAccessInterface inmemoryEventDAO = new InMemoryEventsDataAccessObject();
        Event event = new CommonEvent(1, "badminton", "", new CommonLocation(new String[3], "UofT", "Canada"), new ArrayList<>(), new ArrayList(), LocalDateTime.now(), "type", "desciption", true, 1);
        Event event2 = new CommonEvent(1, "something", "", new CommonLocation(new String[3], "UofT", "Canada"), new ArrayList<>(), new ArrayList(), LocalDateTime.now(), "badminton", "desciption", true, 1);
        Event event3 = new CommonEvent(1, "badminton on campus", "", new CommonLocation(new String[3], "UofT", "Canada"), new ArrayList<>(), new ArrayList(), LocalDateTime.now(), "type", "desciption", true, 1);
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

    /**
     * Test the itneractor returns nothing when no events satisfy the search request.
     */
    @Test
    void testNoMatch(){
        Event event = new CommonEvent(1, "something", "owner", new CommonLocation(new String [3], "UofT", "Canada"), new ArrayList<>(), new ArrayList(), LocalDateTime.now(), "type", "desciption", true, 1);
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