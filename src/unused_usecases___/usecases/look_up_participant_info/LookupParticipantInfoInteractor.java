package unused_usecases___.usecases.look_up_participant_info;

import entity.Location.Location;
import entity.Users.User;

public class LookupParticipantInfoInteractor implements LookupParticipantInfoInputBoundary{
    /**
     * The interactor class for the LookupParticipantInfo use case.
     */
    private final LookupPariticpantInfoDataAccessInterface dataAccessObject;
    private final LookupParticipantInfoOutputBoundary presenter;

    /**
     * The contructor method for a LookupParticipantInfoInteractor use case.
     * @param dataAccessObject The associated data access object for the use case.
     * @param presenter The associated presenter that implements the output boundary.
     */
    public LookupParticipantInfoInteractor(LookupPariticpantInfoDataAccessInterface dataAccessObject, LookupParticipantInfoOutputBoundary presenter) {
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
    }

    /**
     * A public method that lets the interactor execute with the input data, which carries a User object as participant
     * to look up.
     * @param inputData Represents a User object to look up.
     */
    @Override
    public void execute(LookupParticipantInfoInputData inputData) {
        User user = dataAccessObject.getParticipant(inputData);
        String username = user.getUsername();
        Integer age = user.getAge();
        String sex = user.getSex();
        String contact = user.getContact();
        Location location = user.getLocation();

        LookupParticipantInfoOutputData outputData = new LookupParticipantInfoOutputData(username, age, sex, contact, location);
        presenter.prepareSuccessView(outputData);
    }
}
