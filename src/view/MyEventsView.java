package view;

//TODO: (note): Remember to change button and text colors when going from Joined to Owned events and vice versa

import data_access.InMemoryCurrentUserDAO;
import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEventFactory;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewManagerModelAdapter;
import interface_adapter.back_out.BackOutController;
import interface_adapter.back_out.BackOutPresenter;
import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserPresenter;
import interface_adapter.get_current_user.GetCurrentUserState;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_event_details.*;
import interface_adapter.get_ids.GetIDsController;
import interface_adapter.get_ids.GetIDsPresenter;
import interface_adapter.get_ids.GetIDsState;
import interface_adapter.get_ids.GetIDsViewModel;
import use_case.back_out.BackOutInputBoundary;
import use_case.back_out.BackOutInteractor;
import use_case.get_current_user.GetCurrentUserInteractor;
import use_case.get_event_details.GetEventDetailsInteractor;
import use_case.get_ids.GetIDsInteractor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author submergedduck
 */
public class MyEventsView extends javax.swing.JFrame implements PropertyChangeListener {

    /**
     * Creates new form MyEventsView
     */
    public final String viewName = "My Events";

    private final GetIDsController getIDsController;
    private final GetCurrentUserController getCurrentUserController;
    private final GetEventDetailsController eventDetailsController;
    private final BackOutController backOutController;
    private String currentUser;
    private ArrayList<Integer> joinedEvents;
    private ArrayList<Integer> createdEvents;
    private ArrayList<String> eventDescriptions = new ArrayList<>();
    private view.ButtonGradient Back_BUTTON;
    private javax.swing.JPanel BottomSeperator_PANEL;
    private view.ButtonGradient EventDetails_BUTTON;
    private javax.swing.JLabel EventNameCreateFailed_LABEL;
    private javax.swing.JList<String> Events_LIST;
    private javax.swing.JScrollPane Events_SCROLLPANE;
    private view.ButtonGradient Joined_BUTTON;
    private javax.swing.JPanel Main_PANEL;
    private view.ButtonGradient Owned_BUTTON;
    private javax.swing.JLabel Title_LABEL;
    private javax.swing.JPanel TopSeperator_PANEL;
    private keeptoo.KGradientPanel Top_GRADIENTPANEL;
    public MyEventsView(GetIDsController getIDsController, GetIDsViewModel getIDsViewModel,
                        GetCurrentUserController getCurrentUserController, GetCurrentUserViewModel getCurrentUserViewModel,
                        GetEventDetailsController eventDetailsController, BackOutController backOutController,GetEventDetailsViewModel getEventDetailsViewModel) {
        this.getIDsController = getIDsController;
        this.getCurrentUserController = getCurrentUserController;
        this.eventDetailsController = eventDetailsController;
        this.backOutController = backOutController;
        getIDsViewModel.addPropertyChangeListener(this);
        getCurrentUserViewModel.addPropertyChangeListener(this);
        getEventDetailsViewModel.addPropertyChangeListener(this);
        initComponents();
    }

