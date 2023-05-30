package pages;

import awt.*;
import database.LoginCheck;

import java.awt.*;
import javax.swing.*;

public class Login {

    public static void getLogin() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        panel.setLayout(null);

        frame.setTitle("PolyGlott");
        frame.setSize(1600, 1000);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
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
        title = new JLabel("SIGN IN");
        title.setBounds(140, 200, 200, 40);
        title.setFont(new Font("Century Gothic", Font.BOLD, 41));
        title.setForeground(Color.BLACK);
        panel.add(title);

        Font textFont = new Font("Century Gothic", Font.BOLD, 27);

        JLabel username = BuildFrame.getTextLabel("Username", 140, 300);
        panel.add(username);
        JTextField usernameField = BuildFrame.getTextField(140, 342);
        panel.add(usernameField);

        JLabel password = BuildFrame.getTextLabel("Password", 140, 410);
        panel.add(password);
        JPasswordField passwordField = BuildFrame.getPasswordField(140, 452);
        panel.add(passwordField);

        JButton signInButton = new JButton("SIGN IN");
        signInButton.setFont(textFont);
        signInButton.setBounds(140,520, 150,50);
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(Buttons.orangeButtonColor);
        signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signInButton.addActionListener(
                e -> LoginCheck.loginCheck(usernameField.getText(), String.valueOf(passwordField.getPassword()), frame)

        );
        panel.add(signInButton);

        JLabel noAccount = new JLabel("Don't have an account?");
        noAccount.setBounds(140, 640, 400,30);
        noAccount.setFont(new Font("Century Gothic", Font.PLAIN, 27));
        noAccount.setForeground(Color.BLACK);
        panel.add(noAccount);

        JButton registerButton = new JButton("REGISTER");
        registerButton.setFont(new Font("Century Gothic", Font.PLAIN, 27));
        registerButton.setBounds(140,690, 150,50);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Buttons.orangeButtonColor);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(
                e -> {
                    frame.dispose();
                    Register.getRegister();
                }

        );
        panel.add(registerButton);

        frame.setVisible(true);
    }

}