package interface_adapter.get_event_details;

import use_case.get_event_details.GetEventDetailsOutputBoundary;
import use_case.get_event_details.GetEventDetailsOutputData;

public class MockGetEventDetailsPresenter implements GetEventDetailsOutputBoundary {
    @Override
    public void prepareView(GetEventDetailsOutputData outputData) {
        String output = String.format("%s,%s,%s,%s,%s,%s",outputData.getOwnerUser(), outputData.getEventName(),
                outputData.getDescription(), outputData.getEventAddress(), outputData.getDate(),outputData.getCapacity());
        System.out.println(output);
    }
}