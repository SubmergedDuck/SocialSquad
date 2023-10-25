package view;
import entity.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
public class LoginView extends JFrame{


        private JTextField txtUser;
        private JPasswordField txtPass;
        private JTextField longID;

        private JLabel lblUser;
        private JLabel lblPass;
        private JLabel lblID;
        private JButton btnLogin;
        private JButton btnExit;
        private JPanel panel1;

        ArrayList<User> users = new ArrayList<User>();

        public LoginView()
        {
            setLayout(new BorderLayout(10, 10));
            panel1 = new JPanel();
            panel1.setPreferredSize(new Dimension(250, 250));

            txtUser = new JTextField();
            txtUser.setPreferredSize(new Dimension(150, 20));
            txtPass = new JPasswordField();
            txtPass.setPreferredSize(new Dimension(150, 20));
            longID= new JTextField();
            longID.setPreferredSize(new Dimension(150, 20));
            lblUser = new JLabel("Username: ");
            lblPass = new JLabel("Password: ");
            lblID = new JLabel ("Student ID: ");



            btnLogin = new JButton("Login");
            btnExit = new JButton("Exit");


            panel1.add(lblUser);
            panel1.add(txtUser);
            panel1.add(lblPass);
            panel1.add(txtPass);
            panel1.add(lblID);
            panel1.add(longID);

            panel1.add(btnLogin);
            panel1.add(btnExit);

//            ActionListener LoginAction = new LoginListener();
//            btnLogin.addActionListener(LoginAction);
//
//            ActionListener ExitAction = new ExitListener();
//            btnExit.addActionListener(ExitAction);
//            add(panel1, BorderLayout.CENTER);
        }

        {
            try
            {
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

        }


    }

