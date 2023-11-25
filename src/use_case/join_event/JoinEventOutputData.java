package use_case.join_event;

/**
 * Output data for the join event use case.
 */

public class JoinEventOutputData {
    private final String capacity;

    /**
     * Constructor for JoinEventOutputData.
     * @param capacity the capacity of the event
     */

    public JoinEventOutputData(String capacity) {
        this.capacity = capacity;
    }

    /**
     * Getter for the capacity.
     * @return the capacity
     */

    public String getCapacity() {
        return this.capacity;
    }
}
