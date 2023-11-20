package use_case.get_event_details;

/**
 * Input boundary for the get event details use case.
 */
public interface GetEventDetailsInputBoundary {
    void execute(GetEventDetailsInputData inputData);
}