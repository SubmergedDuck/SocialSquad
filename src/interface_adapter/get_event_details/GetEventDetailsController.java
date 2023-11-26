package interface_adapter.get_event_details;

import entity.Events.Event;
import use_case.get_event_details.GetEventDetailsInputBoundary;
import use_case.get_event_details.GetEventDetailsInputData;

public class GetEventDetailsController {
    final GetEventDetailsInputBoundary interactor;

    public GetEventDetailsController(GetEventDetailsInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(Event event) {
        GetEventDetailsInputData inputData = new GetEventDetailsInputData(event.getEventID());
        interactor.execute(inputData);
    }
}
