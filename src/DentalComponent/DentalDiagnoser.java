package DentalComponent;

import com.sun.corba.se.spi.ior.Identifiable;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.util.*;
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
import java.util.List;


public class DentalDiagnoser extends JComponent implements MouseListener {
    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);

    List<Patient> _patients;
    int _selectedPatient;


    private Jaw _upperJaw;
    private Jaw _downJaw;
    private Image finalUpperJaw;
    private Image finalDownJaw;

    private Tooth currentTooth;
    private List<CanvasButton> buttonsList;

    private TextureManager manager;

    public DentalDiagnoser(){
        try{
            manager = new TextureManager();
        }catch(Exception e){

        }

        _patients = new ArrayList<>();

        for(int i = 0; i<3; i++){
            _patients.add(new Patient());
        }

        _selectedPatient = 0;

        buttonsList = new ArrayList<>();
        CanvasButton but1 = new CanvasButton(450,500,"Extract button");
        but1.setBehavior(() -> {
            currentTooth.set_isExtracted();

            if(!currentTooth.isExtracted()){
                currentTooth.set_isSick();
            }
            repaint();
        });

        CanvasButton but2 = new CanvasButton(550,500,"Mark as infected");
        but2.setBehavior(() -> {
            currentTooth.set_isSick();
            repaint();
        });


        buttonsList.add(but1);
        buttonsList.add(but2);
        _downJaw = new Jaw();
        _upperJaw = new Jaw();

        addMouseListener(this);

        generateCoordinates();

        try {
            finalUpperJaw = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/upperJaw.png")).getScaledInstance(300,270,Image.SCALE_DEFAULT);
            finalDownJaw = ImageIO.read(new File("D:/Projekty/Edukacja/DentalDiagnosis/out/production/DentalDiagnosis/images/downJaw.png")).getScaledInstance(300,270,Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    private void generateCoordinates() {
        _patients.get(_selectedPatient)._downJaw.getToothList().get(0).setCoords(48,320);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(1).setCoords(48,360);
        _patients.get(_selectedPatient). _downJaw.getToothList().get(2).setCoords(52,400);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(3).setCoords(60,440);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(4).setCoords(70,480);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(5).setCoords(90,515);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(6).setCoords(115,540);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(7).setCoords(155,550);

        _patients.get(_selectedPatient)._downJaw.getToothList().get(8).setCoords(310,320);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(9).setCoords(310,360);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(10).setCoords(308,400);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(11).setCoords(299,440);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(12).setCoords(290,480);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(13).setCoords(270,515);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(14).setCoords(247,540);
        _patients.get(_selectedPatient)._downJaw.getToothList().get(15).setCoords(205,550);


        _patients.get(_selectedPatient)._upperJaw.getToothList().get(0).setCoords(48,260);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(1).setCoords(48,220);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(2).setCoords(52,175);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(3).setCoords(60,130);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(4).setCoords(70,88);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(5).setCoords(90,50);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(6).setCoords(115,28);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(7).setCoords(155,20);

        _patients.get(_selectedPatient)._upperJaw.getToothList().get(8).setCoords(310,260);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(9).setCoords(310,220);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(10).setCoords(308,175);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(11).setCoords(299,130);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(12).setCoords(290,88);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(13).setCoords(270,50);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(14).setCoords(247,28);
        _patients.get(_selectedPatient)._upperJaw.getToothList().get(15).setCoords(205,20);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.CYAN);

        g2.drawImage(finalUpperJaw,50,30,null);

        g2.drawImage(finalDownJaw,50,320,null);

        for(int i = 0; i<_patients.get(_selectedPatient)._downJaw.getToothList().size() ;i++) {
            if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).isExtracted() == true) {
                continue;
            }
            if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).get_type().equals("jedynka") || _patients.get(_selectedPatient)._downJaw.getToothList().get(i).get_type().equals("dwójka")) {
                if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).isSick()) {
                    g2.drawImage(manager.getToothsImages().get("tThreeState1INF.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);
                } else {
                    g2.drawImage(manager.getToothsImages().get("tThreeState1.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);

                }
            } else if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).get_type().equals("trójka")) {
                if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).getSide().equals("Lewa")) {
                    if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tThreeState2LEFTINF.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);
                    } else {
                        g2.drawImage(manager.getToothsImages().get("tThreeState2LEFT.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);
                    }
                } else if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).getSide().equals("Prawa")) {
                    if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tThreeState2INF.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);
                    } else {
                        g2.drawImage(manager.getToothsImages().get("tThreeState2.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);

                    }
                }
            } else if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).get_type().equals("czwórka") || _patients.get(_selectedPatient)._downJaw.getToothList().get(i).get_type().equals("piątka")) {
                if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).isSick()) {
                    g2.drawImage(manager.getToothsImages().get("tTwoINF.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);
                } else {
                    g2.drawImage(manager.getToothsImages().get("tTwo.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);

                }
            } else if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).get_type().equals("szóstka") || _patients.get(_selectedPatient)._downJaw.getToothList().get(i).get_type().equals("siódemka") || _patients.get(_selectedPatient)._downJaw.getToothList().get(i).get_type().equals("ósemka")) {
                if (_patients.get(_selectedPatient)._downJaw.getToothList().get(i).isSick()) {
                    g2.drawImage(manager.getToothsImages().get("tOneINF.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);

                } else {
                    g2.drawImage(manager.getToothsImages().get("tOne.png"), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._downJaw.getToothList().get(i).body.getY(), null);

                }
            }
        }
            for(int i = 0; i<_upperJaw.getToothList().size() ;i++){
                if(_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).isExtracted()==true){
                    continue;
                }
                if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).get_type().equals("jedynka") || _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).get_type().equals("dwójka")) {
                    if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tThreeState11INF.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);
                    }else{
                        g2.drawImage(manager.getToothsImages().get("tThreeState11.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);

                    }
                } else if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).get_type().equals("trójka")) {
                    if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).getSide().equals("Lewa")) {
                        if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).isSick()) {
                            g2.drawImage(manager.getToothsImages().get("tThreeState22LEFTINF.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);
                        }else{
                            g2.drawImage(manager.getToothsImages().get("tThreeState22LEFT.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);

                        }
                    } else if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).getSide().equals("Prawa")) {
                        if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).isSick()) {
                            g2.drawImage(manager.getToothsImages().get("tThreeState22INF.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);
                        }else{
                            g2.drawImage(manager.getToothsImages().get("tThreeState22.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);

                        }
                    }
                } else if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).get_type().equals("czwórka") || _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).get_type().equals("piątka")) {
                    if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tTwoINF.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);
                    }else{
                        g2.drawImage(manager.getToothsImages().get("tTwo.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);

                    }
                } else if (_patients.get(_selectedPatient)._upperJaw.getToothList().get(i).get_type().equals("szóstka") || _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).get_type().equals("siódemka") || _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).get_type().equals("ósemka")) {
                    if (_upperJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tOneINF.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);
                    }else{
                        g2.drawImage(manager.getToothsImages().get("tOne.png"), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getX(), (int) _patients.get(_selectedPatient)._upperJaw.getToothList().get(i).body.getY(), null);

                    }
                }
        }

        Rectangle2D rect = new Rectangle2D.Double(400,50,300,500);
            g2.draw(rect);
            g2.setColor(Color.cyan);
            g2.fill(rect);
            g2.setColor(Color.DARK_GRAY);
            for(int i=0;i<buttonsList.size();i++){
                g2.setColor(Color.orange);
                g2.fill(buttonsList.get(i).getButShape());
                g2.setColor(Color.black);
                g2.draw(buttonsList.get(i).getButShape());
                g2.setColor(Color.black);
                g2.drawString(buttonsList.get(i).get_text(),(int)buttonsList.get(i).getButShape().getBounds2D().getX()+10,(int)buttonsList.get(i).getButShape().getBounds2D().getY()+20);

            }
            if(currentTooth != null) {
                g2.drawString(currentTooth.getSide() + " " + currentTooth.get_type(), 450, 80);

                if(currentTooth.isSick()==false){
                    g2.drawString("Status: not infected",450,150);
                }else{
                    g2.drawString("Status: infected",450,150);
                }

                if(currentTooth.isExtracted()){
                    g2.drawString("Extraction status: true",450,200);

                }else{
                    g2.drawString("Extraction status: false",450,200);
                }

            }
    }

    public void extractTooth(int t, int jaw){
        if(jaw==0){
            _downJaw.getToothList().get(t).set_isExtracted();
        }else{
            _upperJaw.getToothList().get(t).set_isExtracted();
        }
    }

    private List<IDentalable> listeners = new ArrayList<IDentalable>();

    public void addListener(IDentalable toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int a=0;a<buttonsList.size();a++){
            if(buttonsList.get(a).getButShape().contains(e.getPoint())){
                System.out.println("SYSTEM");
                buttonsList.get(a).Behavior();


            }


        }



        if(e.getY()<310){
            for(int i=0; i<_upperJaw.getToothList().size();i++){
                if(_upperJaw.getToothList().get(i).body.contains(e.getPoint())){
                    System.out.println(_upperJaw.getToothList().get(i).getSide() + " " + _upperJaw.getToothList().get(i).get_type());
                    currentTooth = _upperJaw.getToothList().get(i);
                    this.repaint();

                }
            }
        }else{
            for(int i=0; i<_downJaw.getToothList().size();i++){
                if(_downJaw.getToothList().get(i).body.contains(e.getPoint())){
                    System.out.println(_downJaw.getToothList().get(i).getSide() + " " + _downJaw.getToothList().get(i).get_type());
                    currentTooth = _downJaw.getToothList().get(i);
                    this.repaint();

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
