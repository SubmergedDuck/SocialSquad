package use_case.get_ids;

import java.util.ArrayList;

/**
 * Data access interface for the GetIDs use case.
 */
public interface GetIDsDataAccessInterface {

    /**
     * Gets all the ids of a certain event that the user is involved in.
     * @param username the username we want to get the IDs
     * @param isCreatedEvent if the type ID we want are created event IDs.
     * @return
     */
    ArrayList<Integer> getIds(String username, boolean isCreatedEvent);
}
