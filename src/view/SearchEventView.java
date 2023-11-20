package view;

import interface_adapter.search_event.SearchEventController;
import interface_adapter.search_event.SearchEventViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//public class SearchEventView extends JPanel implements ActionListener, PropertyChangeListener {
//    public final String viewName = "Search an event!";
//
//    private final SearchEventViewModel searchEventViewModel;
//    private final JTextField searchRequestInputField;
//    private final SearchEventController searchController;
//
//    private final JButton search;
//    private final JButton back; //TODO: this back button will trigger the BackOut use case
//
//    public SearchEventView(SearchEventController searchController, SearchEventViewModel searchViewModel) {
//        this.searchController = searchController;
//        this.searchEventViewModel = searchViewModel;
//
//        JLabel title = new JLabel(SearchEventViewModel.TITLE_LABEL);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        LabelTextPanel searchRequest = new LabelTextPanel(new JLabel(SearchEventViewModel.SEARCH_LABEL), searchRequestInputField);
//        JPanel buttons = new JPanel();
//        search = new JButton(SearchEventViewModel.SEARCH_EVENT_BUTTON_LABEL);
//        buttons.add(search);
//
//        search.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(search)) {
//                            //TODO: complete
//                        }
//                    }
//                }
//        );
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//
//    }
//}
