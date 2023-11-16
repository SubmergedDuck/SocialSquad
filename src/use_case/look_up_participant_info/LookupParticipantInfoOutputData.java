package use_case.look_up_participant_info;

import entity.Location.Location;

public class LookupParticipantInfoOutputData {
    /**
     * The output data class for the LookupParticipantInfo use case.
     */
    private final String username;
    private final Integer age;
    private final String sex;
    private final String contact;
    private final Location location;

    /**
     * The constructor call for a LookupParticipantInfoOutputData object.
     * @param username Reprsents the username of the participant looked up.
     * @param age Represents the age of the participant looked up.
     * @param sex Represents the sex of the participant looked up.
     * @param contact Represents the contact of the participant looked up.
     * @param location Represents the location of the participant looked, in the form of a Location object.
     */
    public LookupParticipantInfoOutputData(String username, Integer age, String sex, String contact, Location location) {
        this.username = username;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.location = location;
    }

    /**
     * A public getter method for the participant's username.
     * @return The username of the participant.
     */
    public String getUsername() {
        return username;
    }

    /**
     * A public getter method for the participant's age.
     * @return The age of the participant.
     */

    public Integer getAge() {
        return age;
    }

    /**
     * A public getter method for the participant's sex.
     * @return The sex of the participant.
     */

    public String getSex() {
        return sex;
    }

    /**
     * A public getter method for the participant's contact information.
     * @return The contact information of the participant.
     */

    public String getContact() {
        return contact;
    }

    /**
     * A public getter method for the participant's location, translated into String, instead of directly outputting a
     * Location object.
     * @return The location of the participant in the form of a string.
     */

    public String getLocationMessage() {
        return location.toString();
    }
}
