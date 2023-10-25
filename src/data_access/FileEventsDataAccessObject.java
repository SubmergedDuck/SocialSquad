package data_access;

import use_case.create_event.CreateEventDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;

public class FileEventsDataAccessObject implements GetDirectionDataAccessInterface,
                                                   CreateEventDataAccessInterface,
                                                   SearchEventDataAccessInterface,
                                                   JoinEventDataAccessInterface {
}
