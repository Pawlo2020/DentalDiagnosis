package Presentation;

import DentalComponent.DentalDiagnoser;
import DentalComponent.Tooth;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    JButton but1;
    JButton but2;
    JButton but3;

    JLabel hi;


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

        JPanel midPanel = new JPanel();
        midPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        DentalDiagnoser diag = new DentalDiagnoser();
        diag.setPreferredSize(new Dimension(750,600));
        //diag.addListener(this);
        midPanel.add(diag);

        JPanel sep = new JPanel(new FlowLayout());
        sep.setPreferredSize(new Dimension(125,400));
        midPanel.add(sep);

        JPanel tPanel = new JPanel(new FlowLayout());
        tPanel.setBackground(Color.GREEN);
        tPanel.setPreferredSize(new Dimension(300,400));

        hi = new JLabel("Diagnostyka Zęba");
        //tPanel.add(hi);
        //midPanel.add(tPanel);

        add(midPanel);





    }

    void switchPanel(int i){




    }



}
