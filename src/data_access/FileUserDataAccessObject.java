package data_access;

import entity.Users.CommonUser;
import entity.Users.UserFactory;
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
                                                 JoinEventDataAccessInterface,
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
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("age", 2);
        headers.put("sex", 3);
        headers.put("contact", 4);
        headers.put("events_joined", 5);
        headers.put("events_created", 6);
        headers.put("last_updated_location", 7);

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
                    String eventsJoinedIDs = String.valueOf(col[headers.get("events_joined")]); // expected: "1, 2, 3, ..." Event IDs separated by comma
                    String eventsCreatedIDs = String.valueOf(col[headers.get("events_created")]);
                    int age = Integer.parseInt(String.valueOf(col[headers.get("age")]));
                    String sex = String.valueOf(headers.get("sex"));
                    String contact = String.valueOf(headers.get("contact"));
                    String locationString = String.valueOf(headers.get("location")); // Location String is expected to be "Lattitude, Longitude" in decimal degrees. E.g. New York: 40.753056, -73.983056


                    // Initializing an ArrayList<Event> from String read from CSV
                    String[] eventsJoinedIDsInStrings = eventsJoinedIDs.split(","); //Expect "1, 2, 3" --> "1", "2", "3"
                    ArrayList<Integer> eventJoinedIDs = new ArrayList<>();
                    for (String id: eventsJoinedIDsInStrings) {
                        eventJoinedIDs.add(Integer.parseInt(id));
                    }
                    ArrayList<Event> eventsJoined = fileEventsDataAccessObject.makeEvents(eventJoinedIDs); //EventDAO will instantiate an ArrayList of Event that the user joined

                    // Initializing an ArrayList<Event> from String read from CSV
                    String[] eventsCreatedIDsInStrings = eventsCreatedIDs.split(",");
                    ArrayList<Integer> eventCreatedIDs = new ArrayList<>();
                    for (String id: eventsCreatedIDsInStrings) {
                        eventCreatedIDs.add(Integer.parseInt(id));
                    }
                    ArrayList<Event> eventsCreated = fileEventsDataAccessObject.makeEvents(eventJoinedIDs); //EventDAO will instantiate an ArrayList of Event that the user created

                    // Initializing a Location from String read from CSV
                    Location location = locationFactory.makeLocation(locationString); // location String is a list of coordinates
                    User user = userFactory.create(username, password, age, sex, contact); // user will be instantiated as having an empty ArrayList of joinedEvents and createdEvents
                    user.setCreatedEvents(eventsCreated);
                    user.setJoinedEvents(eventsJoined); // manually put in the ArrayList of events
                    usernameToUser.put(username, user);
                }
            }
        }
    }


    @Override
    public void save(User user) {
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
                    if (eventsCreated.iterator().hasNext()) {
                        eventsCreatedID += ","; // if the current event is not the last, place a comma to separate its ID from the next one
                    }

                }

                for (Event event: eventsJoined) {
                    eventsJoinedID += event.getEventID();
                    if (eventsJoined.iterator().hasNext()){
                        eventsCreatedID += ","; // if the current event is not the last, place a comma to separate its ID from the next one
                    };
                }

                String locationString = String.valueOf(location.getCoordinates()); // location is saved by coordinates: lattitude, longtitude

                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getUsername(), user.getPassword(), user.getAge(), user.getSex(), user.getContact(), eventsJoinedID, eventsCreatedID, locationString);
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public Integer generateEventID() {
        return null;
    }

    public void save(Event event) {
        String ownerUser = event.getOwnerUser();
        User eventOwner = this.usernameToUser.get(ownerUser);
        ArrayList<Event> hostedEvents = eventOwner.getCreatedEvents();
        hostedEvents.add(event);
    }

    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(CommonUser commonUser) {

    }

    @Override
    public CommonUser get(String username) {
        return null;
    }
}

