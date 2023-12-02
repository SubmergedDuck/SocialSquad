package unused_usecases___.interface_adapter.get_most_recent_event;

public class RecentEventState {
    private int eventID;

    public RecentEventState(RecentEventState copy){
        this.eventID = copy.eventID;
    }

    public RecentEventState(){}

    public void setEventID(int eventID){this.eventID = eventID;}

    public int getEventID(){return this.getEventID();}
}
