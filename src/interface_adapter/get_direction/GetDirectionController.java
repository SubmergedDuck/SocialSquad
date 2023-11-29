package interface_adapter.get_direction;

import entity.Events.Event;
import entity.Users.User;
import use_case.get_direction.GetDirectionInputBoundary;
import use_case.get_direction.GetDirectionInputData;

public class GetDirectionController {
    final GetDirectionInputBoundary interactor;

    public GetDirectionController(GetDirectionInputBoundary interactor){
        this.interactor = interactor;
    }

    public void execute(User currentUser, Event event, int mapWidth, int mapHeight){
        GetDirectionInputData inputData = new GetDirectionInputData(event.getEventID(), currentUser.getUsername(),
                mapWidth, mapHeight);
        interactor.execute(inputData);
    }
}
