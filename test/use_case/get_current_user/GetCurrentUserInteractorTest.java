package use_case.get_current_user;

import data_access.InMemoryCurrentUserDAO;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import interface_adapter.get_current_user.GetCurrentUserPresenter;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetCurrentUserInteractorTest {
    @Test
    public void getCorrectUser(){
        String username = "testUser";
        InMemoryCurrentUserDAO inMemoryCurrentUserDAO = new InMemoryCurrentUserDAO();
        CommonUserFactory userFactory = new CommonUserFactory();
        GetCurrentUserOutputBoundary mockPresenter = new GetCurrentUserOutputBoundary() {
            @Override
            public void prepareView(GetCurrentUserOutputData outputData) {
                assertEquals(username, outputData.getCurrentUser());
            }
        };
        User testUser = userFactory.create(username,"123",20,"m","testcontact");
        inMemoryCurrentUserDAO.changeUser(testUser);
        GetCurrentUserInteractor getCurrentUserInteractor = new GetCurrentUserInteractor(mockPresenter,inMemoryCurrentUserDAO);
        getCurrentUserInteractor.execute();
    }
}