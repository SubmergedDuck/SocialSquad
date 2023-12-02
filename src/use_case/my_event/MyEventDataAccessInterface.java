package use_case.my_event;

import entity.Events.Event;

import java.util.List;

public interface MyEventDataAccessInterface {
    List<Event> getJoinedEvents(String username);
    List<Event> getCreatedEvents(String username);
}
