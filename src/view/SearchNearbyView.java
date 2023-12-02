package view;

import data_access.GenerateRoute;
import data_access.InMemoryCurrentUserDAO;
import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUser;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewManagerModelAdapter;
import interface_adapter.back_out.BackOutController;
import interface_adapter.back_out.BackOutPresenter;
import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserPresenter;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import interface_adapter.get_direction.GetDirectionController;
import interface_adapter.get_direction.GetDirectionPresenter;
import interface_adapter.get_direction.GetDirectionViewModel;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.join_event.JoinEventPresenter;
import interface_adapter.join_event.JoinEventViewModel;
import interface_adapter.search_nearby.SearchNearbyPresenter;
import interface_adapter.search_nearby.SearchNearbyState;
import interface_adapter.search_nearby.SearchNearbyViewModel;
import use_case.back_out.BackOutInteractor;
import use_case.get_current_user.GetCurrentUserInteractor;
import use_case.get_direction.GetDirectionInteractor;
import use_case.get_event_details.GetEventDetailsInputData;
import use_case.get_event_details.GetEventDetailsInteractor;
import use_case.join_event.JoinEventInteractor;
import use_case.search_nearby.SearchNearbyInteractor;
import use_case.search_nearby.SearchNearbyOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author submergedduck (Utami Widowati), Katrina Sha
 */
public class SearchNearbyView extends javax.swing.JFrame implements ActionListener, PropertyChangeListener {

    /**
     * Creates new form SearchNearbyView
     */
    public final String viewName = "search nearby";
    private final SearchNearbyViewModel searchNearbyViewModel;
    private final BackOutController backOutController;
    private final GetEventDetailsController getEventDetailsController;
    private ButtonGradient Back_BUTTON;
    private javax.swing.JPanel BottomSeperator_PANEL;
    private ButtonGradient EventDetails_BUTTON;
    private javax.swing.JLabel EventNameCreateFailed_LABEL;
    private javax.swing.JLabel Event_LABEL;
    private JList<String> Events_LIST;
    private javax.swing.JScrollPane Events_SCROLLPANE;
    private javax.swing.JPanel Main_PANEL;
    private javax.swing.JLabel Title_LABEL;
    private javax.swing.JPanel TopSeperator_PANEL;
    private keeptoo.KGradientPanel Top_GRADIENTPANEL;

    public SearchNearbyView(SearchNearbyViewModel searchNearbyViewModel,
                            GetEventDetailsController getEventDetailsController, BackOutController backOutController) {
        initComponents();
        this.searchNearbyViewModel = searchNearbyViewModel;
        this.getEventDetailsController = getEventDetailsController;
        this.backOutController = backOutController;
        this.searchNearbyViewModel.addPropertyChangeListener(this);
    }

