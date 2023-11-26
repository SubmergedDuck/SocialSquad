// TODO: Add imports

package view;

import entity.Events.CommonEvent;
import entity.Events.Event;
import entity.Location.CommonLocationFactory;
import entity.Location.Location;
import entity.Location.LocationFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.back_out.BackOutController;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsPresenter;
import interface_adapter.get_event_details.GetEventDetailsState;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.join_event.JoinEventController;
import interface_adapter.search_nearby.SearchNearbyPresenter;
import use_case.get_event_details.GetEventDetailsOutputData;
import use_case.search_nearby.SearchNearbyOutputData;

import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author submergedduck
 */
public class EventDetailsView extends javax.swing.JFrame implements ActionListener, PropertyChangeListener {

    /**
     * Creates new form loginView
     */
    public final String viewName = "event details";
    private final GetEventDetailsViewModel getEventDetailsViewModel;
    private final BackOutController backOutController;
    private final JoinEventController joinEventController;


    private ButtonGradient Back_BUTTON;
    private javax.swing.JPanel BottomSeperator_PANEL;
    private javax.swing.JPanel BottomSeperator_PANEL1;
    private javax.swing.JLabel Capacity_LABEL;
    private javax.swing.JLabel DescriptionHeading_LABEL;
    private javax.swing.JPanel Description_PANEL;
    private javax.swing.JScrollPane Description_SCROLLPANE;
    private javax.swing.JTextArea Description_TEXTAREA;
    private javax.swing.JLabel EventName_LABEL;
    private ButtonGradient JoinLeaveEvent_BUTTON;
    private javax.swing.JLabel LocationHeading_LABEL;
    private javax.swing.JPanel Location_PANEL;
    private javax.swing.JScrollPane Location_SCROLLPANE;
    private javax.swing.JTextArea Location_TEXTAREA;
    private javax.swing.JPanel Main_PANEL;
    private javax.swing.JLabel TimeDescription_LABEL;
    private javax.swing.JLabel TimeHeading_LABEL;
    private javax.swing.JPanel Time_PANEL;
    private javax.swing.JLabel Title_LABEL;
    private javax.swing.JPanel TopSeperator_PANEL;
    private keeptoo.KGradientPanel Top_GRADIENTPANEL;
    private javax.swing.JLabel TypeByUser_LABEL;

    public EventDetailsView(GetEventDetailsViewModel getEventDetailsViewModel, JoinEventController joinEventController,
                            BackOutController backOutController) {
        initComponents();
        this.getEventDetailsViewModel = getEventDetailsViewModel;
        this.joinEventController = joinEventController;
        this.backOutController = backOutController;
    }

//    // A constructor just for testing
//    public EventDetailsView(GetEventDetailsViewModel getEventDetailsViewModel) {
//        initComponents();
//        this.getEventDetailsViewModel = getEventDetailsViewModel;
//    }

    private void initComponents() {

        Main_PANEL = new javax.swing.JPanel();
        Top_GRADIENTPANEL = new keeptoo.KGradientPanel();
        Title_LABEL = new javax.swing.JLabel();
        TopSeperator_PANEL = new javax.swing.JPanel();
        BottomSeperator_PANEL = new javax.swing.JPanel();
        EventName_LABEL = new javax.swing.JLabel();
        JoinLeaveEvent_BUTTON = new ButtonGradient();
        Back_BUTTON = new ButtonGradient();
        Description_PANEL = new javax.swing.JPanel();
        DescriptionHeading_LABEL = new javax.swing.JLabel();
        Location_PANEL = new javax.swing.JPanel();
        LocationHeading_LABEL = new javax.swing.JLabel();
        Time_PANEL = new javax.swing.JPanel();
        TimeHeading_LABEL = new javax.swing.JLabel();
        Description_SCROLLPANE = new javax.swing.JScrollPane();
        Description_TEXTAREA = new javax.swing.JTextArea();
        TypeByUser_LABEL = new javax.swing.JLabel();
        BottomSeperator_PANEL1 = new javax.swing.JPanel();
        Capacity_LABEL = new javax.swing.JLabel();
        Location_SCROLLPANE = new javax.swing.JScrollPane();
        Location_TEXTAREA = new javax.swing.JTextArea();
        TimeDescription_LABEL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main_PANEL.setBackground(new java.awt.Color(255, 255, 255));

        Top_GRADIENTPANEL.setkEndColor(new java.awt.Color(140, 100, 255));
        Top_GRADIENTPANEL.setkGradientFocus(400);
        Top_GRADIENTPANEL.setkStartColor(new java.awt.Color(223, 131, 255));
        Top_GRADIENTPANEL.setPreferredSize(new java.awt.Dimension(350, 40));

        Title_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 14)); // NOI18N
        Title_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        Title_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title_LABEL.setText("Event details");
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

        EventName_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        EventName_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        EventName_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