    private void initComponents() {

        Main_PANEL = new javax.swing.JPanel();
        Top_GRADIENTPANEL = new keeptoo.KGradientPanel();
        Title_LABEL = new javax.swing.JLabel();
        TopSeperator_PANEL = new javax.swing.JPanel();
        BottomSeperator_PANEL = new javax.swing.JPanel();
        EventNameCreateFailed_LABEL = new javax.swing.JLabel();
        Events_SCROLLPANE = new javax.swing.JScrollPane();
        Events_LIST = new javax.swing.JList<>();
        EventDetails_BUTTON = new ButtonGradient();
        Back_BUTTON = new ButtonGradient();
        Joined_BUTTON = new ButtonGradient();
        Owned_BUTTON = new ButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main_PANEL.setBackground(new java.awt.Color(255, 255, 255));
        Main_PANEL.setPreferredSize(new java.awt.Dimension(350, 650));

        Top_GRADIENTPANEL.setkEndColor(new java.awt.Color(140, 100, 255));
        Top_GRADIENTPANEL.setkGradientFocus(400);
        Top_GRADIENTPANEL.setkStartColor(new java.awt.Color(223, 131, 255));
        Top_GRADIENTPANEL.setPreferredSize(new java.awt.Dimension(350, 40));

        Title_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 14)); // NOI18N
        Title_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        Title_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title_LABEL.setText("My Joined Events");
        Title_LABEL.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 1, 1, 1));

        TopSeperator_PANEL.setBackground(new java.awt.Color(118, 43, 236));
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

        EventNameCreateFailed_LABEL.setFont(new java.awt.Font("Gotham Medium", 3, 10)); // NOI18N
        EventNameCreateFailed_LABEL.setForeground(new java.awt.Color(255, 102, 197));
        EventNameCreateFailed_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        Events_SCROLLPANE.setBackground(new java.awt.Color(255, 255, 255));
        Events_SCROLLPANE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 222, 233)));
        Events_SCROLLPANE.setToolTipText("");
        Events_SCROLLPANE.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 0, 5));

        Events_LIST.setFont(new java.awt.Font("Gotham Medium", 1, 12)); // NOI18N
        Events_LIST.setForeground(new java.awt.Color(196, 182, 206));

        getCurrentUserController.execute();
        getIDsController.execute(currentUser, false);

        for (Integer eventID: joinedEvents){
            eventDetailsController.execute(eventID, false);
        }

        Events_LIST.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = eventDescriptions.toArray(new String[eventDescriptions.size()]);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        Events_LIST.setFixedCellHeight(40);
        Events_LIST.setSelectionBackground(new java.awt.Color(251, 247, 255));
        Events_LIST.setSelectionForeground(new java.awt.Color(140, 100, 255));
        Events_SCROLLPANE.setViewportView(Events_LIST);

        EventDetails_BUTTON.setText("Event Details");
        EventDetails_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EventDetails_BUTTONActionPerformed(evt);
            }
        });

        Back_BUTTON.setText("Back");
        Back_BUTTON.setColor1(new java.awt.Color(255, 102, 197));
        Back_BUTTON.setColor2(new java.awt.Color(255, 102, 197));
        Back_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_BUTTONActionPerformed(evt);
            }
        });

        Joined_BUTTON.setForeground(new java.awt.Color(255, 255, 255));
        Joined_BUTTON.setText("Joined");
        Joined_BUTTON.setToolTipText("");
        Joined_BUTTON.setColor1(new java.awt.Color(140, 100, 255));
        Joined_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Joined_BUTTONActionPerformed(evt);
            }
        });

        Owned_BUTTON.setForeground(new java.awt.Color(255, 255, 255));
        Owned_BUTTON.setText("Owned");
        Owned_BUTTON.setToolTipText("");
        Owned_BUTTON.setColor1(new java.awt.Color(140, 100, 255));
        Owned_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Owned_BUTTONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Main_PANELLayout = new javax.swing.GroupLayout(Main_PANEL);
        Main_PANEL.setLayout(Main_PANELLayout);
        Main_PANELLayout.setHorizontalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(BottomSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(Back_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(EventDetails_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                                .addComponent(Joined_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(Owned_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(EventNameCreateFailed_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(Events_SCROLLPANE, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Main_PANELLayout.setVerticalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Joined_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Owned_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Events_SCROLLPANE, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(EventNameCreateFailed_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BottomSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Back_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(EventDetails_BUTTON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(38, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void EventDetails_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource().equals(EventDetails_BUTTON)){
            if (Events_LIST.getSelectedIndex() != -1){
                String value = Events_LIST.getSelectedValue();
                Matcher matcher = Pattern.compile("\\d+").matcher(value);
                matcher.find();
                int eventID = Integer.valueOf(matcher.group());
                eventDetailsController.execute(eventID,true);
            }
        }
    }

    private void Back_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        backOutController.execute("Home");
    }

    private void Joined_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        Events_LIST.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = new String[0];
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        getIDsController.execute(currentUser, false);

        for (Integer eventID: joinedEvents){
            eventDetailsController.execute(eventID, false);
        }
    }

    private void Owned_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        Events_LIST.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = new String[0];
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        getIDsController.execute(currentUser, true);

        for (Integer eventID: createdEvents){
            eventDetailsController.execute(eventID, false);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        // Set FlatLaf Light theme
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("FlatLaf Light".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyEventsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyEventsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyEventsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyEventsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Test
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                InMemoryEventsDataAccessObject inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();
                InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
                InMemoryCurrentUserDAO inMemoryCurrentUserDAO = new InMemoryCurrentUserDAO();
                CommonUserFactory userFactory = new CommonUserFactory();
                LocationFactory locationFactory = new CommonLocationFactory();
                CommonEventFactory eventFactory = new CommonEventFactory();
                Location location = null;
                try {
                    location = locationFactory.makeLocation("(43.665510,-79.387280)");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                User newUser = userFactory.create("username","123",20,"m","test contact");
                inMemoryCurrentUserDAO.changeUser(newUser);
                inMemoryUsersDataAccessObject.save(newUser);
                ArrayList<String> peopledJoined = new ArrayList<>();
                peopledJoined.add(newUser.getUsername());
                Event newEvent = eventFactory.create(0,"test event","another user",location,peopledJoined,
                        new ArrayList<String>(), LocalDateTime.now(),"test event", "test", false, 10);
                Event newEvent2 = eventFactory.create(1, "another event", newEvent.getOwnerUser(), location,
                        peopledJoined, new ArrayList<String>(), LocalDateTime.now(), "another event", "testt", false, 5);
                ArrayList<Event> createdEvents = newUser.getCreatedEvents();
                createdEvents.add(newEvent2);
                inMemoryEventsDataAccessObject.save(newEvent2);
                inMemoryEventsDataAccessObject.save(newEvent);
                ArrayList<Event> userJoinedEvents = newUser.getJoinedEvents();
                userJoinedEvents.add(newEvent);
                GetCurrentUserViewModel getCurrentUserViewModel = new GetCurrentUserViewModel();
                GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();
                GetIDsViewModel getIDsViewModel = new GetIDsViewModel();

                //Creating the controllers,presenters, and interactors for each use case in this view.
                GetCurrentUserPresenter getCurrentUserPresenter = new GetCurrentUserPresenter(getCurrentUserViewModel);
                GetIDsPresenter getIDsPresenter = new GetIDsPresenter(getIDsViewModel);
                OnlyGetEventDetailsPresenter getEventDetailsPresenter = new OnlyGetEventDetailsPresenter(getEventDetailsViewModel);

                GetCurrentUserInteractor getCurrentUserInteractor = new GetCurrentUserInteractor(getCurrentUserPresenter, inMemoryCurrentUserDAO);
                GetIDsInteractor getIDsInteractor = new GetIDsInteractor(inMemoryUsersDataAccessObject, getIDsPresenter);
                GetEventDetailsInteractor getEventDetailsInteractor = new GetEventDetailsInteractor(getEventDetailsPresenter,inMemoryEventsDataAccessObject);

                GetCurrentUserController getCurrentUserController1 = new GetCurrentUserController(getCurrentUserInteractor);
                GetIDsController getIDsController1 = new GetIDsController(getIDsInteractor);
                GetEventDetailsController getEventDetailsController1 = new GetEventDetailsController(getEventDetailsInteractor);

                ViewManagerModelAdapter viewManagerModelAdapter = new ViewManagerModelAdapter(viewManagerModel);
                BackOutPresenter backOutPresenter = new BackOutPresenter(viewManagerModelAdapter);
                BackOutInputBoundary backOutInterator = new BackOutInteractor(backOutPresenter);
                BackOutController backOutController = new BackOutController(backOutInterator);

                new MyEventsView(getIDsController1, getIDsViewModel, getCurrentUserController1,getCurrentUserViewModel,
                        getEventDetailsController1, backOutController, getEventDetailsViewModel).setVisible(true);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof GetCurrentUserState){
            GetCurrentUserState state = (GetCurrentUserState)evt.getNewValue();
            currentUser = state.getUsername();
        } else if (evt.getNewValue() instanceof GetIDsState){
            GetIDsState state = (GetIDsState)evt.getNewValue();
            if (!state.getIsCreated()){
                joinedEvents = state.getAllIDs();
                for (Integer eventID: joinedEvents){
                    eventDetailsController.execute(eventID,false);
                }
            } else {
                createdEvents = state.getAllIDs();
                for (Integer eventID: createdEvents){
                    eventDetailsController.execute(eventID,false);
                }
            }
            eventDescriptions = new ArrayList<>();
            state.setAllIDs(new ArrayList<>());
        } else if (evt.getNewValue() instanceof GetEventDetailsState){
            GetEventDetailsState state = (GetEventDetailsState)evt.getNewValue();
            String formattedString = String.format("(Event ID: %s) %s (%s). Address: %s. Owner: %s",
                    state.getEventID(), state.getEventName(), state.getEventDate(), state.getEventAddress(), state.getOwnerUser());
            eventDescriptions.add(formattedString);
            Events_LIST.setModel(new javax.swing.AbstractListModel<String>() {
                String[] strings = eventDescriptions.toArray(new String[eventDescriptions.size()]);
                public int getSize() { return strings.length; }
                public String getElementAt(int i) { return strings[i]; }
            });
        }
    }
}
