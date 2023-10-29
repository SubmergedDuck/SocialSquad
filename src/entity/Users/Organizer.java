package entity.Users;

import entity.Events.Event;

public interface Organizer {

    Event getOfEvent();
    void setPrivacy(Event event, Boolean bool);
    void setTime(Event event, String time);
    void setType(Event event, String eventType);
    void setDescription(Event event, String description);
    User viewParticipantInfo(Integer userID);
    void removeParticipant(Event event, Integer userID);
    void transferOrganizer(Integer userID);






}
