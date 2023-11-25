package use_case.leave_event;

/**
 * Input boundary for the leave event use case.
 */
public interface LeaveEventInputBoundary {

    /**
     * Executes the join event use case.
     * @param leaveEventInputData The input data for the use case.
     */

    void execute(LeaveEventInputData leaveEventInputData);

}
