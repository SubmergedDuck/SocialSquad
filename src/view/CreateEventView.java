package view;
// TODO: add imports

import data_access.*;
import entity.Events.*;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import entity.Users.CommonUser;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewManagerModelAdapter;
import interface_adapter.back_out.BackOutController;
import interface_adapter.back_out.BackOutPresenter;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.create_event.CreateEventPresenter;
import interface_adapter.create_event.CreateEventState;
import interface_adapter.create_event.CreateEventViewModel;
import interface_adapter.generate_static_map.GenerateStaticMapController;
import use_case.back_out.BackOutInputBoundary;
import use_case.back_out.BackOutInteractor;
import use_case.back_out.BackOutOutputBoundary;
import use_case.create_event.CreateEventInputBoundary;
import use_case.create_event.CreateEventInteractor;
import use_case.create_event.CreateEventOutputBoundary;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import data_access.InMemoryEventsDataAccessObject;
import data_access.InMemoryUsersDataAccessObject;
import entity.Events.CommonEventFactory;
import entity.Location.CommonLocationFactory;
import entity.Users.CommonUserFactory;
import entity.Users.User;
import entity.Users.UserFactory;
import interface_adapter.create_event.CreateEventController;
import interface_adapter.create_event.CreateEventPresenter;
import interface_adapter.create_event.CreateEventState;
import interface_adapter.create_event.CreateEventViewModel;
import interface_adapter.get_current_user.GetCurrentUserController;
import interface_adapter.get_current_user.GetCurrentUserState;
import interface_adapter.get_current_user.GetCurrentUserViewModel;
import use_case.create_event.CreateEventInteractor;
import use_case.generate_static_map.GSMInteractor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 *
 * @author submergedduck
 */
public class CreateEventView extends javax.swing.JFrame implements PropertyChangeListener {

    /**
     * Creates new form loginView
     */
    public final String viewName = "create event";
    private final CreateEventViewModel createEventViewModel;
    private final CreateEventController createEventController;
    private final BackOutController backOutController;
    private final GenerateStaticMapController generateStaticMapController;
    private final GetCurrentUserViewModel getCurrentUserViewModel;
    private view.ButtonGradient Back_BUTTON;
    private javax.swing.JPanel BottomSeperator_PANEL;
    private javax.swing.JLabel Capacity_LABEL;
    private javax.swing.JTextField Capacity_TEXTFIELD;
    private view.ButtonGradient CreateEvent_BUTTON;
    private javax.swing.JLabel DateCapacityCreateFailed_LABEL;
    private javax.swing.JLabel Date_LABEL;
    private javax.swing.JTextField Date_TEXTFIELD;
    private javax.swing.JLabel Description_LABEL;
    private javax.swing.JTextArea Description_TEXTAREA;
    private javax.swing.JTextField Description_TEXTFIELD;
    private javax.swing.JLabel EventNameCreateFailed_LABEL;
    private javax.swing.JLabel EventName_LABEL;
    private javax.swing.JTextField EventName_TEXTFIELD;
    private javax.swing.JLabel EventTypeCreateFailed_LABEL;
    private javax.swing.JLabel EventType_LABEL;
    private javax.swing.JTextField EventType_TEXTFIELD;
    private javax.swing.JLabel LocationCreateFailed_LABEL;
    private javax.swing.JLabel Location_LABEL;
    private javax.swing.JTextField Location_TEXTFIELD;
    private javax.swing.JPanel Main_PANEL;
    private javax.swing.JTextField StartTime_TEXTFIELD;
    private javax.swing.JLabel TimeCreateFailed_LABEL;
    private javax.swing.JLabel Time_LABEL;
    private javax.swing.JLabel Title_LABEL;
    private javax.swing.JPanel TopSeperator_PANEL;
    private keeptoo.KGradientPanel Top_GRADIENTPANEL;

    public CreateEventView(CreateEventViewModel createEventViewModel, CreateEventController createEventController,
                           BackOutController backOutController,
                           GetCurrentUserViewModel getCurrentUserViewModel, GenerateStaticMapController generateStaticMapController) {
        this.createEventController = createEventController;
        this.backOutController = backOutController;
        this.createEventViewModel = createEventViewModel;
        this.getCurrentUserViewModel = getCurrentUserViewModel;
        this.generateStaticMapController = generateStaticMapController;
        createEventViewModel.addPropertyChangeListener(this);
        initComponents();
    }

