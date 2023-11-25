//TODO: Add imports

package view;

import interface_adapter.join_event.JoinEventController;
import interface_adapter.search_nearby.SearchNearbyController;
import interface_adapter.search_nearby.SearchNearbyViewModel;

/**
 *
 * @author submergedduck
 */
public class SearchNearbyView extends javax.swing.JFrame {

    /**
     * Creates new form SearchNearbyView
     */
    public final String viewName = "search nearby";
    private final SearchNearbyViewModel searchNearbyViewModel;

    private final SearchNearbyController searchNearbyController;
    // private final GoBackController TODO: GoBack should be implemented as a use case
    private final JoinEventController joinEventController;
    private EventLoader eventLoader = new EventLoader(this); // This JPane will give the View event details and a list of events searched
    private ButtonGradient Back_BUTTON;
    private javax.swing.JPanel BottomSeperator_PANEL;
    private ButtonGradient EventDetails_BUTTON;
    private javax.swing.JLabel EventNameCreateFailed_LABEL;
    private javax.swing.JLabel Event_LABEL;
    private javax.swing.JList<String> Events_LIST;
    private javax.swing.JScrollPane Events_SCROLLPANE;
    private javax.swing.JPanel Main_PANEL;
    private javax.swing.JLabel Title_LABEL;
    private javax.swing.JPanel TopSeperator_PANEL;
    private keeptoo.KGradientPanel Top_GRADIENTPANEL;

    public SearchNearbyView(SearchNearbyViewModel searchNearbyViewModel, SearchNearbyController searchNearbyController,
                            JoinEventController joinEventController) {
        initComponents();
        this.searchNearbyViewModel = searchNearbyViewModel;
        this.searchNearbyController = searchNearbyController;
        //TODO BackButtonController should be added to here
        this.joinEventController = joinEventController;
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
        Events_LIST = new javax.swing.JList<>();
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

        Events_LIST.setFont(new java.awt.Font("Gotham Medium", 1, 12)); // NOI18N
        Events_LIST.setForeground(new java.awt.Color(196, 182, 206));
        Events_LIST.setModel(new javax.swing.AbstractListModel<String>() {



// TODO: Note: this is a placeholder for the list of events
            String[] strings = { "Bob's Building Workshop (Jun 19 '23) [Cap: 7/10]", "Outdoor Frisbee (Jun 10 '23) [Cap: 4/10]", "LoL 5 v 5 Tourney (Sep 10 '23) [Cap: 25/50]", "Other (Dec 30 '23) [Cap: 1/5]", "Other (Dec 30 '23) [Cap: 1/5]", "Other (Dec 30 '23) [Cap: 1/5]", "Other (Dec 30 '23) [Cap: 1/5]", "Other (Dec 30 '23) [Cap: 1/5]", "Other (Dec 30 '23) [Cap: 1/5]", "Other (Dec 30 '23) [Cap: 1/5]", "Other (Dec 30 '23) [Cap: 1/5]" };
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
                                                        .addComponent(EventNameCreateFailed_LABEL, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }

    private void EventDetails_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void Back_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
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
                if ("Nimbus".equals(info.getName())) {
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
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SearchNearbyView().setVisible(true);
//            }
//        });
    }

}
