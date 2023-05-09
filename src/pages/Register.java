package pages;

import awt.BuildFrame;
import database.RegisterAccount;

import javax.swing.*;
import java.awt.*;

public class Register {
    public static void getRegister() {
        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        frame.add(panel);

        Color backgroundColor = new Color(242, 175, 136);
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("img/background.png"));
        Dimension size = background.getPreferredSize();
        background.setBounds(size.width - 70, 0, size.width, size.height);
        panel.add(background);
        panel.setBackground(Color.WHITE);

        JLabel title;
        title = new JLabel("SIGN UP");
        title.setBounds(140, 100, 160, 40);
        title.setFont(new Font("Century Gothic", Font.BOLD, 41));
        title.setForeground(Color.BLACK);
        panel.add(title);

        Color gray = new Color(247,247,247);
        JLabel email;
        JTextField emailField;
        email = new JLabel("Email");
        email.setBounds(140,190,300,30);
        email.setForeground(Color.BLACK);
        email.setFont(new Font("Century Gothic", Font.BOLD, 27));
        panel.add(email);
        emailField = new JTextField();
        emailField.setBounds(140,232,400,40);
        emailField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        emailField.setBackground(gray);
        panel.add(emailField);

        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(140,300,300,30);
        username.setForeground(Color.BLACK);
        username.setFont(new Font("Century Gothic", Font.BOLD, 27));
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(140,342,400,40);
        usernameField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        usernameField.setBackground(gray);
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(140,410,300,30);
        password.setForeground(Color.BLACK);
        password.setFont(new Font("Century Gothic", Font.BOLD, 27));
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(140,452,400,40);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        passwordField.setBackground(gray);
        panel.add(passwordField);

        JLabel confirmPassword;
        JPasswordField confirmPasswordField;
        confirmPassword = new JLabel("Confirm password");
        confirmPassword.setBounds(140,520,300,30);
        confirmPassword.setForeground(Color.BLACK);
        confirmPassword.setFont(new Font("Century Gothic", Font.BOLD, 27));
        panel.add(confirmPassword);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(140,562,400,40);
        confirmPasswordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        confirmPasswordField.setBackground(gray);
        panel.add(confirmPasswordField);

        Color buttonColor = new Color(245, 117, 5);
        JButton registerButton;
        registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        registerButton.setBounds(140,630, 130,40);
        registerButton.setForeground(Color.BLACK);
        registerButton.setBackground(buttonColor);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(
                e -> RegisterAccount.registerAccount(usernameField.getText(),
                        emailField.getText(),
                        String.valueOf(passwordField.getPassword()),
                        String.valueOf(confirmPasswordField.getPassword()),
                        frame)

        );
        panel.add(registerButton);

        JLabel account = new JLabel("Already have an account?");
        account.setBounds(140, 710, 400,20);
        account.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        account.setForeground(Color.BLACK);
        panel.add(account);

        JButton signInButton;
        signInButton = new JButton("SIGN IN");
        signInButton.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        signInButton.setBounds(140,750,130,40);
        signInButton.setForeground(Color.BLACK);
        signInButton.setBackground(buttonColor);
        signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signInButton.addActionListener(
                e -> {
                    frame.dispose();
                    Login.getLogin();
                }

        );
        panel.add(signInButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
    }
}