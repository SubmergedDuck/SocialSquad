package view;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";
    //private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JPasswordField ageInputField = new JPasswordField(15);
    private final JPasswordField realNameInputField = new JPasswordField(15);
    //private final SignupController signupController;
    //private final JButton signUp;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
