//package view;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class EventLabelRenderer extends JLabel implements javax.swing.ListCellRenderer {
//    @Override
//    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//
//        if (isSelected) {
//            setBackground(list.getSelectionBackground());
//            setForeground(list.getSelectionForeground());
//        } else {
//            setBackground(list.getBackground());
//            setForeground(list.getForeground());
//        }
//
//        this.setText((String) value);
//        this.setSize(1000, 100);
//        //this.setEnabled(true);
//        return this;
//        //return new JTextField(String.valueOf(value));
//
//        //Set the icon and text.  If icon was null, say so.
////        ImageIcon icon = images[selectedIndex];
////        String pet = petStrings[selectedIndex];
////        setIcon(icon);
////        if (icon != null) {
////            setText(pet);
////            setFont(list.getFont());
////        } else {
////            setUhOhText(pet + " (no image available)",
////                    list.getFont());
////        }
//    }
//}
