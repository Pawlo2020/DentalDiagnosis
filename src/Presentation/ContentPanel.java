package Presentation;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    public ContentPanel(){
        super();
        setMaximumSize(new Dimension(960, 720));
        BoxLayout contentLayout = new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(contentLayout);
        setBackground(new Color(255, 255, 255));

        //Beans



    }
}
