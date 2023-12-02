package data_access;

import entity.Events.CommonEventFactory;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests for the file user DAO.
 */
public class FileUserDataAccessObjectTest {
    private FileUserDataAccessObject userDataAccessObject;

    private FileEventDataAccessObject fileEventDataAccessObject;
    private User testUser1;
    private User testUser2;
    private final String csvPath = "TestUserDatabase.csv";
    private final File userDatabase = new File(csvPath);

    /**
     * Initialzies all the objects used for testing.
     *
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        UserFactory userFactory = new CommonUserFactory();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        fileEventDataAccessObject = new FileEventDataAccessObject("TestEvent&UserDatabase.csv", new CommonEventFactory(), new CommonLocationFactory(), formatter);
        testUser1 = userFactory.create("testUser1", "testPassword1", 20, "m", "testContact1");
        testUser2 = userFactory.create("testUser2", "testPassword2", 23, "f", "testContact2");
        userDataAccessObject = new FileUserDataAccessObject(csvPath, userFactory, fileEventDataAccessObject);
        userDataAccessObject.save(testUser1);
        userDataAccessObject.save(testUser2);
    }

    /**
     * Checks if the header in the csv file is correct.
     */
    @Test
    public void readHeader() {
        try (BufferedReader reader = new BufferedReader(new FileReader(userDatabase))) {
            String header = reader.readLine();
            assertEquals("username,password,age,sex,contact,joinedEvents,createdEvents", header);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests if the first user line is properly formatted
     */
    @Test
    public void readFirstUserLine() {
        try (BufferedReader reader = new BufferedReader(new FileReader(userDatabase))) {
            reader.readLine(); //skip the header
            String userOne = reader.readLine();
            String joinedEvents = eventIDsToString(testUser1.getJoinedEvents());
            String createdEvents = eventIDsToString(testUser1.getCreatedEvents());
            String expectedString = String.format("%s,%s,%s,%s,%s,%s,%s", testUser2.getUsername(), testUser2.getPassword(),
                    String.valueOf(testUser2.getAge()), testUser2.getSex(), testUser2.getContact(),joinedEvents,createdEvents);
            assertEquals(expectedString, userOne);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests if the properly file has stored more than one event.
     */
    @Test
    public void readSubsequentUserLines() {
        try (BufferedReader reader = new BufferedReader(new FileReader(userDatabase))) {
            reader.readLine(); //skip the header
            reader.readLine(); //skip the first user line
            String nextUser = reader.readLine();
            String joinedEvents = eventIDsToString(testUser1.getJoinedEvents());
            String createdEvents = eventIDsToString(testUser1.getCreatedEvents());
            String expectedString = String.format("%s,%s,%s,%s,%s,%s,%s", testUser1.getUsername(), testUser1.getPassword(),
                    String.valueOf(testUser1.getAge()), testUser1.getSex(), testUser1.getContact(),joinedEvents,createdEvents);
            assertEquals(expectedString, nextUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String eventIDsToString(ArrayList<Event> eventCollection) {
        String currentString = "";
        for (int i = 0; i < eventCollection.size(); i++) {
            if (i == eventCollection.size() - 1) {
                currentString = currentString + eventCollection.get(i).getEventID();
            } else {
                currentString = currentString + eventCollection.get(i).getEventID() + " ";
            }
        }
        if (currentString.equals("")){
            currentString = " ";
        }
        return currentString;
    }
}