package use_case.get_current_user;

import data_access.InMemoryCurrentUserDAO;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import interface_adapter.get_current_user.GetCurrentUserPresenter;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GetCurrentUserInteractorTest {
    @Test
    public void getCorrectUser() throws IOException {
        String username = "testUser";
        InMemoryCurrentUserDAO inMemoryCurrentUserDAO = new InMemoryCurrentUserDAO();
        CommonUserFactory userFactory = new CommonUserFactory();
        CommonLocationFactory locationFactory = new CommonLocationFactory();
        Location location = locationFactory.makeLocation(("(50,50)"));
        User testUser = userFactory.create(username,"123",20,"m","testcontact");
        testUser.setLocation(location);
        inMemoryCurrentUserDAO.changeUser(testUser);
        GetCurrentUserOutputBoundary mockPresenter = new GetCurrentUserOutputBoundary() {
            @Override
            public void prepareView(GetCurrentUserOutputData outputData) {
                assertEquals(username, outputData.getCurrentUser());
                assertEquals(testUser.getLocation().getCoordinates(),outputData.getUserCoordinates());
            }
        };

        GetCurrentUserInteractor getCurrentUserInteractor = new GetCurrentUserInteractor(mockPresenter,inMemoryCurrentUserDAO);
        getCurrentUserInteractor.execute();
    }
}