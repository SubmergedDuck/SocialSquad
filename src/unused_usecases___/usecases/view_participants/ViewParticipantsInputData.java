package unused_usecases___.usecases.view_participants;

public class ViewParticipantsInputData {
    private final int eventID;

    public ViewParticipantsInputData(int eventID){
        this.eventID = eventID;
    }

    int getEventID(){return this.eventID;}
}
