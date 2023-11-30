package interface_adapter.get_direction;

import interface_adapter.ViewManagerModel;
import use_case.get_direction.GetDirectionOutputBoundary;
import use_case.get_direction.GetDirectionOutputData;

public class GetDirectionPresenter implements GetDirectionOutputBoundary {

    private final GetDirectionViewModel getDirectionViewModel;

    public GetDirectionPresenter(GetDirectionViewModel getDirectionViewModel){
        this.getDirectionViewModel = getDirectionViewModel;
    }
    @Override
    public void prepareView(GetDirectionOutputData outputData) {
        GetDirectionState state = getDirectionViewModel.getState();
        state.setGeneratedImage(outputData.getDirectionImage());
        this.getDirectionViewModel.setState(state);
        getDirectionViewModel.firePropertyChanged();
    }
}