    private void initComponents() {

        Main_PANEL = new javax.swing.JPanel();
        Top_GRADIENTPANEL = new keeptoo.KGradientPanel();
        Title_LABEL = new javax.swing.JLabel();
        TopSeperator_PANEL = new javax.swing.JPanel();
        BottomSeperator_PANEL = new javax.swing.JPanel();
        Event_LABEL = new javax.swing.JLabel();
        EventNameCreateFailed_LABEL = new javax.swing.JLabel();
        Events_SCROLLPANE = new javax.swing.JScrollPane();
        Events_LIST = new JList<String>();
        EventDetails_BUTTON = new ButtonGradient();
        Back_BUTTON = new ButtonGradient();

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
        Title_LABEL.setText("Search Nearby Events");
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

        Event_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        Event_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        Event_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Event_LABEL.setText("Events");

        EventNameCreateFailed_LABEL.setFont(new java.awt.Font("Gotham Medium", 3, 10)); // NOI18N
        EventNameCreateFailed_LABEL.setForeground(new java.awt.Color(255, 102, 197));
        EventNameCreateFailed_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EventNameCreateFailed_LABEL.setText("*View Event Details Failed: no event selected");

        Events_SCROLLPANE.setBackground(new java.awt.Color(255, 255, 255));
        Events_SCROLLPANE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 222, 233)));
        Events_SCROLLPANE.setToolTipText("");
        Events_SCROLLPANE.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 0, 5));

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

        javax.swing.GroupLayout Main_PANELLayout = new javax.swing.GroupLayout(Main_PANEL);
        Main_PANEL.setLayout(Main_PANELLayout);
        Main_PANELLayout.setHorizontalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Event_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                                        .addComponent(EventNameCreateFailed_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Events_SCROLLPANE, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(BottomSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(51, 51, 51)
                                                .addComponent(Back_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(EventDetails_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Main_PANELLayout.setVerticalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Event_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Events_SCROLLPANE, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
//                                .addComponent(EventNameCreateFailed_LABEL)
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
    }

    private void EventDetails_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<Event> eventArrayList = searchNearbyViewModel.getState().getEventsSearched();
        // get what event is clicked on
        // make sure an event is clicked on
        if (evt.getSource().equals(EventDetails_BUTTON)) {
            if (Events_LIST.getSelectedIndex() != -1) {
                Event eventSelected = eventArrayList.get(Events_LIST.getSelectedIndex());
                System.out.println(eventSelected.getEventName() + " is selected to view details.");// someething is selected

                getEventDetailsController.execute(eventSelected.getEventID());
            } else {
                JOptionPane.showMessageDialog(this, "Please select an event first.");
            }
        }
    }

    private void Back_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource().equals(Back_BUTTON)) {
            System.out.println("SearchNearby View\nbacking out\n");
            backOutController.execute();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("FlatLaf Light".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchNearbyView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchNearbyView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchNearbyView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchNearbyView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame application = new JFrame("Search nearby - Event detail demo");
                SearchNearbyState state = new SearchNearbyState();
                SearchNearbyViewModel searchNearbyViewModel = new SearchNearbyViewModel();
                InMemoryEventsDataAccessObject inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();
                InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
                InMemoryCurrentUserDAO inMemoryCurrentUserDAO = new InMemoryCurrentUserDAO();
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                CommonUserFactory userFactory = new CommonUserFactory();
                User tempUser = userFactory.create("username","123",20,"m","contact");
                inMemoryCurrentUserDAO.changeUser(tempUser);

                SearchNearbyInteractor interactor = new SearchNearbyInteractor(inMemoryEventsDataAccessObject, new SearchNearbyPresenter(searchNearbyViewModel, viewManagerModel));

                GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();
                GetEventDetailsPresenter getEventDetailsPresenter = new GetEventDetailsPresenter(getEventDetailsViewModel, viewManagerModel);
                GetEventDetailsInteractor interactor1 = new GetEventDetailsInteractor(getEventDetailsPresenter, inMemoryEventsDataAccessObject);
                GetEventDetailsController getEventDetailsController = new GetEventDetailsController(interactor1);

                ViewManagerModelAdapter viewManagerModelAdapter = new ViewManagerModelAdapter(viewManagerModel);
                BackOutPresenter backOutPresenter = new BackOutPresenter(viewManagerModelAdapter);
                BackOutInteractor backOutInteractor = new BackOutInteractor(backOutPresenter);
                BackOutController backOutController = new BackOutController(backOutInteractor);

                SearchNearbyView view = new SearchNearbyView(searchNearbyViewModel, getEventDetailsController, backOutController);
                JoinEventViewModel joinEventViewModel = new JoinEventViewModel("join event");
                JoinEventPresenter joinEventPresenter = new JoinEventPresenter(joinEventViewModel);
                JoinEventInteractor joinEventInteractor = new JoinEventInteractor(joinEventPresenter,inMemoryUsersDataAccessObject,
                        inMemoryEventsDataAccessObject,inMemoryCurrentUserDAO);
                JoinEventController joinEventController = new JoinEventController(joinEventInteractor);

                GetDirectionViewModel getDirectionViewModel1 = new GetDirectionViewModel();
                GetDirectionPresenter getDirectionPresenter = new GetDirectionPresenter(getDirectionViewModel1);
                GetDirectionInteractor getDirectionInteractor = new GetDirectionInteractor(getDirectionPresenter,inMemoryEventsDataAccessObject,inMemoryUsersDataAccessObject,
                        new GenerateRoute());
                GetDirectionController getDirectionController1 = new GetDirectionController(getDirectionInteractor);

                GetCurrentUserViewModel getCurrentUserViewModel1 = new GetCurrentUserViewModel();
                GetCurrentUserPresenter getCurrentUserPresenter = new GetCurrentUserPresenter(getCurrentUserViewModel1);
                GetCurrentUserInteractor getCurrentUserInteractor = new GetCurrentUserInteractor(getCurrentUserPresenter,inMemoryCurrentUserDAO);
                GetCurrentUserController getCurrentUserController1 = new GetCurrentUserController(getCurrentUserInteractor);

                EventDetailsView eventDetailsView = new EventDetailsView(getEventDetailsViewModel, joinEventController,backOutController,
                        getDirectionController1,getDirectionViewModel1,getCurrentUserViewModel1, getCurrentUserController1);

                searchNearbyViewModel.addPropertyChangeListener(view);
                getEventDetailsViewModel.addPropertyChangeListener(view); //TODO here
                getEventDetailsViewModel.addPropertyChangeListener(eventDetailsView); //TODO here

                view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                CardLayout cardLayout = new CardLayout();
                JPanel views = new JPanel(cardLayout);
                application.add(views);

                views.add(view.getRootPane(), "search nearby");
                views.add(eventDetailsView.getRootPane(), "get event details");
                ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);
                viewManagerModel.addPropertyChangeListener(viewManager);

                // test run: received events nearby --> view event details --> join event
                try {
                    User user = new CommonUser("owner", "password", 20, "f", "contact");

                    LocationFactory factory = new CommonLocationFactory();
                    Location location = factory.makeLocation("(43.665510,-79.387280)"); // Home, within 2KM
                    Location location2 = factory.makeLocation("(43.645531,-79.380348)"); // Union Station (3KM)
                    Event event = new CommonEvent(1, "badminton", "owner", location, new ArrayList<>(),
                            new ArrayList<>(), LocalDateTime.now(), "type", "description", false,
                            10); // This event should be returned
                    Event event2 = new CommonEvent(2, "group trip", "owner", location2, new ArrayList<>(),
                            new ArrayList<>(), LocalDateTime.now(), "type", "description", false, 10);

                    ArrayList<Event> eventArrayList = new ArrayList<>();
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

                    getEventDetailsController.execute(event.getEventID());
                    viewManagerModel.setActiveView(view.viewName);
                    viewManagerModel.firePropertyChanged();

                    application.pack();
                    application.setVisible(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("search event")) {
            SearchNearbyState state = (SearchNearbyState) evt.getNewValue();
            if (state.getNoEventsFound()) { // no event is found as the result of the SearchNearby use case
                JOptionPane.showMessageDialog(this, "No events are found within 2KM from you.");

            } else {
                ArrayList<Event> eventsFound = state.getEventsSearched();
                Events_LIST.setFont(new java.awt.Font("Gotham Medium", 1, 12)); // NOI18N
                Events_LIST.setForeground(new java.awt.Color(196, 182, 206));
                Events_LIST.setModel(new javax.swing.AbstractListModel<String>() {
                    public int getSize() { return eventsFound.size(); }
                    public String getElementAt(int i) {// formalize text here
                        String result = "";

                        Event event = eventsFound.get(i);
                        String name = event.getEventName().toUpperCase() + " ";

                        LocalDateTime time = event.getTime();
                        String strTime = String.valueOf(time.getHour()) + ":" + String.valueOf(time.getMinute()) + " " +
                                String.valueOf(time.getDayOfMonth()) + "/" + String.valueOf(time.getMonth()) + "/" +
                                String.valueOf(time.getYear()) + " ";
                        String location = event.getLocation().getAddress() + " ";
                        String capacity = "[" + String.valueOf(event.getCapacity()) + "] ";

                        result = result + name + strTime + location + capacity;

                        return result;
                    }
                });
                Events_LIST.setFixedCellHeight(40);
                Events_LIST.setSelectionBackground(new java.awt.Color(251, 247, 255));
                Events_LIST.setSelectionForeground(new java.awt.Color(140, 100, 255));
                Events_SCROLLPANE.setViewportView(Events_LIST);
            }
        } else if (evt.getPropertyName().equals("event details")) {
            System.out.println("getting event details");

        }

    }
}