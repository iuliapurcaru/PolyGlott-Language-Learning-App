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
        Dimension sizeBkg = background.getPreferredSize();
        background.setBounds(sizeBkg.width - 70, 228, sizeBkg.width, sizeBkg.height);
        panel.add(background);
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon("img/logo-title.png"));
        Dimension sizeLogo = logo.getPreferredSize();
        logo.setBounds(sizeLogo.width - 70, 0, sizeLogo.width, sizeLogo.height);
        panel.add(logo);
        panel.setBackground(Color.WHITE);

        JLabel title;
        title = new JLabel("SIGN UP");
        title.setBounds(140, 100, 160, 40);
        title.setFont(new Font("Century Gothic", Font.BOLD, 41));
        title.setForeground(Color.BLACK);
        panel.add(title);

        Color gray = new Color(247,247,247);
        Font textFont = new Font("Century Gothic", Font.BOLD, 27);
        Font textFieldFont = new Font("Century Gothic", Font.PLAIN, 20);

        JLabel email;
        JTextField emailField;
        email = new JLabel("Email");
        email.setBounds(140,190,300,30);
        email.setForeground(Color.BLACK);
        email.setFont(textFont);
        panel.add(email);
        emailField = new JTextField();
        emailField.setBounds(140,232,400,40);
        emailField.setFont(textFieldFont);
        emailField.setBackground(gray);
        panel.add(emailField);

        JLabel username;
        JTextField usernameField;
        username = new JLabel("Username");
        username.setBounds(140,300,300,30);
        username.setForeground(Color.BLACK);
        username.setFont(textFont);
        panel.add(username);
        usernameField = new JTextField();
        usernameField.setBounds(140,342,400,40);
        usernameField.setFont(textFieldFont);
        usernameField.setBackground(gray);
        panel.add(usernameField);

        JLabel password;
        JPasswordField passwordField;
        password = new JLabel("Password");
        password.setBounds(140,410,300,30);
        password.setForeground(Color.BLACK);
        password.setFont(textFont);
        panel.add(password);
        passwordField = new JPasswordField();
        passwordField.setBounds(140,452,400,40);
        passwordField.setFont(textFieldFont);
        passwordField.setBackground(gray);
        panel.add(passwordField);

        JLabel confirmPassword;
        JPasswordField confirmPasswordField;
        confirmPassword = new JLabel("Confirm password");
        confirmPassword.setBounds(140,520,300,30);
        confirmPassword.setForeground(Color.BLACK);
        confirmPassword.setFont(textFont);
        panel.add(confirmPassword);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(140,562,400,40);
        confirmPasswordField.setFont(textFieldFont);
        confirmPasswordField.setBackground(gray);
        panel.add(confirmPasswordField);

        Color buttonColor = new Color(245, 117, 5);
        JButton registerButton;
        registerButton = new JButton("REGISTER");
        registerButton.setFont(textFont);
        registerButton.setBounds(140,630, 150,50);
        registerButton.setForeground(Color.WHITE);
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
        account.setBounds(140, 740, 400,30);
        account.setFont(new Font("Century Gothic", Font.PLAIN, 27));
        account.setForeground(Color.BLACK);
        panel.add(account);

        JButton signInButton;
        signInButton = new JButton("SIGN IN");
        signInButton.setFont(new Font("Century Gothic", Font.PLAIN, 27));
        signInButton.setBounds(140,790,150,50);
        signInButton.setForeground(Color.WHITE);
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