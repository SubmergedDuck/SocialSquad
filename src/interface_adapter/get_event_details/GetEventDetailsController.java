package interface_adapter.get_event_details;

import use_case.get_event_details.GetEventDetailsInputData;

public class GetEventDetailsController {
    public void execute(GetEventDetailsInputData inputData) {
        System.out.println("Getting the detail of event ID " + inputData.getEventID());
    }
}