//        EventName_LABEL.setText("Drop-In Outdoor Frisbee, Aug 12 '23");

        JoinLeaveEvent_BUTTON.setText("Join Event");
        JoinLeaveEvent_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoinLeaveEvent_BUTTONActionPerformed(evt);
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

        Description_PANEL.setBackground(new java.awt.Color(251, 247, 255));
        Description_PANEL.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 222, 233)), null));

        DescriptionHeading_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        DescriptionHeading_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        DescriptionHeading_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DescriptionHeading_LABEL.setText("Description");

        javax.swing.GroupLayout Description_PANELLayout = new javax.swing.GroupLayout(Description_PANEL);
        Description_PANEL.setLayout(Description_PANELLayout);
        Description_PANELLayout.setHorizontalGroup(
                Description_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Description_PANELLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(DescriptionHeading_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        Description_PANELLayout.setVerticalGroup(
                Description_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Description_PANELLayout.createSequentialGroup()
                                .addContainerGap(11, Short.MAX_VALUE)
                                .addComponent(DescriptionHeading_LABEL)
                                .addContainerGap())
        );

        Location_PANEL.setBackground(new java.awt.Color(251, 247, 255));
        Location_PANEL.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 222, 233)), null));

        LocationHeading_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        LocationHeading_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        LocationHeading_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LocationHeading_LABEL.setText("Location");

        javax.swing.GroupLayout Location_PANELLayout = new javax.swing.GroupLayout(Location_PANEL);
        Location_PANEL.setLayout(Location_PANELLayout);
        Location_PANELLayout.setHorizontalGroup(
                Location_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Location_PANELLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(LocationHeading_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        Location_PANELLayout.setVerticalGroup(
                Location_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Location_PANELLayout.createSequentialGroup()
                                .addContainerGap(11, Short.MAX_VALUE)
                                .addComponent(LocationHeading_LABEL)
                                .addContainerGap())
        );

        Time_PANEL.setBackground(new java.awt.Color(251, 247, 255));
        Time_PANEL.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 222, 233)), null));

        TimeHeading_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        TimeHeading_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        TimeHeading_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TimeHeading_LABEL.setText("Time");

        javax.swing.GroupLayout Time_PANELLayout = new javax.swing.GroupLayout(Time_PANEL);
        Time_PANEL.setLayout(Time_PANELLayout);
        Time_PANELLayout.setHorizontalGroup(
                Time_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Time_PANELLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(TimeHeading_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        Time_PANELLayout.setVerticalGroup(
                Time_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Time_PANELLayout.createSequentialGroup()
                                .addContainerGap(11, Short.MAX_VALUE)
                                .addComponent(TimeHeading_LABEL)
                                .addContainerGap())
        );

        Description_SCROLLPANE.setBorder(null);

        Description_TEXTAREA.setEditable(false);
        Description_TEXTAREA.setBackground(new java.awt.Color(255, 255, 255));
        Description_TEXTAREA.setColumns(20);
        Description_TEXTAREA.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        Description_TEXTAREA.setForeground(new java.awt.Color(140, 100, 255));
        Description_TEXTAREA.setLineWrap(true);
        Description_TEXTAREA.setRows(5);
//        Description_TEXTAREA.setText("Join us for a day of spirited throws and friendly competition at our Drop-In Outdoor Frisbee sessions! No experience? No problem! Players of all levels are encouraged to join.");
        Description_TEXTAREA.setCaretColor(new java.awt.Color(255, 255, 255));
        Description_TEXTAREA.setSelectionColor(new java.awt.Color(140, 100, 255));
        Description_SCROLLPANE.setViewportView(Description_TEXTAREA);

        TypeByUser_LABEL.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        TypeByUser_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        TypeByUser_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
//        TypeByUser_LABEL.setText("Frisbee by iloveDucks");

        BottomSeperator_PANEL1.setBackground(new java.awt.Color(229, 222, 233));
        BottomSeperator_PANEL1.setPreferredSize(new java.awt.Dimension(100, 1));

        javax.swing.GroupLayout BottomSeperator_PANEL1Layout = new javax.swing.GroupLayout(BottomSeperator_PANEL1);
        BottomSeperator_PANEL1.setLayout(BottomSeperator_PANEL1Layout);
        BottomSeperator_PANEL1Layout.setHorizontalGroup(
                BottomSeperator_PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        BottomSeperator_PANEL1Layout.setVerticalGroup(
                BottomSeperator_PANEL1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1, Short.MAX_VALUE)
        );

        Capacity_LABEL.setFont(new java.awt.Font("Gotham Medium", 3, 10)); // NOI18N
        Capacity_LABEL.setForeground(new java.awt.Color(255, 102, 197));
        Capacity_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
//        Capacity_LABEL.setText("4/12 People"); //TODO here

        Location_SCROLLPANE.setBorder(null);
        Location_SCROLLPANE.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Location_SCROLLPANE.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        Location_TEXTAREA.setEditable(false);
        Location_TEXTAREA.setBackground(new java.awt.Color(255, 255, 255));
        Location_TEXTAREA.setColumns(20);
        Location_TEXTAREA.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        Location_TEXTAREA.setForeground(new java.awt.Color(140, 100, 255));
        Location_TEXTAREA.setLineWrap(true);
        Location_TEXTAREA.setRows(5);
//        Location_TEXTAREA.setText("Toronto, ON, Canada, M5S 2E5\nBack Campus"); //TODO here
        Location_TEXTAREA.setCaretColor(new java.awt.Color(255, 255, 255));
        Location_TEXTAREA.setSelectionColor(new java.awt.Color(140, 100, 255));
        Location_SCROLLPANE.setViewportView(Location_TEXTAREA);

        TimeDescription_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        TimeDescription_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        TimeDescription_LABEL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        TimeDescription_LABEL.setText("10:00 AM to 01:00 PM"); //TODO here

        javax.swing.GroupLayout Main_PANELLayout = new javax.swing.GroupLayout(Main_PANEL);
        Main_PANEL.setLayout(Main_PANELLayout);
        Main_PANELLayout.setHorizontalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(BottomSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                                .addComponent(Back_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(JoinLeaveEvent_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(EventName_LABEL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(TypeByUser_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(BottomSeperator_PANEL1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                        .addComponent(Capacity_LABEL, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(Location_SCROLLPANE)
                                        .addComponent(Description_SCROLLPANE)
                                        .addComponent(Description_PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Location_PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Time_PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(TimeDescription_LABEL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Main_PANELLayout.setVerticalGroup(
                Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Main_PANELLayout.createSequentialGroup()
                                .addComponent(Top_GRADIENTPANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EventName_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TypeByUser_LABEL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BottomSeperator_PANEL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Capacity_LABEL)
                                .addGap(18, 18, 18)
                                .addComponent(Time_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TimeDescription_LABEL)
                                .addGap(18, 18, 18)
                                .addComponent(Location_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Location_SCROLLPANE, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Description_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Description_SCROLLPANE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(BottomSeperator_PANEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Main_PANELLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Back_BUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(JoinLeaveEvent_BUTTON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Main_PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Main_PANEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void JoinLeaveEvent_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO change this for later
        System.out.println("You just joined this event!");
    }

    private void Back_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO change this later
        System.out.println("Go back");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EventDetailsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventDetailsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventDetailsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventDetailsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                GetEventDetailsViewModel getEventDetailsViewModel = new GetEventDetailsViewModel();

                JoinEventController joinEventController = new JoinEventController();
                BackOutController backOutController = new BackOutController();

                EventDetailsView view = new EventDetailsView(getEventDetailsViewModel, joinEventController, backOutController);
                getEventDetailsViewModel.addPropertyChangeListener(view);


                try {
                    LocationFactory factory = new CommonLocationFactory();
                    Location location = factory.makeLocation("(43.665510,-79.387280)"); // Home, within 2KM
                    Event event = new CommonEvent(1, "badminton", "owner", location, new ArrayList<>(),
                            new ArrayList<>(), LocalDateTime.now(), "type", "description", false,
                            10); // This event should be returned

                    GetEventDetailsPresenter presenter = new GetEventDetailsPresenter(getEventDetailsViewModel, viewManagerModel);
                    GetEventDetailsOutputData outputData = new GetEventDetailsOutputData(event.getOwnerUser(),
                            event.getEventName(), event.getEventAddress(), event.getEventDate(), event.getDescription(),
                            String.valueOf(event.getCapacity()));
                    presenter.prepareView(outputData);
                    view.setVisible(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                view.setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) { // Set the label by values given
            GetEventDetailsState state = (GetEventDetailsState) evt.getNewValue();
            String ownerUser = state.getOwnerUser();
            String name = state.getEventName();
            String address = state.getEventAddress();
            String time = state.getEventDate();
            String description = state.getEventDescription();
            String capacity = state.getEventCapacity();

            TypeByUser_LABEL.setText(name + " by " + ownerUser);
            Capacity_LABEL.setText(capacity + " People");
            Location_TEXTAREA.setText(address);
            TimeDescription_LABEL.setText(time);
            Description_TEXTAREA.setText(description);
            EventName_LABEL.setText(name + " " + time);




        }

    }
}
