package pages;

import awt.BuildFrame;
import awt.Buttons;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Dictionary extends JFrame {
    public static void getDictionary(String username, String language) {

        JPanel panel = new JPanel();
        JFrame frame = BuildFrame.getFrame();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        frame.add(panel);

        JButton[] buttons = Buttons.getButtons(frame, username, language);
        for (int i = 0; i < 7; i++) {
            panel.add(buttons[i]);
        }
        buttons[6].setText("DICTIONARY");

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Century Gothic", Font.BOLD, 25));
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(250, 182, 1270, 700);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        try (BufferedReader reader = new BufferedReader(new FileReader("content/" + language + "/dictionary.csv"))) {
            String line;
            int i = 0;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
                i++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static boolean wordExists(String word, String language) {
        try (BufferedReader reader = new BufferedReader(new FileReader("content/" + language + "/dictionary.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase(word)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addToDictionary(String word, String language) {
        if (!wordExists(word, language)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("content/" + language + "/dictionary.csv", true))) {
                writer.write(word);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}