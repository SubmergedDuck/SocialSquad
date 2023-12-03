package use_case.create_event;

import entity.Events.Event;
import entity.Events.EventFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The use case interactor for the create event use case. It is responsible for accessing the DAOs to create new events
 * and update the created events of users. Furthermore, the interactor will tell the presenter if an event was successfully
 * created or not.
 */
public class CreateEventInteractor implements CreateEventInputBoundary{
    final CreateEventEventDataAccessInterface eventDataAccessObject;
    final CreateEventDataAccessInterface userDataAccessObject;
    final CreateEventOutputBoundary createEventPresenter;
    final EventFactory eventFactory;
    final LocationFactory locationFactory;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for CreateEventInteractor
     * @param eventDataAccessObject event DAO
     * @param userDataAccessObject user DAO
     * @param createEventPresenter the create event presenter
     * @param eventFactory an event factory
     * @param locationFactory a location factory
     */
    public CreateEventInteractor(CreateEventEventDataAccessInterface eventDataAccessObject,
                                 CreateEventDataAccessInterface userDataAccessObject,CreateEventOutputBoundary createEventPresenter,
                                 EventFactory eventFactory, LocationFactory locationFactory){
        this.eventDataAccessObject = eventDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.createEventPresenter = createEventPresenter;
        this.eventFactory = eventFactory;
        this.locationFactory = locationFactory;
    }


    /**
     * Given the created event's information, this method calls the event DAO and adds the event. Furthermore,
     * the user DAO is accessed to change the event creator's created events instance to include the created event.
     * @param input the user inputs for the created event
     */
    @Override
    public void execute(CreateEventInputData input) throws IOException {
        //Makes the location given the location input

        ArrayList<String> allErrors = errorHelper(input);
        Event currentEvent; //temp value, will be reassigned once the event type is figured out
        if (!allErrors.isEmpty()){
            presentErrorHelper(allErrors);
        } else {
            //No errors with input.
            Location eventLocation = locationFactory.makeLocation(input.getLocation());
            Integer eventID = eventDataAccessObject.generateEventID();
            LocalDateTime date = LocalDateTime.parse(input.getTime(), formatter);
            currentEvent = eventFactory.create(eventID, input.getEventName(), input.getOwner(), eventLocation,
                    new ArrayList<String>(), new ArrayList<String>(), date, input.getType(),
                    input.getDescription(), false, Integer.valueOf(input.getCapacity()));
            eventDataAccessObject.save(currentEvent);
            userDataAccessObject.save(currentEvent);

            createEventPresenter.prepareSuccessView();
        }
    }

    private ArrayList<String> errorHelper(CreateEventInputData input){
        //Helps to detect if there are any invalid inputs
        ArrayList<String> allErrors = new ArrayList<>();
        String[] allStrings = {input.getEventName(), input.getType(), input.getEventName(), input.getCapacity(),
                input.getTime(), input.getDescription()};
        if (input.getEventName().isEmpty()){
            allErrors.add("no event name");
        }
        if (input.getType().isEmpty()){
            allErrors.add("no event type");
        }
        try {
            int test = Integer.parseInt(input.getCapacity());
            if (test < 0){
                allErrors.add("cannot have negative capacity");
            }
        } catch (NumberFormatException e){
            allErrors.add("invalid capacity");
        }
        try {
            LocalDateTime testTime = LocalDateTime.parse(input.getTime(),formatter);
        } catch (DateTimeParseException e){
            allErrors.add("invalid time");
        }

        try {
            Location location = locationFactory.makeLocation(input.getLocation());
        } catch (IOException e){
            allErrors.add("invalid coordinates");
        }

        //We don't want inputs besides the coordinates to have commas, otherwise it messes with how data is stored in the csv.
        boolean hasComma = false;
        for (String givenInput : allStrings){
            if (givenInput.contains(",")){
                hasComma = true;
            }
        }
        if (hasComma){
            allErrors.add("A non-coordinate input has a comma");
        }

        return allErrors;
    }
    private void presentErrorHelper(ArrayList<String> errors){
        //Presents error
        String allErrors = "Errors: ";
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