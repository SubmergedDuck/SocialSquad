package data_access;

import entity.Events.Event;
import entity.Users.User;
import entity.Users.UserFactory;
import use_case.common_interfaces.MapUserDataAccessInterface;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.loggedIn.LoggedInUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.remove_participant.RemoveParticipantDataAccessInterface;
import use_case.search_event.SearchEventDataAccessInterface;
import use_case.search_event.SearchEventInputData;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * File data access object for users.
 */
public class FileUserDataAccessObject implements RemoveParticipantDataAccessInterface, SignupUserDataAccessInterface,
        CreateEventDataAccessInterface, MapUserDataAccessInterface,
        LoggedInUserDataAccessInterface, LoginUserDataAccessInterface {
    private final File userDataBase;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> usernameToUser = new HashMap<>();
    private final UserFactory userFactory;

    /**
     * Constructor for FileUserDataAccessObject. It reads the csv file that stores the users and creates new user objects and saves them
     * to the user map.
     * @param csvPath the csv file that stores the users
     * @param userFactory user factory
     * @throws IOException error when reading the csv file
     */
    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException{
        this.userDataBase = new File(csvPath);
        this.userFactory = userFactory;

        String[] labels = {"username", "password", "age", "sex", "contact"};
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
                    User user = userFactory.create(username,password,age,sex,contact);
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
                String line = String.format("%s,%s,%s,%s,%s",user.getUsername(),user.getPassword(),String.valueOf(user.getAge()),
                        user.getSex(), user.getContact());
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
    }

    @Override
    public void removeUser(String username, Integer eventID) {
        User deletedUser = usernameToUser.get(username);
        ArrayList<Event> joinedEvents = deletedUser.getJoinedEvents();

        Event eventRemove = null;
        for (Event event : joinedEvents) {
            if (event.getEventID() == eventID) {
                eventRemove = event;
            }
        }
        joinedEvents.remove(eventRemove);
    }
}
