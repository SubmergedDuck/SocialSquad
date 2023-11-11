package use_case.look_up_participant_info;

import entity.Location.Location;

public class LookupParticipantInfoOutputData {
    private final String username;
    private final Integer age;
    private final String sex;
    private final String contact;
    private final Location location;

    public LookupParticipantInfoOutputData(String username, Integer age, String sex, String contact, Location location) {
        this.username = username;
        this.age = age;
        this.sex = sex;
        this.contact = contact;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getContact() {
        return contact;
    }

    public String getLocationMessage() {
        return location.toString();
    }
}
