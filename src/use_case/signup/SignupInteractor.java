package use_case.signup;

import entity.Events.Event;
import entity.Users.User;
import entity.Users.UserFactory;
import java.util.ArrayList;

public class SignupInteractor {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessObject, SignupOutputBoundary userPresenter, UserFactory userFactory){
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    public void execute(SignupInputData signupInputData){
        if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())){
            userPresenter.prepareFailView("Passwords do not match");
        } else if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("Username already exists");
        } else if (signupInputData.getAge() < 0){
            //Maybe make it so that the user can only input numbers in the signup age textbox
            userPresenter.prepareFailView("Invalid age input");
        } else if (signupInputData.inputsEmpty()){
            userPresenter.prepareFailView("Fill out all the inputs");
        } else if (signupInputData.getSex().toLowerCase() != "m" || signupInputData.getSex().toLowerCase() != "f"){
            //Checks if the input sex information is valid.
            userPresenter.prepareFailView("Invalid sex");
        } else {
            // All the inputs are good.
            ArrayList<Event> joinedEvents = new ArrayList<>();
            ArrayList<Event> createdEvents = new ArrayList<>();
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), joinedEvents,
                    createdEvents, signupInputData.getRealName(), signupInputData.getContact());

        }


        /*
        CommonUser(username, password, joinedEvents, createdEvents, age, sex, userid,
                contact, location);
         */
    }
}
