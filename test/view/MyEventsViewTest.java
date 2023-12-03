package view;

import interface_adapter.back_out.BackOutController;
import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.get_ids.GetIDsController;
import interface_adapter.get_ids.GetIDsViewModel;
import interface_adapter.my_event.MyEventController;
import interface_adapter.my_event.MyEventViewModel;
import use_case.back_out.BackOutInputBoundary;
import use_case.get_current_user.GetCurrentUserInputBoundary;
import use_case.get_current_user.GetCurrentUserInteractor;
import use_case.get_event_details.GetEventDetailsInputBoundary;
import use_case.get_ids.GetIDsInputBoundary;
import use_case.my_event.MyEventInputBoundary;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class MyEventsViewTest {
    @Test
    public void testMyEventsView() {
        MyEventsView.main(new String[]{});

        GetIDsInputBoundary getIDsInputBoundary = null;
        GetCurrentUserInteractor getCurrentUserInputBoundary = null;
        GetEventDetailsInputBoundary getEventDetailsInputBoundary = null;
        BackOutInputBoundary backOutInputBoundary = null;

        GetIDsController getIDsController = new GetIDsController(getIDsInputBoundary);
        GetIDsViewModel getIDsViewModel = new GetIDsViewModel();
        GetCurrentUserController getCurrentUserController = new GetCurrentUserController(getCurrentUserInputBoundary);
        GetCurrentUserViewModel getCurrentUserViewModel = new GetCurrentUserViewModel();
        GetEventDetailsController eventDetailsController = new GetEventDetailsController(getEventDetailsInputBoundary);
        BackOutController backOutController = new BackOutController(backOutInputBoundary);
        GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();



        JFrame myEventsView = new MyEventsView(getIDsController, getIDsViewModel, getCurrentUserController, getCurrentUserViewModel, eventDetailsController, backOutController, getEventDetailsViewModel);
        myEventsView.setVisible(true);


    }
}
