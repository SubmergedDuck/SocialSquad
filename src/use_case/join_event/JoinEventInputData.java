package use_case.join_event;

import entity.Events.Event;
import entity.Users.User;

public class JoinEventInputData {
    private final Event event;
    private final User user;

    public JoinEventInputData(Event event, User user) {
        this.event = event;
        this.user = user;
    }



}
