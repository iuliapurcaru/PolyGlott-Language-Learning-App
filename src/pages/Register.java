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

        Font textFont = new Font("Century Gothic", Font.BOLD, 27);

        JLabel email = BuildFrame.getTextLabel("Email", 140, 190);
        panel.add(email);
        JTextField emailField = BuildFrame.getTextField(140, 232);
        panel.add(emailField);

        JLabel username = BuildFrame.getTextLabel("Username", 140, 300);
        panel.add(username);
        JTextField usernameField = BuildFrame.getTextField(140, 342);
        panel.add(usernameField);

        JLabel password = BuildFrame.getTextLabel("Password", 140, 410);
        panel.add(password);
        JPasswordField passwordField = BuildFrame.getPasswordField(140, 452);
        panel.add(passwordField);

        JLabel confirmPassword = BuildFrame.getTextLabel("Confirm password", 140, 520);
        panel.add(confirmPassword);
        JPasswordField confirmPasswordField = BuildFrame.getPasswordField(140, 562);
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