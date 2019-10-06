package Presentation;

import Presentation.ContentPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    ContentPanel contentPanel;
    JPanel topPanel;

    public MainWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setEnabled(false);
                contentPanel.getDiag().saveState();
            }
        });

        // Global size and bounds of the frame
        setBounds(250, 100, 800, 720);

        // Exclusion on resizable features
        setResizable(false);

        // Setting up background of the frame
        setBackground(new Color(223, 228, 234));

        // Definition of the main layout for title bar, form and button section
        BoxLayout mainlayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        setLayout(mainlayout);

        initGUI();
    }

    void initGUI(){
        topPanel = new JPanel();
        JLabel titleLabel = new JLabel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setMaximumSize(new Dimension(960, 60));
        topPanel.setBackground(new Color(45, 152, 218));

        titleLabel.setText("Dental Diagnosis");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Verdana",Font.PLAIN,24));

        add(topPanel);
        topPanel.add(titleLabel);

        contentPanel = new ContentPanel();
        add(contentPanel);
    }
}
