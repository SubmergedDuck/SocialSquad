package data_access;

import entity.Events.Event;
import entity.Users.User;
import use_case.get_current_user.CurrentUserDataAccessInterface;
import use_case.join_event.JoinEventCurrentUserDataAccessInterface;
import use_case.login.LoginCurrentUserDataAccessInterface;
import use_case.logout.LogoutCurrentUserDataAccessInterface;

// TODO: Class should implement interfaces in:
//  LoginUseCase, LogoutUseCase, JoinEventUseCase, LeaveEventUseCase, CreateEventUseCase, & GetDirectionUseCase
public class InMemoryCurrentUserDAO implements LogoutCurrentUserDataAccessInterface, CurrentUserDataAccessInterface,
                                               JoinEventCurrentUserDataAccessInterface, LoginCurrentUserDataAccessInterface {
    private User currentUser;

    /**
     * A public method that saves the current user that is logged in.
     * @param user The user that is currently logged in.
     */
    @Override
    public void loginCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * A public method that logs out the current user.
     */
    @Override
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
    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * A public method that changes the current logged-in user saved in the DAO.
     * @param user the new logged-in user.
     */
    public void changeUser(User user){currentUser = user;}

    @Override
    public void setUser(User user) {
        currentUser = user;
    }
}
