package use_case.transfer_ownership_use_case;

import data_access.InMemoryEventsDataAccessObject;
import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocation;
import entity.Location.Location;
import entity.Users.CommonUser;
import entity.Users.User;
import interface_adapter.transfer_ownership.TransferOwnershipController;
import interface_adapter.transfer_ownership.TransferOwnershipPresenter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransferOwnershipInteractorTest {

    @Test
    void successTransfer() {
        Event event = new CommonEvent(1, "Badminton on campus", "kat", new CommonLocation(new String[3], "UofT", "Canada"), new ArrayList<>(), new ArrayList(), LocalDateTime.now().plusHours(25), "type", "desciption", true, 1);
        User newOwner = new CommonUser("newOwner", "password", 1, "F", "email");
        event.getPeopleJoined().add(newOwner.getUsername());
        TransferOwnershipDataAccessInterface inMemoryDAO = new InMemoryEventsDataAccessObject();
        ((InMemoryEventsDataAccessObject) inMemoryDAO).save(event);

        TransferOwnershipOutputBoundary presenter = new TransferOwnershipOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                ;
            }

            @Override
            public void prepareFailView(TransferOwnershipOutputData failReason) {
                fail("prepareFailView is called, the use case should succeed instead.");

            }
        };
        TransferOwnershipInputData inputData = new TransferOwnershipInputData(event, "newOwner");
        TransferOwnershipController controller = new TransferOwnershipController();
        controller.execute(inputData);

        assert event.getOwnerUser().equals("newOwner");
    }
    @Test
    void within24HourFail() {
        Event event = new CommonEvent(1, "Badminton on campus", "kat", new CommonLocation(new String[3], "UofT", "Canada"), new ArrayList<>(), new ArrayList(), LocalDateTime.now().plusHours(23), "type", "desciption", true, 1);
        User newOwner = new CommonUser("newOwner", "password", 1, "F", "email");
        event.getPeopleJoined().add(newOwner.getUsername());
        TransferOwnershipDataAccessInterface inMemoryDAO = new InMemoryEventsDataAccessObject();
        ((InMemoryEventsDataAccessObject) inMemoryDAO).save(event);

        TransferOwnershipOutputBoundary presenter = new TransferOwnershipOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                fail ("prepareSuccessView is called. This is unexpected");
            }

            @Override
            public void prepareFailView(TransferOwnershipOutputData failReason) {
                assert failReason.getFailReason().equals("within24Hours");

            }
        };
        TransferOwnershipInputData inputData = new TransferOwnershipInputData(event, "newOwner");
        TransferOwnershipController controller = new TransferOwnershipController();
        controller.execute(inputData);
    }
    @Test
    void notPariticpantFail() {
        Event event = new CommonEvent(1, "Badminton on campus", "kat", new CommonLocation(new String[3], "UofT", "Canada"), new ArrayList<>(), new ArrayList(), LocalDateTime.now().plusHours(25), "type", "desciption", true, 1);
        User owner = new CommonUser("kat", "password", 1, "F", "email");
        event.getPeopleJoined().add(owner.getUsername());
        TransferOwnershipDataAccessInterface inMemoryDAO = new InMemoryEventsDataAccessObject();
        ((InMemoryEventsDataAccessObject) inMemoryDAO).save(event);

        TransferOwnershipOutputBoundary presenter = new TransferOwnershipOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                fail ("prepareSuccessView is called. The use case should fail.");
                ;
            }

            @Override
            public void prepareFailView(TransferOwnershipOutputData failReason) {
                assert failReason.getFailReason().equals("invalidParticipantUsername");

            }
        };
        TransferOwnershipInputData inputData = new TransferOwnershipInputData(event, "newOwner");
        TransferOwnershipController controller = new TransferOwnershipController();
        controller.execute(inputData);
    }
}