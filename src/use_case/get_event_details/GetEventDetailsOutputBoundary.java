package use_case.get_event_details;

/**
 * Output boundary for the get event details use case.
 */
public interface GetEventDetailsOutputBoundary {
    void prepareView(GetEventDetailsOutputData outputData);
}