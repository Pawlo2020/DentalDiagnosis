package Presentation;

import DentalComponent.DentalDiagnoser;
import DentalComponent.DiagnoserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

public class ContentPanel extends JPanel {

    JButton but1;
    JButton but2;
    JButton but3;


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

        but1.setBackground(new Color(55, 66, 250));
        but2.setBackground(new Color(55, 66, 250));
        but3.setBackground(new Color(55, 66, 250));

        but1.setForeground(Color.white);
        but2.setForeground(Color.white);
        but3.setForeground(Color.white);

        panelsSwitcher.add(but1);
        panelsSwitcher.add(but2);
        panelsSwitcher.add(but3);

        add(panelsSwitcher);

        switchPanel(1);


        DentalDiagnoser diag = new DentalDiagnoser();
        diag.setPreferredSize(new Dimension(960,600));
        add(diag);



    }

    void switchPanel(int i){




    }


}
