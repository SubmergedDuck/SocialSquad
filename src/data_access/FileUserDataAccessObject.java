package data_access;

import entity.Events.Event;
import entity.Users.User;
import entity.Users.UserFactory;
import use_case.common_interfaces.MapUserDataAccessInterface;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.get_ids.GetIDsDataAccessInterface;
import use_case.join_event.JoinEventUserDataAccessInterface;
import use_case.leave_event.LeaveEventUserDataAccessInterface;
import use_case.loggedIn.LoggedInUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import unused_usecases___.usecases.remove_participant.RemoveParticipantDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.*;

/**
 * File data access object for users.
 */

public class FileUserDataAccessObject implements RemoveParticipantDataAccessInterface, SignupUserDataAccessInterface,
        CreateEventDataAccessInterface, MapUserDataAccessInterface,LoggedInUserDataAccessInterface,
        LoginUserDataAccessInterface, GetIDsDataAccessInterface, JoinEventUserDataAccessInterface, LeaveEventUserDataAccessInterface{
    private final File userDataBase;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> usernameToUser = new HashMap<>();
    private final UserFactory userFactory;
    private final String elementSeperator = " ";
    private final FileEventDataAccessObject fileEventDataAccessObject;

    /**
     * Constructor for FileUserDataAccessObject. It reads the csv file that stores the users and creates new user objects and saves them
     * to the user map.
     * @param csvPath the csv file that stores the users
     * @param userFactory user factory
     * @throws IOException error when reading the csv file
     */
    public FileUserDataAccessObject(String csvPath, UserFactory userFactory, FileEventDataAccessObject fileEventDataAccessObject) throws IOException{
        this.userDataBase = new File(csvPath);
        this.userFactory = userFactory;
        this.fileEventDataAccessObject = fileEventDataAccessObject;

        String[] labels = {"username", "password", "age", "sex", "contact", "joinedEvents", "createdEvents"};
        for (int i = 0; i < labels.length; i++){
            headers.put(labels[i], i);
        }
        if (userDataBase.length() == 0){
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(userDataBase))) {
                reader.readLine(); //Skip the header

                String row;
                while ((row = reader.readLine()) != null){
                    String[] userValues = row.split(",");

                    //Parsing values
                    String username = userValues[headers.get("username")];
                    String password = userValues[headers.get("password")];
                    Integer age = Integer.valueOf(userValues[headers.get("age")]);
                    String sex = userValues[headers.get("sex")];
                    String contact = userValues[headers.get("contact")];
                    String joinedEvents = userValues[headers.get("joinedEvents")];
                    String[] collectionJoinedEvents = joinedEvents.split(elementSeperator);
                    String createdEvents = userValues[headers.get("createdEvents")];
                    String[] collectionCreatedEvents = createdEvents.split(elementSeperator);
                    User user = this.userFactory.create(username,password,age,sex,contact);

                    ArrayList<Event> userJoinedEvents = user.getJoinedEvents();
                    ArrayList<Event> userCreatedEvents = user.getCreatedEvents();
                    for (String id : collectionJoinedEvents){
                        Integer eventID = Integer.parseInt(id);
                        Event selectedEvent = fileEventDataAccessObject.getEvent(eventID);
                        userJoinedEvents.add(selectedEvent);
                    }
                    for (String id : collectionCreatedEvents){
                        Integer eventID = Integer.parseInt(id);
                        Event selectedEvent = fileEventDataAccessObject.getEvent(eventID);
                        userCreatedEvents.add(selectedEvent);
                    }
                    usernameToUser.put(user.getUsername(), user);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return usernameToUser.containsKey(identifier);
    }

    /**
     * Saves the user to the map and updates the file to store the user.
     * @param savedUser the user that is being saved.
     */
    public void save(User savedUser){
        usernameToUser.put(savedUser.getUsername(),savedUser);
        save();
    }

    @Override
    public User get(String username) {
        return usernameToUser.get(username);
    }

    private void save(){
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter(userDataBase));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            for (User user : usernameToUser.values()){
                String joinedEventIDs = "";
                for (int i = 0; i < user.getJoinedEvents().size(); i++){
                    int eventID = user.getJoinedEvents().get(i).getEventID();
                    if (i == user.getJoinedEvents().size() - 1){
                        joinedEventIDs = joinedEventIDs + eventID;
                    } else {
                        joinedEventIDs = joinedEventIDs + eventID + elementSeperator;
                    }
                }
                if (joinedEventIDs.equals("")){
                    joinedEventIDs = elementSeperator;
                }
                String createdEventIDs = "";
                for (int i = 0; i < user.getCreatedEvents().size(); i++){
                    int eventID = user.getCreatedEvents().get(i).getEventID();
                    if (i == user.getCreatedEvents().size() - 1){
                        createdEventIDs = createdEventIDs + eventID;
                    } else {
                        createdEventIDs = createdEventIDs + eventID + elementSeperator;
                    }
                }
                if (createdEventIDs.equals("")){
                    createdEventIDs = elementSeperator;
                }
                String line = String.format("%s,%s,%s,%s,%s,%s,%s",user.getUsername(),user.getPassword(),user.getAge(),
                        user.getSex(), user.getContact(), joinedEventIDs,createdEventIDs);
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] getCoordinates(String user) {
        User selectedUser = usernameToUser.get(user);
        return selectedUser.getLocation().getCoordinates();
    }

    @Override
    public void save(Event event) {
        String ownerUser = event.getOwnerUser();
        User eventOwner = this.usernameToUser.get(ownerUser);
        ArrayList<Event> hostedEvents = eventOwner.getCreatedEvents();
        hostedEvents.add(event);
        save();
    }

    @Override
    public void removeUser(String username, Integer eventID) {
        User deletedUser = usernameToUser.get(username);
        ArrayList<Event> joinedEvents = deletedUser.getJoinedEvents();

        Event eventRemove = null;
        for (Event event : joinedEvents) {
            if (Objects.equals(event.getEventID(), eventID)) {
                eventRemove = event;
            }
        }
        joinedEvents.remove(eventRemove);
    }

    @Override
    public void userJoinEvent(String username, Event event) {
        User user = usernameToUser.get(username);
        user.getJoinedEvents().add(event);
        save();
    }

    @Override
    public ArrayList<Integer> getIds(String username, boolean isCreatedEvent) {
        ArrayList<Integer> currentIDs = new ArrayList<>();
        User user = usernameToUser.get(username);
        if (isCreatedEvent){
            for (Event event : user.getCreatedEvents()){
                currentIDs.add(event.getEventID());
            }
        } else {
            for (Event event : user.getJoinedEvents()){
                currentIDs.add(event.getEventID());
            }
        }
        return currentIDs;
    }

    @Override
    public void userLeaveEvent(String username, Integer eventID) {
        User user = usernameToUser.get(username);
        ArrayList<Event> joinedEvents = user.getJoinedEvents();
        Event selectedEvent = null;
        for (Event joinedEvent : joinedEvents){
            if (joinedEvent.getEventID().equals(eventID)){
                selectedEvent = joinedEvent;
            }
        }
        joinedEvents.remove(selectedEvent);
    }

    @Override
    public ArrayList<Event> getUserJoinedEvents(String username) {
        User user = usernameToUser.get(username);
        return user.getJoinedEvents();
    }
}
