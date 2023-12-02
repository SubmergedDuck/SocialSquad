package unused_usecases___.interface_adapter.get_most_recent_event;

import unused_usecases___.usecases.get_most_recent_event.RecentEventInputBoundary;

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
