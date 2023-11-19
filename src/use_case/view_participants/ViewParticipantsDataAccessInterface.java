package use_case.view_participants;

import java.util.List;

/**
 * Data access interface for the view participants use case.
 */
public interface ViewParticipantsDataAccessInterface {
    List<String> getParticipants(Integer eventID);
}
