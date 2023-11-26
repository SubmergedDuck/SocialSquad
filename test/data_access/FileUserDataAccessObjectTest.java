package data_access;

import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class FileUserDataAccessObjectTest {
    private FileUserDataAccessObject userDataAccessObject;
    private User testUser1;
    private User testUser2;
    private final String csvPath = "TestUserDatabase.csv";
    private final File userDatabase = new File(csvPath);

    @Before
    public void init() throws IOException {
        UserFactory userFactory = new CommonUserFactory();
        testUser1 = userFactory.create("testUser1","testPassword1",20, "m","testContact1");
        testUser2 = userFactory.create("testUser2","testPassword2",23, "f", "testContact2");
        userDataAccessObject = new FileUserDataAccessObject(csvPath,userFactory);
        userDataAccessObject.save(testUser1);
        userDataAccessObject.save(testUser2);
    }

    @Test
    public void readHeader(){
        try (BufferedReader reader = new BufferedReader(new FileReader(userDatabase))) {
            String header = reader.readLine();
            assertEquals("username,password,age,sex,contact",header);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readFirstUserLine(){
        try (BufferedReader reader = new BufferedReader(new FileReader(userDatabase))) {
            reader.readLine(); //skip the header
            String userOne = reader.readLine();
            String expectedString = String.format("%s,%s,%s,%s,%s", testUser2.getUsername(), testUser2.getPassword(),
            String.valueOf(testUser2.getAge()), testUser2.getSex(), testUser2.getContact());
            assertEquals(expectedString, userOne);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void readSubsequentUserLines(){
        try (BufferedReader reader = new BufferedReader(new FileReader(userDatabase))) {
            reader.readLine(); //skip the header
            reader.readLine(); //skip the first user line
            String nextUser = reader.readLine();
            String expectedString = String.format("%s,%s,%s,%s,%s", testUser1.getUsername(), testUser1.getPassword(),
                    String.valueOf(testUser1.getAge()), testUser1.getSex(), testUser1.getContact());
            assertEquals(expectedString, nextUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}