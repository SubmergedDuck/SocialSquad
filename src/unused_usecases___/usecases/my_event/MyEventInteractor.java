package use_case.my_event;

import entity.Events.Event;

import java.util.ArrayList;

public class MyEventInteractor implements MyEventInputBoundary{
    final MyEventDataAccessInterface myEventDataAccessInterface;

    final MyEventOutputBoundary myEventPresenter;

    public MyEventInteractor(MyEventOutputBoundary myEventPresenter, MyEventDataAccessInterface myEventDataAccessInterface){
        this.myEventPresenter = myEventPresenter;
        this.myEventDataAccessInterface = myEventDataAccessInterface;
    }

    @Override
    public void execute(MyEventInputData inputData) {
        ArrayList<Event> joinedEvent = inputData.getJoinedEvent();
        ArrayList<Event> createdEvent = inputData.getCreatedEvent();
        MyEventOutputData myEventOutputData = new MyEventOutputData(joinedEvent,createdEvent);
        myEventPresenter.prepareSuccessView(myEventOutputData);

    }
}
