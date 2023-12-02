package interface_adapter.my_event;

import use_case.my_event.MyEventOutputBoundary;
import use_case.my_event.MyEventOutputData;

public class MyEventPresenter implements MyEventOutputBoundary {
    private final MyEventViewModel myEventViewModel;

    public  MyEventPresenter(MyEventViewModel myEventViewModel){
        this.myEventViewModel = myEventViewModel;
    }

    @Override
    public void prepareSuccessView(MyEventOutputData outputData) {

    }

    @Override
    public void prepareFailView() {

    }
}
