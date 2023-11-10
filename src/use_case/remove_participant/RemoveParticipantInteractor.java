package use_case.remove_participant;

public class RemoveParticipantInteractor implements RemoveParticipantInputBoundary{
    final RemoveParticipantDataAccessInterface userDataAccessObject;

    final RemoveParticipantDataAccessInterface eventDataAccessObject;

    final RemoveParticipantOutputBoundary removeEventPresenter;


    /*
    Constructor
     */
    public RemoveParticipantInteractor(RemoveParticipantDataAccessInterface userDataAccessObject,
                                       RemoveParticipantDataAccessInterface eventDataAccessObject, RemoveParticipantOutputBoundary removeEventPresenter){
        this.userDataAccessObject = userDataAccessObject;
        this.eventDataAccessObject = eventDataAccessObject;
        this.removeEventPresenter = removeEventPresenter;
    }

    /*
    The interactor accesses the event and user DAO to make changes to the removed user's joined events and the event's joined users array lists.
    Once this is done, the username of the deleted user is passed to the presenter.
     */
    public void execute(RemoveParticipantInputData inputData){
        userDataAccessObject.removeUser(inputData.getDeletedUser(), inputData.getEventID()); //Looks up a user in the DAO and mutates its joined events instance.
        eventDataAccessObject.removeUser(inputData.getDeletedUser(), inputData.getEventID()); //Looks up an event in the DAO and mutates its joined users instance.
        RemoveParticipantOutputData outputData = new RemoveParticipantOutputData(inputData.getDeletedUser());
        removeEventPresenter.prepareView(outputData);
    }
}
