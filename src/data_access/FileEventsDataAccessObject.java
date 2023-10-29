package data_access;

import entity.Events.Event;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;

import java.util.ArrayList;

public class FileEventsDataAccessObject implements GetDirectionDataAccessInterface,
                                                   CreateEventDataAccessInterface,
                                                   SearchEventDataAccessInterface,
                                                   JoinEventDataAccessInterface {
    public ArrayList<Event> makeEvents(ArrayList<Integer> eventIDs) {
        //TODO: this method take an ArrayList of event IDs and create an ArrayList of Events.
        return null;
    }
}
