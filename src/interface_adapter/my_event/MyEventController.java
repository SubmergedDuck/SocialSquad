package interface_adapter.my_event;

import entity.Users.User;
import use_case.my_event.MyEventInputBoundary;
import use_case.my_event.MyEventInputData;

/**
 * Controller for the MyEvent use case.
 */

public class MyEventController {
    final MyEventInputBoundary interactor;

    public MyEventController(MyEventInputBoundary interactor){
        this.interactor = interactor;
    }
    public void execute(User user){
        MyEventInputData myEventInputData = new MyEventInputData(user.getUsername(), user.getJoinedEvents(),user.getCreatedEvents());
        interactor.execute(myEventInputData);
    }
}
