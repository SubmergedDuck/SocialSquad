package data_access;

import entity.Events.Event;
import entity.Users.User;
import use_case.join_event.JoinEventCurrentUserDataAccessInterface;
import use_case.logout.LogoutCurrentUserDataAccessInterface;

// TODO: Class should implement interfaces in:
//  LoginUseCase, LogoutUseCase, JoinEventUseCase, LeaveEventUseCase, CreateEventUseCase, & GetDirectionUseCase
public class InMemoryCurrentUserDAO implements LogoutCurrentUserDataAccessInterface,
                                               JoinEventCurrentUserDataAccessInterface {
    private User currentUser;

    /**
     * A public method that saves the current user that is logged in.
     * @param user The user that is currently logged in.
     */
    public void loginCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * A public method that logs out the current user.
     */
    public void logoutCurrentUser() {
        this.currentUser = null;
    }

    /**
     * A public method that adds the given event to the current user's joined events list.
     * @param eventID The event ID of the event to be added to the current user's joined events list.
     */
    @Override
    public void currentUserJoinEvent(Integer eventID) {
        // Get Event from eventID
        Event Event = new InMemoryEventsDataAccessObject().getEventMap().get(eventID);
        this.currentUser.getJoinedEvents().add(Event);
    }

    /**
     * A public method that returns the current user that is logged in.
     * @return The current user that is logged in.
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

}
