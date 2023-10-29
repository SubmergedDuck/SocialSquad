package data_access;

import entity.Users.UserFactory;
import use_case.create_event.CreateEventDataAccessInterface;
import use_case.get_direction.GetDirectionDataAccessInterface;
import use_case.join_event.JoinEventDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import java.io.*;
import java.util.*;

import entity.Users.User;
import entity.Events.Event;
import entity.Location.Location;
import entity.Location.LocationFactory;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface,
                                                 JoinEventDataAccessInterface, CreateEventDataAccessInterface,
                                                 GetDirectionDataAccessInterface {
    private final File userDatabase;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String,User> usernameToUser = new HashMap<>();
    private final FileEventsDataAccessObject fileEventsDataAccessObject;
    private final LocationFactory locationFactory;



    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, FileEventsDataAccessObject fileEventsDataAccessObject, LocationFactory locationFactory, UserFactory userFactory) throws IOException {
        this.fileEventsDataAccessObject = fileEventsDataAccessObject;
        this.locationFactory = locationFactory;
        this.userFactory = userFactory;

        this.userDatabase = new File(csvPath);
        headers.put("userID", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("age", 3);
        headers.put("sex", 4);
        headers.put("contact", 5);
        headers.put("events_joined", 6);
        headers.put("events_created", 7);
        headers.put("last_updated_location", 8);

        if (userDatabase.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(userDatabase))) {
                String header = reader.readLine();

                // TODO: For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("userID, username, password, age, sex, contact, events_joined, events_created, last_updated_location");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");

                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String eventsJoinedIDs = String.valueOf(col[headers.get("events_joined")]);
                    String eventsCreatedIDs = String.valueOf(col[headers.get("events_created")]);
                    int age = Integer.parseInt(String.valueOf(col[headers.get("age")]));
                    String sex = String.valueOf(headers.get("sex"));
                    int userID = Integer.parseInt(String.valueOf((headers.get("userID"))));
                    String contact = String.valueOf(headers.get("contact"));
                    String locationString = String.valueOf(headers.get("location"));


                    // Initializing an ArrayList<Event> from String read from CSV
                    String[] eventsJoinedIDsInStrings = eventsJoinedIDs.split(","); //Expect "1, 2, 3" --> "1", "2", "3"
                    ArrayList<Integer> eventJoinedIDs = new ArrayList<>();
                    for (String id: eventsJoinedIDsInStrings) {
                        eventJoinedIDs.add(Integer.parseInt(id));
                    }
                    ArrayList<Event> eventsJoined = fileEventsDataAccessObject.makeEvents(eventJoinedIDs);

                    // Initializing an ArrayList<Event> from String read from CSV
                    //TODO: do the same thing as for eventsJoined
                    ArrayList<Integer> eventCreatedIDs = new ArrayList<>();
                    ArrayList<Event> eventsCreated = fileEventsDataAccessObject.makeEvents(eventJoinedIDs);

                    // Initializing a Location from String read from CSV
                    Location location = locationFactory.makeLocation(locationString); // locaitonString is a list of coordinates

                    User user = userFactory.create(username, password, eventsJoined, eventsCreated, age, sex, userID, contact, location);
                    usernameToUser.put(username, user);
                }
            }
        }
    }


    private void save(User user) {
        usernameToUser.put(user.getUsername(), user);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(userDatabase));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user: usernameToUser.values()) {
                ArrayList<Event> eventsCreated = user.getCreatedEvents();
                ArrayList<Event> eventsJoined = user.getJoinedEvents();
                Location location = user.getLocation();

                String eventsCreatedID = null;
                String eventsJoinedID = null;

                for (Event event: eventsCreated) {
                    eventsCreatedID += event.getEventID();
                    eventsCreatedID += ","; // make Event IDs' separated by comma

                }

                for (Event event: eventsJoined) {
                    eventsJoinedID += event.getEventID();
                    eventsCreatedID += ",";
                }

                String locationString = String.valueOf(location.getCoordinates()); // location is saved by coordinates [lattitude, longtitude]

                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", user.getUserID(),
                        user.getUsername(), user.getPassword(), user.getAge(), user.getSex(), user.getContact(), eventsCreatedID, eventsJoinedID, locationString);
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

