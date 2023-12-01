package use_case.create_event;

//Adapter pattern to add additional methods defined in the create event dai that wont be used in the user dai.
public interface CreateEventEventDataAccessInterface extends CreateEventDataAccessInterface {
    Integer generateEventID();
}
