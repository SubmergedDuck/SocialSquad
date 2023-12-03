package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.back_out.BackOutController;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.create_event.CreateEventViewModel;
import interface_adapter.generate_static_map.GenerateStaticMapController;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import org.junit.Test;
import use_case.back_out.BackOutInputBoundary;
import use_case.create_event.CreateEventInputBoundary;
import use_case.generate_static_map.GSMInputBoundary;

import javax.swing.*;

public class CreateEventViewTest {
    @Test
    public void testCreateEventView() {
        CreateEventView.main(new String[]{});

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateEventInputBoundary createEventInputBoundary = null;
        BackOutInputBoundary backOutInputBoundary = null;
        GSMInputBoundary gsmInputBoundary = null;


        CreateEventViewModel createEventViewModel = new CreateEventViewModel(viewManagerModel);
        CreateEventController createEventController = new CreateEventController(createEventInputBoundary);
        BackOutController backOutController = new BackOutController(backOutInputBoundary);
        GetCurrentUserViewModel getCurrentUserViewModel = new GetCurrentUserViewModel();
        GenerateStaticMapController generateStaticMapController = new GenerateStaticMapController(gsmInputBoundary);

        JFrame createEventView = new CreateEventView(createEventViewModel, createEventController, backOutController, getCurrentUserViewModel, generateStaticMapController);
        createEventView.setVisible(true);

    }
}
