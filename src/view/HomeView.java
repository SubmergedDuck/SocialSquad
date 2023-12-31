package view;
import data_access.*;
import entity.Events.CommonEvent;
import data_access.InMemoryCurrentUserDAO;
import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.*;
import entity.Events.CommonEventFactory;
import entity.Events.Event;
import entity.Events.EventFactory;
import entity.Location.*;
import entity.Users.CommonUser;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewManagerModelAdapter;
import interface_adapter.back_out.BackOutController;
import interface_adapter.back_out.BackOutPresenter;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserPresenter;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_direction.GetDirectionController;
import interface_adapter.get_direction.GetDirectionPresenter;
import interface_adapter.get_direction.GetDirectionViewModel;
import interface_adapter.generate_static_map.GenerateStaticMapController;
import interface_adapter.generate_static_map.GenerateStaticMapPresenter;
import interface_adapter.generate_static_map.GenerateStaticMapState;
import interface_adapter.generate_static_map.GenerateStaticMapViewModel;
import interface_adapter.create_event.CreateEventPresenter;
import interface_adapter.create_event.CreateEventState;
import interface_adapter.create_event.CreateEventViewModel;
import interface_adapter.get_current_user.GetCurrentUserState;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.get_ids.GetIDsViewModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.join_event.JoinEventPresenter;
import interface_adapter.join_event.JoinEventViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.search_nearby.SearchNearbyController;
import interface_adapter.search_nearby.SearchNearbyPresenter;
import interface_adapter.search_nearby.SearchNearbyState;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import use_case.back_out.BackOutInteractor;
import use_case.get_current_user.GetCurrentUserInteractor;
import use_case.get_direction.GetDirectionInteractor;
import use_case.generate_static_map.GSMInteractor;
import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInteractor;
import use_case.create_event.CreateEventOutputBoundary;
import use_case.get_event_details.GetEventDetailsInteractor;
import use_case.join_event.JoinEventInteractor;
import use_case.join_event.JoinEventOutputBoundary;
import use_case.loggedIn.LoggedInInputBoundary;
import use_case.loggedIn.LoggedInInteractor;
import use_case.loggedIn.LoggedInOutputBoundary;
import use_case.search_nearby.SearchNearbyInteractor;
import use_case.search_nearby.SearchNearbyOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author submergedduck
 */

// TODO: Fix Compiler Errors
public class HomeView extends javax.swing.JFrame implements PropertyChangeListener,ActionListener {
    /**
     * Creates new form HomeView
     */
    public final String viewName = "Home";
    private final int height = 504;
    private final int width = 350;
    private final LoggedInViewModel loggedInViewModel;
    private final LoggedInController loggedInController;
    private final SearchNearbyController searchNearbyController;
    private final CreateEventController createEventController;
    private final CreateEventViewModel createEventViewModel;
    private final GetCurrentUserViewModel getCurrentUserViewModel;

    private final GenerateStaticMapController generateStaticMapController;
    private final GenerateStaticMapViewModel generateStaticMapViewModel;

//    private final MyEventController myEventController;
    private final GetIDsViewModel getIDsViewModel;

    private javax.swing.JPanel BottomSeperator_PANEL;
    private view.ButtonGradient CreateEvent_BUTTON;
    private javax.swing.JLabel LogoutIcon_LABEL;
    private javax.swing.JButton Logout_BUTTON;
    private javax.swing.JPanel Main_PANEL;
    private javax.swing.JLabel MapImage_LABEL;
    private view.ButtonGradient SearchEvent_BUTTON;
    private javax.swing.JLabel Title_LABEL;
    private javax.swing.JPanel TopSeperator_PANEL;
    private keeptoo.KGradientPanel Top_GRADIENTPANEL;
    private view.ButtonGradient MyEvents_BUTTON;

    public HomeView(LoggedInViewModel loggedInViewModel, LoggedInController loggedInController,
                    SearchNearbyController searchNearbyController, CreateEventController createEventController, CreateEventViewModel createEventViewModel,
                    GetCurrentUserViewModel getCurrentUserViewModel,
                    GenerateStaticMapController generateStaticMapController,
                    GenerateStaticMapViewModel generateStaticMapViewModel,
                    GetIDsViewModel getIDsViewModel) throws IOException {
        this.loggedInController = loggedInController;
        this.loggedInViewModel = loggedInViewModel;
        this.searchNearbyController = searchNearbyController;
        this.createEventController = createEventController;
        this.createEventViewModel = createEventViewModel;
        this.getCurrentUserViewModel = getCurrentUserViewModel;
        createEventViewModel.addPropertyChangeListener(this);
        this.generateStaticMapController = generateStaticMapController;
        this.generateStaticMapViewModel = generateStaticMapViewModel;
        this.getIDsViewModel = getIDsViewModel;
        this.generateStaticMapViewModel.addPropertyChangeListener(this);
        initComponents();
    }

