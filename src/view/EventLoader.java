//package view;
//
//import entity.Events.CommonEvent;
//import entity.Events.Event;
//import entity.Location.CommonLocationFactory;
//import entity.Location.Location;
//import entity.Location.LocationFactory;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeSupport;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Vector;
//
//public class EventLoader extends JPanel {
//    public final String viewName = "events returned by search";
//
//    //public final SearchNearbyBasicView searchNearbyBasicView;
//    final JButton viewEventDetails;
//    JList eventList;
//    ArrayList<Event> eventArrayList;
//    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);
//
//    public EventLoader(JFrame searchNearbyBasicView) {
//        observable.addPropertyChangeListener("View event details", (PropertyChangeListener) searchNearbyBasicView); //The view now observes changes from this event loader
//
//        JLabel title = new JLabel("Here are the events within 2KM from you.");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        eventList = new JList();
//        eventList.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                System.out.println(eventList.getSelectedIndex());
//            }
//        });
//        eventList.setSize(200,200);
//        eventList.setListData(new String[]{"test content"});
//
//        viewEventDetails = new JButton("View event details");
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(viewEventDetails);
//        buttonPanel.setAlignmentX(Component.BOTTOM_ALIGNMENT);
//
//        viewEventDetails.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource().equals(viewEventDetails) && eventList.getSelectedIndex() != -1) {
//                    System.out.println("View event details with event name " + eventArrayList.get(eventList.getSelectedIndex()).getEventName());
//                    firePropertyChange("View event details", null, eventArrayList.get(eventList.getSelectedIndex()));
//                } else {
//                    System.out.println("Please select an event first.");
//                }
//            }
//        });
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.add(title, BorderLayout.NORTH);
//        this.add(eventList, BorderLayout.CENTER);
//        this.add(buttonPanel, BorderLayout.SOUTH);
//
//    }
//
//    /**
//     * The below class is for testing, it does not have SearchNearbyBasicView as an observer.
//     */
//    public EventLoader() {
//        JLabel title = new JLabel("Here are the events within 2KM from you.");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        eventList = new JList();
//        eventList.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                System.out.println(eventList.getSelectedIndex());
//            }
//        });
//        eventList.setSize(200,200);
//        eventList.setListData(new String[]{"test content"});
//
//        viewEventDetails = new JButton("View event details");
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(viewEventDetails);
//        buttonPanel.setAlignmentX(Component.BOTTOM_ALIGNMENT);
//
//        viewEventDetails.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource().equals(viewEventDetails) && eventList.getSelectedIndex() != -1) {
//                    System.out.println("View event details with event name " + eventArrayList.get(eventList.getSelectedIndex()).getEventName());
//                    firePropertyChange("View event details", null, eventArrayList.get(eventList.getSelectedIndex())); // Notify observer to give details of the selected event
//                } else {
//                    System.out.println("Please select an event first.");
//                }
//            }
//        });
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.add(title, BorderLayout.NORTH);
//        this.add(eventList, BorderLayout.CENTER);
//        this.add(buttonPanel, BorderLayout.SOUTH);
//
//    }
//
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Event loader");
//        EventLoader eventLoader = new EventLoader();
//        frame.getContentPane().add(eventLoader, BorderLayout.CENTER);
//        frame.setSize(400, 300);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        try {
//            LocationFactory factory = new CommonLocationFactory();
//            Location location = factory.makeLocation("(43.665510,-79.387280)"); // Home, within 2KM
//            Location location2 = factory.makeLocation("(43.645531,-79.380348)"); // Union Station (3KM)
//            Event event = new CommonEvent(1, "badminton", "owner", location, new ArrayList<>(),
//                    new ArrayList<>(), LocalDateTime.now(), "type", "description", false,
//                    10); // This event should be returned
//            Event event2 = new CommonEvent(2, "group trip", "owner", location2, new ArrayList<>(),
//                    new ArrayList<>(), LocalDateTime.now(), "type", "description", false, 10);
//
//            ArrayList<Event> eventArrayList = new ArrayList<>();
//            eventArrayList.add(event);
//            eventArrayList.add(event2);
//
//            eventLoader.load(eventArrayList);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public void load(ArrayList<Event> eventsFound) {
//        this.eventArrayList = eventsFound;
//        ArrayList<String> strEventList = new ArrayList<>();
//        //eventList.setCellRenderer(new EventLabelRenderer()); TODO: If there's more time, use a customized CellRenderer to display the content nicer.
//
//
//        for (Event event: eventsFound) {
//            String str = event.getEventName() + "\n" + "Location: " + event.getLocation().getAddress() + "\n" + "Time: " + event.getTime().toString();
//           // System.out.println(str);
//            strEventList.add(str);
//        }
//        eventList.setListData(strEventList.toArray());
//
//        }
//    }
