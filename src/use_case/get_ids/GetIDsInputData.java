package use_case.get_ids;

public class GetIDsInputData {
    private final String username;

    private final boolean createdEvents;

    public GetIDsInputData(String username, boolean createdEvents){
        this.username = username;
        this.createdEvents = createdEvents;
    }
    String getUsername(){return this.username;}
    boolean isCreatedEvents(){return this.createdEvents;}
}
