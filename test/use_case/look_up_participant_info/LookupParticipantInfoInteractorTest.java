package use_case.look_up_participant_info;

import data_access.InMemoryLookupParticipantUserCaseDAO;
import entity.Users.CommonUser;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.lookup_participant_info.LookupParticipantInfoPresenter;
import interface_adapter.lookup_participant_info.LookupParticipantInfoViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LookupParticipantInfoInteractorTest {
    /**
     * The test class for the LookupParticipantInfo interactor.
     */

    /**
     * Test that the interactor returns the correct username, age, sex, and contact of the user looked up.
     */
    @Test
    void testExecuteCorrectInfoReturn() {
        User participant = new CommonUser("username", "123", 20, "F", "123-456-7890");
        LookupPariticpantInfoDataAccessInterface inMemoryDAO = new InMemoryLookupParticipantUserCaseDAO();
        ((InMemoryLookupParticipantUserCaseDAO) inMemoryDAO).save(participant);

        LookupParticipantInfoOutputBoundary presenter = new LookupParticipantInfoOutputBoundary() {
            @Override
            public void prepareSuccessView(LookupParticipantInfoOutputData outputData) {
                assert outputData.getUsername().equals("username");
                assert outputData.getAge().equals(20);
                assert outputData.getSex().equals("F");
                assert outputData.getContact().equals("123-456-7890");
            }

        };

        LookupParticipantInfoInputData inputData = new LookupParticipantInfoInputData(participant);
        LookupParticipantInfoInteractor interactor = new LookupParticipantInfoInteractor(inMemoryDAO, presenter);
        interactor.execute(inputData);
    }
}