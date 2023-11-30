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

    public void execute(String username, int eventID, int mapWidth, int mapHeight){
        GetDirectionInputData inputData = new GetDirectionInputData(eventID, username,
                mapWidth, mapHeight);
        interactor.execute(inputData);
    }
}