    private void initComponents() throws IOException {

        Main_PANEL = new javax.swing.JPanel();
        Top_GRADIENTPANEL = new keeptoo.KGradientPanel();
        Title_LABEL = new javax.swing.JLabel();
        TopSeperator_PANEL = new javax.swing.JPanel();
        SearchEvent_BUTTON = new ButtonGradient();
        BottomSeperator_PANEL = new javax.swing.JPanel();
        CreateEvent_BUTTON = new ButtonGradient();
        MapImage_LABEL = new javax.swing.JLabel();
        LogoutIcon_LABEL = new javax.swing.JLabel();
        Logout_BUTTON = new javax.swing.JButton();
        MyEvents_BUTTON = new ButtonGradient();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main_PANEL.setBackground(new java.awt.Color(255, 255, 255));

        Top_GRADIENTPANEL.setkEndColor(new java.awt.Color(255, 255, 255));
        Top_GRADIENTPANEL.setkGradientFocus(30);
        Top_GRADIENTPANEL.setkStartColor(new java.awt.Color(255, 255, 255));
        Top_GRADIENTPANEL.setPreferredSize(new java.awt.Dimension(350, 40));

        Title_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 14)); // NOI18N
        Title_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        Title_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title_LABEL.setText("All events created by users");
        Title_LABEL.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 1, 1, 1));

        TopSeperator_PANEL.setBackground(new java.awt.Color(229, 222, 233));
        TopSeperator_PANEL.setPreferredSize(new java.awt.Dimension(100, 1));
        TopSeperator_PANEL.setSize(new java.awt.Dimension(100, 1));

        javax.swing.GroupLayout TopSeperator_PANELLayout = new javax.swing.GroupLayout(TopSeperator_PANEL);
        TopSeperator_PANEL.setLayout(TopSeperator_PANELLayout);
        TopSeperator_PANELLayout.setHorizontalGroup(
                TopSeperator_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        TopSeperator_PANELLayout.setVerticalGroup(
                TopSeperator_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Top_GRADIENTPANELLayout = new javax.swing.GroupLayout(Top_GRADIENTPANEL);
        Top_GRADIENTPANEL.setLayout(Top_GRADIENTPANELLayout);
        Top_GRADIENTPANELLayout.setHorizontalGroup(
                Top_GRADIENTPANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TopSeperator_PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                        .addComponent(Title_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Top_GRADIENTPANELLayout.setVerticalGroup(
                Top_GRADIENTPANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Top_GRADIENTPANELLayout.createSequentialGroup()
                                .addComponent(Title_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TopSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        SearchEvent_BUTTON.setForeground(new java.awt.Color(196, 182, 206));
        SearchEvent_BUTTON.setText("Search Events");
        SearchEvent_BUTTON.setColor1(new java.awt.Color(251, 247, 255));
        SearchEvent_BUTTON.setColor2(new java.awt.Color(247, 239, 255));
        SearchEvent_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    SearchEvent_BUTTONActionPerformed(evt);
                } catch (IOException e) {
                    System.out.println("IOException occured.");
                }
            }
        });

        BottomSeperator_PANEL.setBackground(new java.awt.Color(229, 222, 233));
        BottomSeperator_PANEL.setPreferredSize(new java.awt.Dimension(100, 1));

        javax.swing.GroupLayout BottomSeperator_PANELLayout = new javax.swing.GroupLayout(BottomSeperator_PANEL);
        BottomSeperator_PANEL.setLayout(BottomSeperator_PANELLayout);
        BottomSeperator_PANELLayout.setHorizontalGroup(
                BottomSeperator_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 320, Short.MAX_VALUE)
        );
        BottomSeperator_PANELLayout.setVerticalGroup(
                BottomSeperator_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1, Short.MAX_VALUE)
        );

        CreateEvent_BUTTON.setForeground(new java.awt.Color(196, 182, 206));
        CreateEvent_BUTTON.setText("Create Event");
        CreateEvent_BUTTON.setColor1(new java.awt.Color(251, 247, 255));
        CreateEvent_BUTTON.setColor2(new java.awt.Color(247, 239, 255));
        CreateEvent_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    System.out.println("create event button is clicked");
                    CreateEvent_BUTTONActionPerformed(evt);
                } catch (IOException e) {
                    System.out.println("run time exception occured.");;
                }
            }
        });

        MapImage_LABEL.setBackground(new java.awt.Color(204, 204, 255));
        MapImage_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);


        String[] currentCoordinates = getCurrentUserViewModel.getState().getUserCoordinates();
        generateStaticMapController.execute(currentCoordinates, 100,width, height);

        // TODO: Import logout image icon to src/view
        LogoutIcon_LABEL.setIcon(new javax.swing.ImageIcon("/Users/submergedduck/Desktop/CSC207/LogOutIcon.png"));
        LogoutIcon_LABEL.setText("jLabel2");

        Logout_BUTTON.setFont(new java.awt.Font("Gotham Medium", 1, 12)); // NOI18N
        Logout_BUTTON.setForeground(new java.awt.Color(229, 222, 233));
        Logout_BUTTON.setText("Logout");
        Logout_BUTTON.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Logout_BUTTON.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(Logout_BUTTON)){
                            LoggedInState currentState = loggedInViewModel.getState();

                            HomeView.this.loggedInController.execute(
                                    currentState.getUsername(),null
                            );

                        }
                    }
                }
        );


        MyEvents_BUTTON.setForeground(new java.awt.Color(196, 182, 206));
        MyEvents_BUTTON.setText("My Events");
        MyEvents_BUTTON.setColor1(new java.awt.Color(251, 247, 255));
        MyEvents_BUTTON.setColor2(new java.awt.Color(247, 239, 255));
        MyEvents_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyEvents_BUTTONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Main_PANELLayout = new javax.swing.GroupLayout(Main_PANEL);
        Main_PANEL.setLayout(Main_PANELLayout);
        Main_PANELLayout.setHorizontalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(MapImage_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                                .addComponent(LogoutIcon_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(Logout_BUTTON))
                                                        .addComponent(BottomSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                                .addComponent(SearchEvent_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(CreateEvent_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(95, 95, 95)
                                                .addComponent(MyEvents_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Main_PANELLayout.setVerticalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MapImage_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(MyEvents_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BottomSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(CreateEvent_BUTTON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(SearchEvent_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(Logout_BUTTON)
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addComponent(LogoutIcon_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(3, 3, 3)))
                                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Main_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Main_PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    private void MyEvents_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource().equals(MyEvents_BUTTON)){
            LoggedInState currentState = loggedInViewModel.getState();
            loggedInController.execute(
                    currentState.getUsername(),getIDsViewModel
            );

        }
        // TODO add your handling code here:
    }

    private void CreateEvent_BUTTONActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        if (evt.getSource().equals(CreateEvent_BUTTON)) {
            CreateEventState state = createEventViewModel.getState();
            System.out.println("HomeView: CreatEvent button is clicked");
            System.out.println("display state: " + String.valueOf(state.getIsDisplayed() + "\n")); // currently false
            if (state.getIsDisplayed()) { // the page is already on display
                System.out.println("you are on create event view.\n");
                String eventOwner = getCurrentUserViewModel.getState().getUsername();
                String eventName = state.getEventName();
                String eventType = state.getEventType();
                String coordinates = state.getCoordinates();
                String capacity = state.getCapacity();
                String date = state.getDate();
                String eventTime = state.getEventTime();
                String description = state.getDescription();
                String formattedDate = String.format("%s %s", date, eventTime);

                createEventController.execute(eventOwner, eventName, eventType, coordinates, capacity, description, formattedDate);

            } else { // the button is pressed to set create event page active
                state.setIsDisplayed(true);
                createEventViewModel.setState(state);
                createEventViewModel.firePropertyChanged();
                System.out.println("create event view will be set active.");
            }
        }
    }

    private void SearchEvent_BUTTONActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        if (evt.getSource().equals(SearchEvent_BUTTON)) {
            try {
                String[] userLocation = getCurrentUserViewModel.getState().getUserCoordinates();
                searchNearbyController.execute(userLocation);

            } catch (IOException e) {
                System.out.println("fail to obtain user's current location");
            } catch (Exception e) {
                System.out.println("run time exception occured.");;
            }

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof GenerateStaticMapState){
            GenerateStaticMapState state = (GenerateStaticMapState)evt.getNewValue();
            BufferedImage generatedMap = state.getGeneratedMap();
            MapImage_LABEL.setIcon(new javax.swing.ImageIcon(generatedMap));
        } else if (evt.getNewValue() instanceof GetCurrentUserState){
            GetCurrentUserState state = (GetCurrentUserState)evt.getNewValue();
            try {
                generateStaticMapController.execute(state.getUserCoordinates(),100,width,height);
            } catch (IOException e) {
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("FlatLaf Light".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame application = new JFrame("Home - Search nearby - Event detail demo");
                SearchNearbyState state = new SearchNearbyState();
                SearchNearbyViewModel searchNearbyViewModel = new SearchNearbyViewModel();
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                CreateEventViewModel createEventViewModel = new CreateEventViewModel(viewManagerModel);
                GetCurrentUserViewModel getCurrentUserViewModel = new GetCurrentUserViewModel();


                InMemoryEventsDataAccessObject inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();
                InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
                InMemoryCurrentUserDAO inMemoryCurrentUserDAO = new InMemoryCurrentUserDAO();
                User testUser = new CommonUser("aa", "123", 1, "f", "contact");
                inMemoryCurrentUserDAO.changeUser(testUser);
                inMemoryUsersDataAccessObject.save(testUser);

                GenerateStaticMapViewModel gsmViewModel = new GenerateStaticMapViewModel();
                GenerateStaticMapPresenter generateStaticMapPresenter = new GenerateStaticMapPresenter(gsmViewModel);
                GSMInteractor generateStaticMapInteractor = new GSMInteractor(new GenerateStaticMapBody(), inMemoryUsersDataAccessObject,
                        inMemoryEventsDataAccessObject, generateStaticMapPresenter);
                GenerateStaticMapController generateStaticMapController = new GenerateStaticMapController(generateStaticMapInteractor);

                // put the test user as the user logged in
                GetCurrentUserState getCurrentUserState = getCurrentUserViewModel.getState();
                getCurrentUserState.setUsername(testUser.getUsername());
                getCurrentUserViewModel.setState(getCurrentUserState);

                EventFactory eventFactory = new CommonEventFactory();

                LocationFactory locationFactory = new CommonLocationFactory();

                SearchNearbyInteractor interactor = new SearchNearbyInteractor(inMemoryEventsDataAccessObject, new SearchNearbyPresenter(searchNearbyViewModel, viewManagerModel));
                SearchNearbyController searchNearbyController = new SearchNearbyController(interactor);

                CreateEventOutputBoundary createEventPresenter = new CreateEventPresenter(createEventViewModel);
                CreateEventInputBoundary createEventInteractor = new CreateEventInteractor(inMemoryEventsDataAccessObject, inMemoryUsersDataAccessObject, createEventPresenter, eventFactory, locationFactory);
                CreateEventController createEventController = new CreateEventController(createEventInteractor);

                GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();
                GetEventDetailsPresenter getEventDetailsPresenter = new GetEventDetailsPresenter(getEventDetailsViewModel, viewManagerModel);
                GetEventDetailsInteractor interactor1 = new GetEventDetailsInteractor(getEventDetailsPresenter, inMemoryEventsDataAccessObject);
                GetEventDetailsController getEventDetailsController = new GetEventDetailsController(interactor1);

                JoinEventViewModel joinEventViewModel = new JoinEventViewModel("join event");
                JoinEventOutputBoundary joinEventPresenter = new JoinEventPresenter(joinEventViewModel);
                JoinEventInteractor joinEventInteractor = new JoinEventInteractor(joinEventPresenter,inMemoryUsersDataAccessObject,
                        inMemoryEventsDataAccessObject,inMemoryCurrentUserDAO);
                JoinEventController joinEventController = new JoinEventController(joinEventInteractor);

                ViewManagerModelAdapter viewManagerModelAdapter = new ViewManagerModelAdapter(viewManagerModel);
                BackOutPresenter backOutPresenter = new BackOutPresenter(viewManagerModelAdapter);
                BackOutInteractor backOutInteractor = new BackOutInteractor(backOutPresenter);
                BackOutController backOutController = new BackOutController(backOutInteractor);

                GetDirectionViewModel getDirectionViewModel1 = new GetDirectionViewModel();
                GetDirectionPresenter getDirectionPresenter = new GetDirectionPresenter(getDirectionViewModel1);
                GetDirectionInteractor getDirectionInteractor = new GetDirectionInteractor(getDirectionPresenter,inMemoryEventsDataAccessObject,inMemoryUsersDataAccessObject,
                        new GenerateRoute());
                GetDirectionController getDirectionController1 = new GetDirectionController(getDirectionInteractor);

                GetCurrentUserViewModel getCurrentUserViewModel1 = new GetCurrentUserViewModel();
                GetCurrentUserPresenter getCurrentUserPresenter = new GetCurrentUserPresenter(getCurrentUserViewModel1);
                GetCurrentUserInteractor getCurrentUserInteractor = new GetCurrentUserInteractor(getCurrentUserPresenter,inMemoryCurrentUserDAO);
                GetCurrentUserController getCurrentUserController1 = new GetCurrentUserController(getCurrentUserInteractor);

                SearchNearbyView view = new SearchNearbyView(searchNearbyViewModel, getEventDetailsController, backOutController);
                CreateEventView createEventView = new CreateEventView(createEventViewModel, createEventController, backOutController, getCurrentUserViewModel, generateStaticMapController);
                createEventViewModel.addPropertyChangeListener(createEventView);
                createEventViewModel.addPropertyChangeListener(view);
                EventDetailsView eventDetailsView = new EventDetailsView(getEventDetailsViewModel, joinEventController, joinEventViewModel,
                        backOutController, getDirectionController1,getDirectionViewModel1, getCurrentUserViewModel1, getCurrentUserController1);

                searchNearbyViewModel.addPropertyChangeListener(view);
                getEventDetailsViewModel.addPropertyChangeListener(view);
                getEventDetailsViewModel.addPropertyChangeListener(eventDetailsView);
                createEventViewModel.addPropertyChangeListener(view);

                GetIDsViewModel getIDsViewModel1 = new GetIDsViewModel();

                LoggedInViewModel loggedInViewModel1 = new LoggedInViewModel();
                LoggedInOutputBoundary loggedInPresenter = new LoggedInPresenter(viewManagerModel, loggedInViewModel1, new LoginViewModel(),getIDsViewModel1);
                LoggedInInputBoundary loggedInInteractor = new LoggedInInteractor(inMemoryUsersDataAccessObject, loggedInPresenter);
                LoggedInController loggedInController = new LoggedInController(loggedInInteractor);

                HomeView homeView = null;
                try {
                    homeView = new HomeView(loggedInViewModel1, loggedInController, searchNearbyController, createEventController,createEventViewModel,getCurrentUserViewModel,
                            generateStaticMapController, gsmViewModel, getIDsViewModel1);
                } catch (IOException e) {
                    System.out.println("IO Exception occurred");;
                    e.printStackTrace();
                }
                homeView.setVisible(true);

                homeView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                CardLayout cardLayout = new CardLayout();
                JPanel views = new JPanel(cardLayout);
                application.add(views);

                views.add(view.getRootPane(), "search nearby");
                views.add(eventDetailsView.getRootPane(), "event details");
                views.add(homeView.getRootPane(), "Home");
                views.add(createEventView.getRootPane(), "create event");

                ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);
                viewManagerModel.addPropertyChangeListener(viewManager);

                try {
                    User user = new CommonUser("owner", "password", 20, "f", "contact");

                    LocationFactory factory = new CommonLocationFactory();
                    Location location = factory.makeLocation("(43.665510,-79.387280)"); // Home, within 2KM
                    Location location2 = factory.makeLocation("(43.645531,-79.380348)"); // Union Station (3KM)
                    entity.Events.Event event = new CommonEvent(1, "badminton", "owner", location, new ArrayList<>(),
                            new ArrayList<>(), LocalDateTime.now(), "type", "description", false,
                            10); // This event should be returned
                    entity.Events.Event event2 = new CommonEvent(2, "group trip", "owner", location2, new ArrayList<>(),
                            new ArrayList<>(), LocalDateTime.now(), "type", "description", false, 10);

                    ArrayList<entity.Events.Event> eventArrayList = new ArrayList<>();
                    eventArrayList.add(event);
                    eventArrayList.add(event2);

                    user.setCreatedEvents(eventArrayList); // Let the user create these events

                    // Save the objects to inMemoryDAOs for use
                    inMemoryUsersDataAccessObject.save(user);
                    for (Event event1: eventArrayList) {
                        inMemoryEventsDataAccessObject.save(event1);
                    }

                    state.setEventsSearched(eventArrayList);
                    searchNearbyViewModel.setState(state);
                    SearchNearbyPresenter presenter = new SearchNearbyPresenter(searchNearbyViewModel, viewManagerModel);
                    presenter.prepareSuccessView(new SearchNearbyOutputData(false, eventArrayList));

                    viewManagerModel.setActiveView(homeView.viewName);
                    viewManagerModel.firePropertyChanged();
                    homeView.setVisible(true);

                    application.pack();
                    application.setVisible(true);
                } catch (Exception e) {
                    System.out.println("run time exceptions occured.");;
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(CreateEvent_BUTTON)){
            try {
                CreateEvent_BUTTONActionPerformed(e);
            } catch (IOException ex) {
                System.out.println("IO exception occured.");
            }
        }

    }
}