    private void initComponents() {

        Main_PANEL = new javax.swing.JPanel();
        Top_GRADIENTPANEL = new keeptoo.KGradientPanel();
        Title_LABEL = new javax.swing.JLabel();
        TopSeperator_PANEL = new javax.swing.JPanel();
        BottomSeperator_PANEL = new javax.swing.JPanel();
        EventName_TEXTFIELD = new javax.swing.JTextField();
        EventName_LABEL = new javax.swing.JLabel();
        EventNameCreateFailed_LABEL = new javax.swing.JLabel();
        StartTime_TEXTFIELD = new javax.swing.JTextField();
        Time_LABEL = new javax.swing.JLabel();
        TimeCreateFailed_LABEL = new javax.swing.JLabel();
        Location_TEXTFIELD = new javax.swing.JTextField();
        Location_LABEL = new javax.swing.JLabel();
        LocationCreateFailed_LABEL = new javax.swing.JLabel();
        Capacity_TEXTFIELD = new javax.swing.JTextField();
        Date_TEXTFIELD = new javax.swing.JTextField();
        DateCapacityCreateFailed_LABEL = new javax.swing.JLabel();
        Date_LABEL = new javax.swing.JLabel();
        Capacity_LABEL = new javax.swing.JLabel();
        Description_LABEL = new javax.swing.JLabel();
        Description_TEXTFIELD = new javax.swing.JTextField();
        CreateEvent_BUTTON = new view.ButtonGradient();
        Back_BUTTON = new view.ButtonGradient();
        EventType_TEXTFIELD = new javax.swing.JTextField();
        EventType_LABEL = new javax.swing.JLabel();
        EventTypeCreateFailed_LABEL = new javax.swing.JLabel();
        Description_TEXTAREA = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main_PANEL.setBackground(new java.awt.Color(255, 255, 255));

        Top_GRADIENTPANEL.setkEndColor(new java.awt.Color(140, 100, 255));
        Top_GRADIENTPANEL.setkGradientFocus(400);
        Top_GRADIENTPANEL.setkStartColor(new java.awt.Color(223, 131, 255));
        Top_GRADIENTPANEL.setPreferredSize(new java.awt.Dimension(350, 40));

        Title_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 14)); // NOI18N
        Title_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        Title_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title_LABEL.setText("Create Your Own Event");
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

        EventName_TEXTFIELD.setBackground(new java.awt.Color(251, 247, 255));
        EventName_TEXTFIELD.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        EventName_TEXTFIELD.setForeground(new java.awt.Color(196, 182, 206));
        EventName_TEXTFIELD.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 222, 233), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1)));
        EventName_TEXTFIELD.setCaretColor(new java.awt.Color(196, 182, 206));
        EventName_TEXTFIELD.setSelectionColor(new java.awt.Color(140, 100, 255));

        EventName_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        EventName_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        EventName_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EventName_LABEL.setText("Event Name");

        StartTime_TEXTFIELD.setBackground(new java.awt.Color(251, 247, 255));
        StartTime_TEXTFIELD.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        StartTime_TEXTFIELD.setForeground(new java.awt.Color(196, 182, 206));
        StartTime_TEXTFIELD.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 222, 233), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1)));
        StartTime_TEXTFIELD.setCaretColor(new java.awt.Color(196, 182, 206));
        StartTime_TEXTFIELD.setSelectionColor(new java.awt.Color(140, 100, 255));

        Time_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        Time_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        Time_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Time_LABEL.setText("Time (format: 01:23)");

        Location_TEXTFIELD.setBackground(new java.awt.Color(251, 247, 255));
        Location_TEXTFIELD.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        Location_TEXTFIELD.setForeground(new java.awt.Color(196, 182, 206));
        Location_TEXTFIELD.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 222, 233), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1)));
        Location_TEXTFIELD.setCaretColor(new java.awt.Color(196, 182, 206));
        Location_TEXTFIELD.setSelectionColor(new java.awt.Color(140, 100, 255));

        Location_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        Location_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        Location_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Location_LABEL.setText("Location (Long, Lat)");

        Capacity_TEXTFIELD.setBackground(new java.awt.Color(251, 247, 255));
        Capacity_TEXTFIELD.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        Capacity_TEXTFIELD.setForeground(new java.awt.Color(196, 182, 206));
        Capacity_TEXTFIELD.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 222, 233), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1)));
        Capacity_TEXTFIELD.setCaretColor(new java.awt.Color(196, 182, 206));
        Capacity_TEXTFIELD.setSelectionColor(new java.awt.Color(140, 100, 255));

        Date_TEXTFIELD.setBackground(new java.awt.Color(251, 247, 255));
        Date_TEXTFIELD.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        Date_TEXTFIELD.setForeground(new java.awt.Color(196, 182, 206));
        Date_TEXTFIELD.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 222, 233), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1)));
        Date_TEXTFIELD.setCaretColor(new java.awt.Color(196, 182, 206));
        Date_TEXTFIELD.setSelectionColor(new java.awt.Color(140, 100, 255));

        Date_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        Date_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        Date_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Date_LABEL.setText("Date (yyyy-mm-dd)");

        Capacity_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        Capacity_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        Capacity_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Capacity_LABEL.setText("Capacity");

        Description_TEXTFIELD.setBackground(new java.awt.Color(251, 247, 255));
        Description_TEXTFIELD.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        Description_TEXTFIELD.setForeground(new java.awt.Color(196, 182, 206));
        Description_TEXTFIELD.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 222, 233), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1)));
        Description_TEXTFIELD.setCaretColor(new java.awt.Color(196, 182, 206));
        Description_TEXTFIELD.setSelectionColor(new java.awt.Color(140, 100, 255));

        Description_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        Description_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        Description_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Description_LABEL.setText("Description");

        CreateEvent_BUTTON.setText("Create Event");
        CreateEvent_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    System.out.println("CreateEventView: button action performed.");
                    System.out.println("create event button on create event view is clicked\n");
                    CreateEvent_BUTTONActionPerformed(evt);
                } catch (Exception e) {
                    System.out.println("CreateEvent View: run time exception occured.");
                    e.printStackTrace();
                }
            }
        });

        Back_BUTTON.setText("Cancel");
        Back_BUTTON.setColor1(new java.awt.Color(255, 102, 197));
        Back_BUTTON.setColor2(new java.awt.Color(255, 102, 197));
        Back_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_BUTTONActionPerformed(evt);
            }
        });

        EventType_TEXTFIELD.setBackground(new java.awt.Color(251, 247, 255));
        EventType_TEXTFIELD.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        EventType_TEXTFIELD.setForeground(new java.awt.Color(196, 182, 206));
        EventType_TEXTFIELD.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 222, 233), 1, true), javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1)));
        EventType_TEXTFIELD.setCaretColor(new java.awt.Color(196, 182, 206));
        EventType_TEXTFIELD.setSelectionColor(new java.awt.Color(140, 100, 255));

        EventType_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        EventType_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        EventType_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EventType_LABEL.setText("Event Type");

        Description_TEXTAREA.setBackground(new java.awt.Color(251, 247, 255));
        Description_TEXTAREA.setColumns(20);
        Description_TEXTAREA.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        Description_TEXTAREA.setForeground(new java.awt.Color(196, 182, 206));
        Description_TEXTAREA.setLineWrap(true);
        Description_TEXTAREA.setRows(5);
        Description_TEXTAREA.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 222, 233)), javax.swing.BorderFactory.createEmptyBorder(10, 10, 1, 1)));
        Description_TEXTAREA.setCaretColor(new java.awt.Color(196, 182, 206));


        EventType_TEXTFIELD.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState state = createEventViewModel.getState();
                String text = EventType_TEXTFIELD.getText() + e.getKeyChar();
                state.setEventType(text);
                createEventViewModel.setState(state);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        Capacity_TEXTFIELD.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState state = createEventViewModel.getState();
                String text = Capacity_TEXTFIELD.getText() + e.getKeyChar();
                state.setCapacity(text);
                createEventViewModel.setState(state);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        Location_TEXTFIELD.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState state = createEventViewModel.getState();
                String text = Location_TEXTFIELD.getText() + e.getKeyChar();
                state.setCoordinates(text);
                createEventViewModel.setState(state);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        StartTime_TEXTFIELD.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState state = createEventViewModel.getState();
                String text = StartTime_TEXTFIELD.getText() + e.getKeyChar();
                state.setEventTime(text);
                createEventViewModel.setState(state);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        Description_TEXTAREA.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState state = createEventViewModel.getState();
                String text = Description_TEXTAREA.getText() + e.getKeyChar();
                state.setDescription(text);
                createEventViewModel.setState(state);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        EventName_TEXTFIELD.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState state = createEventViewModel.getState();
                String text = EventName_TEXTFIELD.getText() + e.getKeyChar();
                state.setEventName(text);
                createEventViewModel.setState(state);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        Date_TEXTFIELD.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                CreateEventState state = createEventViewModel.getState();
                String text = Date_TEXTFIELD.getText() + e.getKeyChar();
                state.setDate(text);
                createEventViewModel.setState(state);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        javax.swing.GroupLayout Main_PANELLayout = new javax.swing.GroupLayout(Main_PANEL);
        Main_PANEL.setLayout(Main_PANELLayout);
        Main_PANELLayout.setHorizontalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(EventName_TEXTFIELD)
                                                        .addComponent(EventName_LABEL)
                                                        .addComponent(EventNameCreateFailed_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(Time_LABEL)
                                                        .addComponent(TimeCreateFailed_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                                .addComponent(StartTime_TEXTFIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                                        .addComponent(Location_TEXTFIELD)
                                                        .addComponent(Location_LABEL)
                                                        .addComponent(LocationCreateFailed_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(DateCapacityCreateFailed_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(Date_TEXTFIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Date_LABEL))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(Capacity_LABEL)
                                                                        .addComponent(Capacity_TEXTFIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(Description_LABEL)
                                                        .addComponent(EventType_TEXTFIELD)
                                                        .addComponent(EventType_LABEL)
                                                        .addComponent(EventTypeCreateFailed_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(Description_TEXTAREA)))
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addComponent(Back_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(CreateEvent_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Main_PANELLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(BottomSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Main_PANELLayout.setVerticalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EventName_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EventName_TEXTFIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(EventNameCreateFailed_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Time_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(StartTime_TEXTFIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addComponent(TimeCreateFailed_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(EventType_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EventType_TEXTFIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(EventTypeCreateFailed_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Location_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Location_TEXTFIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(LocationCreateFailed_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Date_LABEL)
                                        .addComponent(Capacity_LABEL))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Date_TEXTFIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Capacity_TEXTFIELD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addComponent(DateCapacityCreateFailed_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Description_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Description_TEXTAREA, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BottomSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Back_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CreateEvent_BUTTON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(42, Short.MAX_VALUE))
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

    private void CreateEvent_BUTTONActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        CreateEventState state = createEventViewModel.getState();
        System.out.println("current displayed state: " + String.valueOf(state.getIsDisplayed()) + "\n" + "has been displayed: " + String.valueOf(state.hasBeenDisplayed));
        String eventOwner = getCurrentUserViewModel.getState().getUsername();
        String eventName = state.getEventName();
        String eventType = state.getEventType();
        String coordinates = state.getCoordinates();
        String capacity = state.getCapacity();
        String date = state.getDate();
        String eventTime = state.getEventTime();
        String description = state.getDescription();
        String formattedDate = String.format("%s %s", date, eventTime);

        System.out.println("View: all input data is collected.\n");
        try{
            createEventController.execute(eventOwner, eventName, eventType, coordinates, capacity, description, formattedDate);
        } catch (IOException ex) {
            System.out.println("create event controller has api call error.\n");
        }
        CoordinatesFromIP coordinatesFromIP = new CoordinatesFromIP();
        String[] currentCoordinates = coordinatesFromIP.getCoordinates();
        generateStaticMapController.execute(currentCoordinates, 100,350, 504);
    }

    private void Back_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        CreateEventState state = createEventViewModel.getState();
        state.setIsDisplayed(false);
        state.setHasBeenDisplayed(false);
        createEventViewModel.setState(state);
        backOutController.execute("Home");
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
            java.util.logging.Logger.getLogger(CreateEventView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateEventView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateEventView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateEventView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CommonUserFactory userFactory = new CommonUserFactory();

                InMemoryUsersDataAccessObject inMemoryUsersDataAccessObject = new InMemoryUsersDataAccessObject();
                InMemoryEventsDataAccessObject inMemoryEventsDataAccessObject = new InMemoryEventsDataAccessObject();

                User user = userFactory.create("testuser","123",20,"m","test contact");
                inMemoryUsersDataAccessObject.save(user);
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                CreateEventViewModel createEventViewModel1 = new CreateEventViewModel(viewManagerModel);
                GetCurrentUserViewModel currentUserViewModel = new GetCurrentUserViewModel();

                GetCurrentUserState getCurrentUserState = currentUserViewModel.getState();
                getCurrentUserState.setUsername(user.getUsername());
                currentUserViewModel.setState(getCurrentUserState);

                CreateEventPresenter createEventPresenter = new CreateEventPresenter(createEventViewModel1);
                CreateEventInteractor createEventInteractor = new CreateEventInteractor(inMemoryEventsDataAccessObject, inMemoryUsersDataAccessObject,
                        createEventPresenter,new CommonEventFactory(), new CommonLocationFactory());
                CreateEventController createEventController1 = new CreateEventController(createEventInteractor);

                BackOutOutputBoundary backOutPresenter = new BackOutPresenter(new ViewManagerModelAdapter(viewManagerModel));
                BackOutInputBoundary backOutInteractor = new BackOutInteractor(backOutPresenter);
                BackOutController backOutController = new BackOutController(backOutInteractor);

                GSMInteractor gsmInteractor = null;

                new CreateEventView(createEventViewModel1,createEventController1,backOutController, currentUserViewModel, new GenerateStaticMapController(null)).setVisible(true);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof CreateEventState){
            CreateEventState createEventState = (CreateEventState)evt.getNewValue();
            if (createEventState.isEventCreated()){
                JOptionPane.showMessageDialog(this, "Event was created!");
                createEventState.setEventCreated(false);
                this.createEventViewModel.setState(createEventState);
            } else {
                JOptionPane.showMessageDialog(this, createEventState.getInputError());
            }
        }
    }
}
