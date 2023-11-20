/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
//
//import interface_adapter.login.LoginController;
//import interface_adapter.login.LoginState;
//import interface_adapter.login.LoginViewModel;
//import interface_adapter.signup.SignupController;
//import interface_adapter.signup.SignupState;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//
//public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {
//
//    public final String viewName = "log in";
//    private final LoginViewModel loginViewModel;
//
//    final JTextField usernameInputField = new JTextField(15);
//    private final JLabel usernameErrorField = new JLabel();
//
//    final JPasswordField passwordInputField = new JPasswordField(15);
//    private final JLabel passwordErrorField = new JLabel();
//
//
//    final JButton logIn;
//    final JButton createANewAccount;
//    private final LoginController loginController;
//
//    public LoginView(LoginViewModel loginViewModel, LoginController controller) {
//
//        this.loginController = controller;
//        this.loginViewModel = loginViewModel;
//        this.loginViewModel.addPropertyChangeListener(this);
//
//        JLabel title = new JLabel("Social Squad");
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        LabelTextPanel usernameInfo = new LabelTextPanel(
//                new JLabel("Username"), usernameInputField);
//        LabelTextPanel passwordInfo = new LabelTextPanel(
//                new JLabel("Password"), passwordInputField);
//
//        JPanel buttons = new JPanel();
//        createANewAccount = new JButton(loginViewModel.CREATE_ACCOUNT_BUTTON_LABEL);
//        buttons.add(createANewAccount);
//        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
//        buttons.add(logIn);
//
//        createANewAccount.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if(e.getSource().equals(createANewAccount)){
//                            LoginState currentState = loginViewModel.getState();
//                            LoginView.this.loginController.execute(
//                                    currentState.getUsername(), currentState.getPassword()
//                            );
//
//                        }
//                    }
//                }
//        );
//
//        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(logIn)) {
//                            LoginState currentState = loginViewModel.getState();
//
//                            loginController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword()
//                            );
//                        }
//                    }
//                }
//        );
//
//
//
//        usernameInputField.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                LoginState currentState = loginViewModel.getState();
//                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
//                loginViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        });
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        passwordInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        LoginState currentState = loginViewModel.getState();
//                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
//                        loginViewModel.setState(currentState);
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//                    }
//                });
//
//        this.add(title);
//        this.add(usernameInfo);
//        this.add(usernameErrorField);
//        this.add(passwordInfo);
//        this.add(passwordErrorField);
//        this.add(buttons);
//    }
//
//    /**
//     * React to a button click that results in evt.
//     */
//    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click " + evt.getActionCommand());
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        LoginState state = (LoginState) evt.getNewValue();
//        setFields(state);
//    }
//
//
//    private void setFields(LoginState state) {
//        usernameInputField.setText(state.getUsername());
//    }
//
//}
import javax.swing.*;

/**
 *
 * @author submergedduck
 */
public class LoginView extends JFrame {

    /**
     * Creates new form loginView
     */
    public LoginView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main_PANEL = new JPanel();
        Top_GRADIENTPANEL = new keeptoo.KGradientPanel();
        SocialSquadTitle_LABEL = new JLabel();
        TopSeperator_PANEL = new JPanel();
        SignIn_BUTTON = new ButtonGradient();
        SignUp_BUTTON = new ButtonGradient();
        Username_LABEL = new JLabel();
        SocialSquadTitle_LABEL2 = new JLabel();
        BottomSeperator_PANEL = new JPanel();
        Password_LABEL = new JLabel();
        Password_PASSWORDFIELD = new JPasswordField();
        Username_TEXTFIELD = new JTextField();
        UsernameLoginFailed_LABEL = new JLabel();
        PasswordLoginFailed_LABEL = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Main_PANEL.setBackground(new java.awt.Color(255, 255, 255));

        Top_GRADIENTPANEL.setkEndColor(new java.awt.Color(140, 100, 255));
        Top_GRADIENTPANEL.setkGradientFocus(30);
        Top_GRADIENTPANEL.setkStartColor(new java.awt.Color(223, 131, 255));

        SocialSquadTitle_LABEL.setFont(new java.awt.Font("Gotham", 1, 25)); // NOI18N
        SocialSquadTitle_LABEL.setForeground(new java.awt.Color(255, 255, 255));
        SocialSquadTitle_LABEL.setHorizontalAlignment(SwingConstants.CENTER);
        SocialSquadTitle_LABEL.setText("Social Squad");

        TopSeperator_PANEL.setBackground(new java.awt.Color(118, 43, 236));
        TopSeperator_PANEL.setPreferredSize(new java.awt.Dimension(100, 1));
        TopSeperator_PANEL.setSize(new java.awt.Dimension(100, 1));

        GroupLayout TopSeperator_PANELLayout = new GroupLayout(TopSeperator_PANEL);
        TopSeperator_PANEL.setLayout(TopSeperator_PANELLayout);
        TopSeperator_PANELLayout.setHorizontalGroup(
            TopSeperator_PANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        TopSeperator_PANELLayout.setVerticalGroup(
            TopSeperator_PANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        GroupLayout Top_GRADIENTPANELLayout = new GroupLayout(Top_GRADIENTPANEL);
        Top_GRADIENTPANEL.setLayout(Top_GRADIENTPANELLayout);
        Top_GRADIENTPANELLayout.setHorizontalGroup(
            Top_GRADIENTPANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(SocialSquadTitle_LABEL, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
            .addComponent(TopSeperator_PANEL, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        Top_GRADIENTPANELLayout.setVerticalGroup(
            Top_GRADIENTPANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, Top_GRADIENTPANELLayout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(SocialSquadTitle_LABEL, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TopSeperator_PANEL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        SignIn_BUTTON.setText("Sign In");
        SignIn_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignIn_BUTTONActionPerformed(evt);
            }
        });

        SignUp_BUTTON.setText("Create Account");
        SignUp_BUTTON.setHorizontalTextPosition(SwingConstants.CENTER);
        SignUp_BUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUp_BUTTONActionPerformed(evt);
            }
        });

        Username_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        Username_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        Username_LABEL.setHorizontalAlignment(SwingConstants.CENTER);
        Username_LABEL.setText("Username");

        SocialSquadTitle_LABEL2.setFont(new java.awt.Font("Gotham", 0, 14)); // NOI18N
        SocialSquadTitle_LABEL2.setForeground(new java.awt.Color(140, 100, 255));
        SocialSquadTitle_LABEL2.setHorizontalAlignment(SwingConstants.CENTER);
        SocialSquadTitle_LABEL2.setText("Don't have an account? Signup now!");

        BottomSeperator_PANEL.setBackground(new java.awt.Color(229, 222, 233));
        BottomSeperator_PANEL.setPreferredSize(new java.awt.Dimension(100, 1));
        BottomSeperator_PANEL.setSize(new java.awt.Dimension(100, 1));

        GroupLayout BottomSeperator_PANELLayout = new GroupLayout(BottomSeperator_PANEL);
        BottomSeperator_PANEL.setLayout(BottomSeperator_PANELLayout);
        BottomSeperator_PANELLayout.setHorizontalGroup(
            BottomSeperator_PANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BottomSeperator_PANELLayout.setVerticalGroup(
            BottomSeperator_PANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        Password_LABEL.setFont(new java.awt.Font("Gotham Medium", 0, 12)); // NOI18N
        Password_LABEL.setForeground(new java.awt.Color(140, 100, 255));
        Password_LABEL.setHorizontalAlignment(SwingConstants.CENTER);
        Password_LABEL.setText("Password");

        Password_PASSWORDFIELD.setBackground(new java.awt.Color(251, 247, 255));
        Password_PASSWORDFIELD.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        Password_PASSWORDFIELD.setForeground(new java.awt.Color(196, 182, 206));
        Password_PASSWORDFIELD.setHorizontalAlignment(JTextField.LEFT);
        Password_PASSWORDFIELD.setBorder(BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 222, 233), 1, true), BorderFactory.createEmptyBorder(1, 10, 1, 1)));
        Password_PASSWORDFIELD.setCaretColor(new java.awt.Color(196, 182, 206));
        Password_PASSWORDFIELD.setSelectionColor(new java.awt.Color(140, 100, 255));

        Username_TEXTFIELD.setBackground(new java.awt.Color(251, 247, 255));
        Username_TEXTFIELD.setFont(new java.awt.Font("Gotham Medium", 3, 12)); // NOI18N
        Username_TEXTFIELD.setForeground(new java.awt.Color(196, 182, 206));
        Username_TEXTFIELD.setBorder(BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(229, 222, 233), 1, true), BorderFactory.createEmptyBorder(1, 10, 1, 1)));
        Username_TEXTFIELD.setCaretColor(new java.awt.Color(196, 182, 206));
        Username_TEXTFIELD.setSelectionColor(new java.awt.Color(140, 100, 255));
        Username_TEXTFIELD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Username_TEXTFIELDActionPerformed(evt);
            }
        });

        UsernameLoginFailed_LABEL.setFont(new java.awt.Font("Gotham Medium", 3, 10)); // NOI18N
        UsernameLoginFailed_LABEL.setForeground(new java.awt.Color(255, 102, 197));
        UsernameLoginFailed_LABEL.setHorizontalAlignment(SwingConstants.RIGHT);
        UsernameLoginFailed_LABEL.setText("*Login Failed: username invalid");

        PasswordLoginFailed_LABEL.setFont(new java.awt.Font("Gotham Medium", 3, 10)); // NOI18N
        PasswordLoginFailed_LABEL.setForeground(new java.awt.Color(255, 102, 197));
        PasswordLoginFailed_LABEL.setHorizontalAlignment(SwingConstants.RIGHT);
        PasswordLoginFailed_LABEL.setText("*Login Failed: password invalid");

        GroupLayout Main_PANELLayout = new GroupLayout(Main_PANEL);
        Main_PANEL.setLayout(Main_PANELLayout);
        Main_PANELLayout.setHorizontalGroup(
            Main_PANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, Main_PANELLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Main_PANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, Main_PANELLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(Main_PANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, Main_PANELLayout.createSequentialGroup()
                                .addComponent(SignUp_BUTTON, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))
                            .addGroup(GroupLayout.Alignment.TRAILING, Main_PANELLayout.createSequentialGroup()
                                .addComponent(SocialSquadTitle_LABEL2)
                                .addGap(47, 47, 47))
                            .addGroup(GroupLayout.Alignment.TRAILING, Main_PANELLayout.createSequentialGroup()
                                .addComponent(SignIn_BUTTON, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99))
                            .addGroup(GroupLayout.Alignment.TRAILING, Main_PANELLayout.createSequentialGroup()
                                .addGroup(Main_PANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(Password_LABEL)
                                    .addComponent(Password_PASSWORDFIELD, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Username_TEXTFIELD, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Username_LABEL)
                                    .addComponent(UsernameLoginFailed_LABEL, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PasswordLoginFailed_LABEL, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48))))
                    .addGroup(GroupLayout.Alignment.TRAILING, Main_PANELLayout.createSequentialGroup()
                        .addComponent(BottomSeperator_PANEL, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(Top_GRADIENTPANEL, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        Main_PANELLayout.setVerticalGroup(
            Main_PANELLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(Main_PANELLayout.createSequentialGroup()
                .addComponent(Top_GRADIENTPANEL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(Username_LABEL)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Username_TEXTFIELD, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(UsernameLoginFailed_LABEL)
                .addGap(5, 5, 5)
                .addComponent(Password_LABEL)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Password_PASSWORDFIELD, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordLoginFailed_LABEL)
                .addGap(46, 46, 46)
                .addComponent(SignIn_BUTTON, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(BottomSeperator_PANEL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(SocialSquadTitle_LABEL2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SignUp_BUTTON, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Main_PANEL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(Main_PANEL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /** work from old loginView
        try {
            Scanner in = new Scanner(new File("USERDATA.txt"));
            while (in.hasNextLine())
            {
                String S=in.nextLine();
                String[] Sa= S.split(",");

                boolean hasUppercase = !txtPass.equals(txtPass.getText().toLowerCase()); //
                boolean hasSpecial   = !Sa[1].matches("[A-Za-z0-9 ]*");
                boolean hasSpace = Sa[1].matches(" ");

                if ((Sa[1].length() < 10))
                {
                    JOptionPane.showMessageDialog(null,
                            "Password must have a minimum of 10 characters", "Error",
                            JOptionPane.ERROR_MESSAGE);


                }

                else if (hasSpace)
                {
                    JOptionPane.showMessageDialog(null,
                            "Password must not have a space", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }



                else if (!hasUppercase)
                {
                    JOptionPane.showMessageDialog(null,
                            "Password must have at least 1 uppercase letter", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }


                else if (!hasSpecial)
                {
                    JOptionPane.showMessageDialog(null,
                            "Password must have at least 1 number", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                else
                {
                    JOptionPane.showMessageDialog(null,
                            "Invalid Username / Password Combo", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                in.close();

            }
            in.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    */


    private void SignIn_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignIn_BUTTONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SignIn_BUTTONActionPerformed

    private void SignUp_BUTTONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUp_BUTTONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SignUp_BUTTONActionPerformed

    private void Username_TEXTFIELDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Username_TEXTFIELDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Username_TEXTFIELDActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Create and display the form (for seeing how view looks purposes)*/
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new loginView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel BottomSeperator_PANEL;
    private JPanel Main_PANEL;
    private JLabel PasswordLoginFailed_LABEL;
    private JLabel Password_LABEL;
    private JPasswordField Password_PASSWORDFIELD;
    private ButtonGradient SignIn_BUTTON;
    private ButtonGradient SignUp_BUTTON;
    private JLabel SocialSquadTitle_LABEL;
    private JLabel SocialSquadTitle_LABEL2;
    private JPanel TopSeperator_PANEL;
    private keeptoo.KGradientPanel Top_GRADIENTPANEL;
    private JLabel UsernameLoginFailed_LABEL;
    private JLabel Username_LABEL;
    private JTextField Username_TEXTFIELD;
    // End of variables declaration//GEN-END:variables
}