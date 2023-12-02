package app;

import entity.Users.CommonUserFactory;
import entity.Users.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.back_out.BackOutController;
import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.get_ids.GetIDsController;
import interface_adapter.get_ids.GetIDsViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.my_event.MyEventController;
import interface_adapter.my_event.MyEventPresenter;
import interface_adapter.my_event.MyEventViewModel;
import use_case.my_event.MyEventDataAccessInterface;
import use_case.my_event.MyEventInputBoundary;
import use_case.my_event.MyEventInteractor;
import use_case.my_event.MyEventOutputBoundary;
import view.MyEventsView;

import javax.swing.*;
import java.io.IOException;

public class MyEventUseCaseFactory {

    private MyEventUseCaseFactory(){}

    public static MyEventsView create(
            ViewManagerModel viewManagerModel,
            MyEventViewModel myEventViewModel,
            MyEventDataAccessInterface dataAccessInterface,
            GetIDsController getIDsController,
            GetIDsViewModel getIDsViewModel,
            GetCurrentUserController getCurrentUserController,
            BackOutController backOutController,
            GetCurrentUserViewModel getCurrentUserViewModel,
            GetEventDetailsController getEventDetailsController, GetEventDetailsViewModel getEventDetailsViewModel
            ){
        try{
            MyEventController myEventController = createUserMyEventUseCase(viewManagerModel,myEventViewModel,dataAccessInterface);
            return new MyEventsView(getIDsController,getIDsViewModel,getCurrentUserController, getCurrentUserViewModel,getEventDetailsController,backOutController, getEventDetailsViewModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open user data file.");

        }
        return null;
    }

    private static MyEventController createUserMyEventUseCase(
            ViewManagerModel viewManagerModel,
            MyEventViewModel myEventViewModel,
            MyEventDataAccessInterface dataAccessInterface) throws IOException{
        MyEventOutputBoundary myEventOutputBoundary = new MyEventPresenter(myEventViewModel);

        UserFactory userFactory = new CommonUserFactory();
        MyEventInputBoundary myEventInputBoundary = new MyEventInteractor(
                myEventOutputBoundary,dataAccessInterface
        );
        return  new MyEventController(myEventInputBoundary);
    }
}
