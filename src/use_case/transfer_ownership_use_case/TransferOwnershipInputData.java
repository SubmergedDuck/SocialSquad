package use_case.transfer_ownership_use_case;

import entity.Events.Event;
import entity.Users.User;

public class TransferOwnershipInputData {
    private final Event event;
    private final String username;

    public TransferOwnershipInputData(Event event, String username) {
        this.event = event;
        this.username = username;
    }

    public Event getEvent() {
        return this.event;
    }

    public String getUsername() {
        return this.username;
    }
}
