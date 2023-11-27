package interface_adapter.generate_static_map;

import use_case.generate_static_map.GSMOutputBoundary;
import use_case.generate_static_map.GSMOutputData;

public class GenerateStaticMapPresenter implements GSMOutputBoundary {

    private final GenerateStaticMapViewModel generateStaticMapViewModel;

    public GenerateStaticMapPresenter(GenerateStaticMapViewModel generateStaticMapViewModel){
        this.generateStaticMapViewModel = generateStaticMapViewModel;
    }

    @Override
    public void prepareView(GSMOutputData outputData) {

    }
}
