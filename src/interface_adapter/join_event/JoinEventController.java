package interface_adapter.join_event;

import use_case.join_event.JoinEventInputBoundary;
import use_case.join_event.JoinEventInputData;

public class JoinEventController {
    final JoinEventInputBoundary interactor;

    public JoinEventController(JoinEventInputBoundary interactor) {
        this.interactor = interactor;
    }
    public void execute(int event, String username) {
        JoinEventInputData inputData = new JoinEventInputData(event, username);
        interactor.execute(inputData);
    }
}
