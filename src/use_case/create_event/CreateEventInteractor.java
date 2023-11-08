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

public class CreateEventInteractor {

    final CreateEventDataAccessInterface eventDataAccessObject;
    final CreateEventDataAccessInterface userDataAccessObject;
    final CreateEventOutputBoundary createEventPresenter;
    final EventFactory eventFactory;
    final InviteOnlyEventFactory inviteEventFactory;
    final RestrictedEventFactory restrictedEventFactory;
    final LocationFactory locationFactory;
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
                        eventLocation, new ArrayList<Integer>(), new ArrayList<Integer>(), date, input.getType(),
                        input.getDescription(), input.getPrivacy(), input.getCapacity(), input.getAgeRestriction(),
                        input.getSexRestriction());
            } else if (input.getInviteStatus()) {
                //Invite only event
                currentEvent = (Event) inviteEventFactory.create(eventID, input.getEventName(), input.getOwner(), eventLocation,
                        new ArrayList<Integer>(), new ArrayList<Integer>(), date, input.getType(),
                        input.getDescription(), input.getPrivacy(), input.getCapacity(), new ArrayList<Integer>());
            } else {
                currentEvent = (Event) eventFactory.create(eventID, input.getEventName(), input.getOwner(), eventLocation,
                        new ArrayList<Integer>(), new ArrayList<Integer>(), date, input.getType(),
                        input.getDescription(), input.getPrivacy(), input.getCapacity());
            }

            eventDataAccessObject.save(currentEvent);
            userDataAccessObject.save(currentEvent);
            createEventPresenter.prepareSuccessView("Event was created");
        }
    }
    private boolean errorHelper(CreateEventInputData input){
        Boolean emptyInput = (input.getEventName().isEmpty()) || (input.getTime().isEmpty()) ||
                (input.getType().isEmpty());
        Boolean invalidGender = false;
        if (!input.getSexRestriction().isEmpty()){
            invalidGender = !(input.getSexRestriction().equalsIgnoreCase("m") || input.getSexRestriction().equalsIgnoreCase("f"));
        }
        return emptyInput || invalidGender;
    }
    private void presentErrorHelper(CreateEventInputData input, Location eventLocation){
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
