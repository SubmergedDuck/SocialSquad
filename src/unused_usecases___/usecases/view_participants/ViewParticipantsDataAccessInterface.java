package unused_usecases___.usecases.view_participants;

import java.util.List;

/**
 * Data access interface for the view participants use case.
 */
public interface ViewParticipantsDataAccessInterface {
    List<String> getParticipants(Integer eventID);
}
