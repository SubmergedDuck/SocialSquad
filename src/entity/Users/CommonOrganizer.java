package entity.Users;

import entity.Events.Event;
import entity.Location.Location;

import java.util.ArrayList;

public class CommonOrganizer extends CommonUser implements Organizer {
    private final Event ofEvent;

    public CommonOrganizer(String username, String password, Event ofEvent, int age, String sex, String contact) {
        super(username, password, age, sex, contact);
        this.ofEvent = ofEvent;
    }

    @Override
    public Event getOfEvent() {
        return ofEvent;
    }

    @Override
    public void setPrivacy(Event event, Boolean bool) {

    }

    @Override
    public void setTime(Event event, String time) {

    }

    @Override
    public void setType(Event event, String eventType) {

    }

    @Override
    public void setDescription(Event event, String description) {

    }

    @Override
    public User viewParticipantInfo(Integer userID) {
        return null;
    }

    @Override
    public void removeParticipant(Event event, Integer userID) {

    }

    @Override
    public void transferOrganizer(Integer userID) {

    }
}
