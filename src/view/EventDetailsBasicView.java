package view;

import entity.Events.Event;
import interface_adapter.get_event_details.GetEventDetailsController;
import interface_adapter.get_event_details.GetEventDetailsState;
import interface_adapter.get_event_details.GetEventDetailsViewModel;
import interface_adapter.join_event.JoinEventController;
import use_case.get_event_details.GetEventDetailsInteractor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EventDetailsBasicView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "event details";

    private final GetEventDetailsViewModel getEventDetailsViewModel;
    private final GetEventDetailsController getEventDetailsController;
    private final JoinEventController joinEventController;

    //TODO private final GoBackController goBackController; --> the constructor will need to change too.

    private final JButton join;
    private final JButton back;
    private final Event event;

    public EventDetailsBasicView(GetEventDetailsViewModel viewModel,
                                 GetEventDetailsController controller,
                                 JoinEventController joinEventController,
                                 Event event) {
        this.getEventDetailsViewModel = viewModel;
        this.getEventDetailsController = controller;
        this.joinEventController = joinEventController;
        this.event = event; // Not sure if we should store the event whose details is displayed here.

        getEventDetailsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("View event details");
        JPanel eventDetails = new JPanel();

        join = new JButton("Join");
        back = new JButton("back");
        JPanel buttons = new JPanel();
        buttons.add(join);
        buttons.add(back);

        this.add(buttons);

        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(join)) {
                    joinEventController.execute(event);
                }

            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    // TODO: Add GoBack controller after the use case is implemented
                    // goBackController.execute();
                }
            }
        });
    }


//    // Another constructor that has no attributes just for testing out the view
//    public EventDetailsBasicView() {
//
//        JLabel title = new JLabel("View event details");
//
//        join = new JButton("Join");
//        back = new JButton("back");
//        JPanel buttons = new JPanel();
//        buttons.add(join);
//        buttons.add(back);
//
//        this.add(buttons);
//
//        join.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource().equals(join)){
//                    System.out.println("Join");;
//                }
//
//            }
//        });
//
//        back.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource().equals(back)) {
//                    System.out.println("Go back");
//                }
//            }
//        });
//    }
//
    @Override
    public void actionPerformed(ActionEvent e) {
        ;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GetEventDetailsState state = (GetEventDetailsState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, new String[] {state.getEventName(), state.getEventAddress(),
                state.getEventDate(), state.getEventDescription(), state.getEventCapacity()});
    }
//
//    public static void main(String[] args) {
//        EventDetailsBasicView view = new EventDetailsBasicView();
//        JFrame frame = new JFrame();
//        frame.setSize(400,300);
//        frame.add(view);
//        frame.setVisible(true);
//    }
}
