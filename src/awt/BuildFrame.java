package awt;

import javax.swing.*;
import java.awt.*;

public class BuildFrame extends JFrame {

    public static JFrame getFrame() {

        JFrame frame = new JFrame();

        frame.setTitle("PolyGlott");
        frame.setSize(1600, 1000);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("img/logo.png");
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    public static JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        return panel;
    }

    public static JLabel getTextLabel(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x,y,300,30);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Century Gothic", Font.BOLD, 27));
        return label;
    }

    public static JTextField getTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x,y,400,40);
        textField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        textField.setBackground(new Color(247,247,247));
        return textField;
    }

    public static JPasswordField getPasswordField(int x, int y) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x,y,400,40);
        passwordField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        passwordField.setBackground(new Color(247,247,247));
        return passwordField;
    }
}