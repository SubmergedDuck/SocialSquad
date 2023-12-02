package use_case.join_event;

/**
 * Input boundary for the join event use case.
 */

public interface JoinEventInputBoundary {
    /**
     * Executes the join event use case.
     * @param joinEventInputData The input data for the use case.
     */
    void execute(JoinEventInputData joinEventInputData);
}
