package data_access;

import entity.Users.User;
import entity.Users.UserFactory;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * File data access object for users.
 */
public class FileUserDataAccessObject {
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

    /**
     * Saves the user to the map and updates the file to store the user.
     * @param savedUser the user that is being saved.
     */
    public void save(User savedUser){
        usernameToUser.put(savedUser.getUsername(),savedUser);
        save();
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
}
