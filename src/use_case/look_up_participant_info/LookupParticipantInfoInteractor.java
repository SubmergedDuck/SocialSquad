package use_case.look_up_participant_info;

import entity.Location.Location;
import entity.Users.User;

public class LookupParticipantInfoInteractor implements LookupParticipantInfoInputBoundary{
    private final LookupPariticpantInfoDataAccessInterface dataAccessObject;
    private final LookupParticipantInfoOutputBoundary presenter;

    public LookupParticipantInfoInteractor(LookupPariticpantInfoDataAccessInterface dataAccessObject, LookupParticipantInfoOutputBoundary presenter) {
        this.dataAccessObject = dataAccessObject;
        this.presenter = presenter;
    }

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
