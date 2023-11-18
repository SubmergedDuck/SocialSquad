package use_case.create_event;

import entity.Events.Event;
import entity.Events.EventFactory;
import entity.Events.InviteOnlyEventFactory;
import entity.Events.RestrictedEventFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The use case interactor for the create event use case. It is responsible for accessing the DAOs to create new events
 * and update the created events of users. Furthermore, the interactor will tell the presenter if an event was successfully
 * created or not.
 */
public class CreateEventInteractor {
    final CreateEventDataAccessInterface eventDataAccessObject;
    final CreateEventDataAccessInterface userDataAccessObject;
    final CreateEventOutputBoundary createEventPresenter;
    final EventFactory eventFactory;
    final InviteOnlyEventFactory inviteEventFactory;
    final RestrictedEventFactory restrictedEventFactory;
    final LocationFactory locationFactory;

    /**
     * Constructor for CreateEventInteractor
     * @param eventDataAccessObject event DAO
     * @param userDataAccessObject user DAO
     * @param createEventPresenter the create event presenter
     * @param eventFactory an event factory
     * @param inviteEventFactory an invite event factory
     * @param restrictedEventFactory a restricted event factory
     * @param locationFactory a location factory
     */
    public CreateEventInteractor(CreateEventDataAccessInterface eventDataAccessObject,
                                 CreateEventDataAccessInterface userDataAccessObject,CreateEventOutputBoundary createEventPresenter,
                                 EventFactory eventFactory, InviteOnlyEventFactory inviteEventFactory,
                                 RestrictedEventFactory restrictedEventFactory, LocationFactory locationFactory){
        this.eventDataAccessObject = eventDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.createEventPresenter = createEventPresenter;
        this.eventFactory = eventFactory;
        this.locationFactory = locationFactory;
        this.inviteEventFactory = inviteEventFactory;
        this.restrictedEventFactory = restrictedEventFactory;
    }

    /**
     * Given the created event's information, this method calls the event DAO and adds the event. Furthermore,
     * the user DAO is accessed to change the event creator's created events instance to include the created event.
     * @param input the user inputs for the created event
     */
    public void execute(CreateEventInputData input){
        //Makes the location given the location input
        Location eventLocation = locationFactory.makeLocation(input.getLocation());
        Event currentEvent = null; //temp value, will be reassigned once the event type is figured out
        Integer eventID = eventDataAccessObject.generateEventID();
        Boolean invalidInput = errorHelper(input);
        if (invalidInput){
            presentErrorHelper(input, eventLocation);
        } else {
            //No errors with input.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(input.getTime(), formatter);
            if (!input.getAgeRestriction().equals(0) || !input.getSexRestriction().equals("")) {
                //Restricted event.
                currentEvent = (Event) restrictedEventFactory.create(eventID, input.getEventName(), input.getOwner(),
                        eventLocation, new ArrayList<String>(), new ArrayList<String>(), date, input.getType(),
                        input.getDescription(), input.getPrivacy(), input.getCapacity(), input.getAgeRestriction(),
                        input.getSexRestriction());
            } else if (input.getInviteStatus()) {
                //Invite only event
                currentEvent = (Event) inviteEventFactory.create(eventID, input.getEventName(), input.getOwner(), eventLocation,
                        new ArrayList<String>(), new ArrayList<String>(), date, input.getType(),
                        input.getDescription(), input.getPrivacy(), input.getCapacity(), input.getInvitedPeople());
            } else {
                //Normal event
                currentEvent = (Event) eventFactory.create(eventID, input.getEventName(), input.getOwner(), eventLocation,
                        new ArrayList<String>(), new ArrayList<String>(), date, input.getType(),
                        input.getDescription(), input.getPrivacy(), input.getCapacity());
            }
            eventDataAccessObject.save(currentEvent);
            userDataAccessObject.save(currentEvent);

            CreateEventOutputData output = new CreateEventOutputData(currentEvent);
            createEventPresenter.prepareSuccessView(output);
        }
    }
    private boolean errorHelper(CreateEventInputData input){
        //Helps to detect if there are any invalid inputs
        Boolean emptyInput = (input.getEventName().isEmpty()) || (input.getTime().isEmpty()) ||
                (input.getType().isEmpty());
        //Event name, time, and type are the only inputs that cannot be empty. Owner should be automatically filled out when the organizer creates the event.
        Boolean invalidGender = false;
        if (!input.getSexRestriction().isEmpty()){
            invalidGender = !(input.getSexRestriction().equalsIgnoreCase("m") || input.getSexRestriction().equalsIgnoreCase("f"));
        }
        return emptyInput || invalidGender;
    }
    private void presentErrorHelper(CreateEventInputData input, Location eventLocation){
        //Presents error
        ArrayList<String> errors = new ArrayList<>();
        String allErrors = "Errors: ";
        if (input.getEventName().isEmpty()){
            errors.add("No event name was inputted");
        } else if (input.getTime().isEmpty()){
            errors.add("No date was inputted");
        } else if (input.getType().isEmpty()){
            errors.add("No event type was inputted");
        } else if (!(input.getSexRestriction().equalsIgnoreCase("m") || input.getSexRestriction().equalsIgnoreCase("f"))){
            errors.add("Invalid gender inputted");
        } else if (eventLocation.getCoordinates().equals(null)){
            //May change depending on how the instance attributes for invalid locations are assigned.
            errors.add("Invalid location inputted");
        }

        //Generates the error message that points out all the errors with the inputs.
        for (int i = 0; i < errors.size(); i++){
            if (i == errors.size() - 1){
                allErrors = allErrors + errors.get(i);
            } else {
                allErrors = allErrors + errors.get(i) + ",";
            }
        }
        createEventPresenter.prepareFailView(allErrors);
    }
}
