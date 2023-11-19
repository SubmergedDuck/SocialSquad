package use_case.view_participants;

import data_access.InMemoryEventsDataAccessObject;
import entity.Events.CommonEventFactory;
import entity.Events.Event;
import entity.Events.EventFactory;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ViewParticipantsInteractorTest {
    private ViewParticipantsInputBoundary viewParticipantsInteractor;
    private ViewParticipantsDataAccessInterface eventDataAccessObject = new InMemoryEventsDataAccessObject();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Event currentEvent;
    @Before
    public void init(){
        LocationFactory locationFactory = new CommonLocationFactory();
        EventFactory eventFactory = new CommonEventFactory();
        UserFactory userFactory = new CommonUserFactory();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime time = LocalDateTime.parse("2022-04-08 12:30",formatter);
        Location location = locationFactory.create(new String[]{"0", "0"}, "123 Street", "Canada");
        User user = userFactory.create("username", "123", 20, "m", "username@gmail.com");
        ArrayList<String> joinedUsers = new ArrayList<>();
        joinedUsers.add(user.getUsername());
        currentEvent = eventFactory.create(0,"test","username", location,
                joinedUsers, new ArrayList<String>(), time, "test", "nothing", false, 10);
        InMemoryEventsDataAccessObject dataAccessObject = (InMemoryEventsDataAccessObject) eventDataAccessObject;
        dataAccessObject.save(currentEvent);
        ViewParticipantsOutputBoundary viewParticipantsPresenter = new ViewParticipantsOutputBoundary() {
            @Override
            public void prepareView(ViewParticipantsOutputData outputData) {
                System.out.println(outputData.getJoinedParticipants());
            }
        };
        viewParticipantsInteractor = new ViewParticipantsInteractor(eventDataAccessObject, viewParticipantsPresenter);
    }
    /**
     * Sets up a print stream before the test (used for checking printed outputs)
     */
    @Before
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }
    /**
     * Restores the stream after the test (used for checking printed outputs)
     */
    @After
    public void restoreStream() {
        System.setOut(originalOut);
    }

    @Test
    public void getParticipantsTest(){
        ViewParticipantsInputData inputData = new ViewParticipantsInputData(currentEvent.getEventID());
        viewParticipantsInteractor.execute(inputData);
        String printedOutput = outContent.toString();
        String correctOutput = "";
        for (int i = 0; i < currentEvent.getPeopleJoined().size(); i++){
            if (i == currentEvent.getPeopleJoined().size() - 1){
                correctOutput = correctOutput + currentEvent.getPeopleJoined().get(i);
            } else {
                correctOutput = correctOutput + currentEvent.getPeopleJoined().get(i) + ",";
            }
        }
        assertEquals(printedOutput, correctOutput + "\n");
    }
}