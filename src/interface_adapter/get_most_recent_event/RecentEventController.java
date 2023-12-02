package interface_adapter.get_most_recent_event;

import use_case.get_most_recent_event.RecentEventInputBoundary;

/**
 * Controller for the get most recent event use case.
 */
public class RecentEventController {

    final RecentEventInputBoundary interactor;

    public RecentEventController(RecentEventInputBoundary interactor){
        this.interactor = interactor;
    }

    public void execute(){
        interactor.execute();
    }
}
