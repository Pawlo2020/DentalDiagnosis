package DentalComponent;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;


public class DentalDiagnoser extends JComponent implements MouseListener {
    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);

    private Jaw _upperJaw;
    private Jaw _downJaw;
    private Image finalUpperJaw;
    private Image finalDownJaw;
    private Image finalTooth;
    private Image finalToothTwo;
    private Image finalToothThreeState2LEFT;
    private Image finalToothThreeState22LEFT;
    private Image finalToothThreeState2;
    private Image finalToothThreeState22;
    private Image finalToothThreeState1;
    private Image finalToothThreeState11;

    public DentalDiagnoser(){

        _downJaw = new Jaw();
        _upperJaw = new Jaw();

        addMouseListener(this);

        generateCoordinates();



        //generateJaws();

        try {

            finalUpperJaw = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/upperJaw.png")).getScaledInstance(300,270,Image.SCALE_DEFAULT);
            finalDownJaw = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/downJaw.png")).getScaledInstance(300,270,Image.SCALE_DEFAULT);

            finalTooth = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/teeth/tOne.png")).getScaledInstance(40,40,Image.SCALE_DEFAULT);
            finalToothTwo = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/teeth/tTwo.png")).getScaledInstance(40,40,Image.SCALE_DEFAULT);
            finalToothThreeState2LEFT = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/teeth/tThreeState2LEFT.png")).getScaledInstance(40,40,Image.SCALE_DEFAULT);
            finalToothThreeState22LEFT = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/teeth/tThreeState22LEFT.png")).getScaledInstance(40,40,Image.SCALE_DEFAULT);
            finalToothThreeState2 = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/teeth/tThreeState2.png")).getScaledInstance(40,40,Image.SCALE_DEFAULT);
            finalToothThreeState22 = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/teeth/tThreeState22.png")).getScaledInstance(40,40,Image.SCALE_DEFAULT);
            finalToothThreeState1 = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/teeth/tThreeState1.png")).getScaledInstance(40,40,Image.SCALE_DEFAULT);
            finalToothThreeState11 = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/teeth/tThreeState11.png")).getScaledInstance(40,40,Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.out.println("ERROR");
        }




    }

    private void generateCoordinates() {
        _downJaw.getToothList().get(0).setCoords(48,320);
        _downJaw.getToothList().get(1).setCoords(48,360);
        _downJaw.getToothList().get(2).setCoords(52,400);
        _downJaw.getToothList().get(3).setCoords(60,440);
        _downJaw.getToothList().get(4).setCoords(70,480);
        _downJaw.getToothList().get(5).setCoords(90,515);
        _downJaw.getToothList().get(6).setCoords(115,540);
        _downJaw.getToothList().get(7).setCoords(155,550);

        _downJaw.getToothList().get(8).setCoords(310,320);
        _downJaw.getToothList().get(9).setCoords(310,360);
        _downJaw.getToothList().get(10).setCoords(308,400);
        _downJaw.getToothList().get(11).setCoords(299,440);
        _downJaw.getToothList().get(12).setCoords(290,480);
        _downJaw.getToothList().get(13).setCoords(270,515);
        _downJaw.getToothList().get(14).setCoords(247,540);
        _downJaw.getToothList().get(15).setCoords(205,550);


        _upperJaw.getToothList().get(0).setCoords(48,260);
        _upperJaw.getToothList().get(1).setCoords(48,220);
        _upperJaw.getToothList().get(2).setCoords(52,175);
        _upperJaw.getToothList().get(3).setCoords(60,130);
        _upperJaw.getToothList().get(4).setCoords(70,88);
        _upperJaw.getToothList().get(5).setCoords(90,50);
        _upperJaw.getToothList().get(6).setCoords(115,28);
        _upperJaw.getToothList().get(7).setCoords(155,20);

        _upperJaw.getToothList().get(8).setCoords(310,260);
        _upperJaw.getToothList().get(9).setCoords(310,220);
        _upperJaw.getToothList().get(10).setCoords(308,175);
        _upperJaw.getToothList().get(11).setCoords(299,130);
        _upperJaw.getToothList().get(12).setCoords(290,88);
        _upperJaw.getToothList().get(13).setCoords(270,50);
        _upperJaw.getToothList().get(14).setCoords(247,28);
        _upperJaw.getToothList().get(15).setCoords(205,20);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.CYAN);

        g2.drawImage(finalUpperJaw,50,30,null);

        g2.drawImage(finalDownJaw,50,320,null);

        for(int i = 0; i<_downJaw.getToothList().size() ;i++) {
            if (_downJaw.getToothList().get(i).get_type().equals("jedynka") || _downJaw.getToothList().get(i).get_type().equals("dwójka")) {
                g2.drawImage(finalToothThreeState1, (int) _downJaw.getToothList().get(i).body.getX(), (int) _downJaw.getToothList().get(i).body.getY(), null);
            } else if (_downJaw.getToothList().get(i).get_type().equals("trójka")) {
                if (_downJaw.getToothList().get(i).getSide().equals("Lewa")) {
                    g2.drawImage(finalToothThreeState2LEFT, (int) _downJaw.getToothList().get(i).body.getX(), (int) _downJaw.getToothList().get(i).body.getY(), null);
                } else if (_downJaw.getToothList().get(i).getSide().equals("Prawa")) {
                    g2.drawImage(finalToothThreeState2, (int) _downJaw.getToothList().get(i).body.getX(), (int) _downJaw.getToothList().get(i).body.getY(), null);
                }
            } else if (_downJaw.getToothList().get(i).get_type().equals("czwórka") || _downJaw.getToothList().get(i).get_type().equals("piątka")) {
                g2.drawImage(finalToothTwo, (int) _downJaw.getToothList().get(i).body.getX(), (int) _downJaw.getToothList().get(i).body.getY(), null);
            } else if (_downJaw.getToothList().get(i).get_type().equals("szóstka") || _downJaw.getToothList().get(i).get_type().equals("siódemka") || _downJaw.getToothList().get(i).get_type().equals("ósemka")) {
                g2.drawImage(finalTooth, (int) _downJaw.getToothList().get(i).body.getX(), (int) _downJaw.getToothList().get(i).body.getY(), null);
            }

        }
            for(int i = 0; i<_upperJaw.getToothList().size() ;i++){
                if (_upperJaw.getToothList().get(i).get_type().equals("jedynka") || _upperJaw.getToothList().get(i).get_type().equals("dwójka")) {
                    g2.drawImage(finalToothThreeState11, (int) _upperJaw.getToothList().get(i).body.getX(), (int) _upperJaw.getToothList().get(i).body.getY(), null);
                } else if (_upperJaw.getToothList().get(i).get_type().equals("trójka")) {
                    if (_upperJaw.getToothList().get(i).getSide().equals("Lewa")) {
                        g2.drawImage(finalToothThreeState22LEFT, (int) _upperJaw.getToothList().get(i).body.getX(), (int) _upperJaw.getToothList().get(i).body.getY(), null);
                    } else if (_upperJaw.getToothList().get(i).getSide().equals("Prawa")) {
                        g2.drawImage(finalToothThreeState22, (int) _upperJaw.getToothList().get(i).body.getX(), (int) _upperJaw.getToothList().get(i).body.getY(), null);
                    }
                } else if (_upperJaw.getToothList().get(i).get_type().equals("czwórka") || _upperJaw.getToothList().get(i).get_type().equals("piątka")) {
                    g2.drawImage(finalToothTwo, (int) _upperJaw.getToothList().get(i).body.getX(), (int) _upperJaw.getToothList().get(i).body.getY(), null);
                } else if (_upperJaw.getToothList().get(i).get_type().equals("szóstka") || _upperJaw.getToothList().get(i).get_type().equals("siódemka") || _upperJaw.getToothList().get(i).get_type().equals("ósemka")) {
                    g2.drawImage(finalTooth, (int) _upperJaw.getToothList().get(i).body.getX(), (int) _upperJaw.getToothList().get(i).body.getY(), null);
                }
        }
    }



    public void cure(){

    }

    public void extractTooth(int t, int jaw){
        if(jaw==0){
            _downJaw.getToothList().get(t).set_isExtracted();
        }else{
            _upperJaw.getToothList().get(t).set_isExtracted();
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getY()<310){
            for(int i=0; i<_upperJaw.getToothList().size();i++){
                if(_upperJaw.getToothList().get(i).body.contains(e.getPoint())){
                    System.out.println(_upperJaw.getToothList().get(i).getSide() + " " + _upperJaw.getToothList().get(i).get_type());
                }
            }
        }else{
            for(int i=0; i<_downJaw.getToothList().size();i++){
                if(_downJaw.getToothList().get(i).body.contains(e.getPoint())){
                    System.out.println(_downJaw.getToothList().get(i).getSide() + " " + _downJaw.getToothList().get(i).get_type());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
