package Presentation;

import DentalComponent.DentalDiagnoser;
import DentalComponent.Tooth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentPanel extends JPanel {

    public DentalDiagnoser getDiag() {
        return diag;
    }

    DentalDiagnoser diag;

    public ContentPanel(){
        super();
        setMaximumSize(new Dimension(960, 720));
        BoxLayout contentLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(contentLayout);
        setBackground(new Color(255, 255, 255));

        //Panels controller
        JPanel panelsSwitcher = new JPanel();
        panelsSwitcher.setLayout(new FlowLayout());
        JButton but1 = new JButton("Pacjent 1");
        JButton but2 = new JButton("Pacjent 2");
        JButton but3 = new JButton("Pacjent 3");

        but1.setBackground(Color.orange);
        but2.setBackground(new Color(55, 66, 250));
        but3.setBackground(new Color(55, 66, 250));

        but1.setForeground(Color.white);
        but2.setForeground(Color.white);
        but3.setForeground(Color.white);

        panelsSwitcher.add(but1);
        panelsSwitcher.add(but2);
        panelsSwitcher.add(but3);

        add(panelsSwitcher);

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        diag = new DentalDiagnoser();
        diag.setPreferredSize(new Dimension(750,600));
        midPanel.add(diag);

        JPanel sep = new JPanel(new FlowLayout());
        sep.setPreferredSize(new Dimension(125,400));
        midPanel.add(sep);

        JPanel tPanel = new JPanel(new FlowLayout());
        tPanel.setBackground(Color.GREEN);
        tPanel.setPreferredSize(new Dimension(300,400));

        add(midPanel);

        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diag.setPatient(0);
                but1.setBackground(Color.orange);
                but2.setBackground(new Color(55, 66, 250));
                but3.setBackground(new Color(55, 66, 250));
            }
        });

        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diag.setPatient(1);
                but2.setBackground(Color.orange);
                but1.setBackground(new Color(55, 66, 250));
                but3.setBackground(new Color(55, 66, 250));
            }
        });

        but3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diag.setPatient(2);
                but3.setBackground(Color.orange);
                but1.setBackground(new Color(55, 66, 250));
                but2.setBackground(new Color(55, 66, 250));
            }
        });
    }
}
