package DentalComponent;

import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class DentalDiagnoser extends JComponent implements MouseListener {
    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
//LEGACY CONFIRMED
//    List<Patient> _patients;
//    int _selectedPatient;

    Patient curPatient;
    int curID;

    private Jaw _upperJaw;
    private Jaw _downJaw;
    private Image finalUpperJaw;
    private Image finalDownJaw;

    private Tooth currentTooth;
    private List<CanvasButton> buttonsList;

    private TextureManager manager;

    public void setPatient(Patient pat){
        curPatient = pat;
        generateCoordinates();
        repaint();
    }

    public void saveState(int id){
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("Patient" + id + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(curPatient);

            out.close();
            file.close();
        }

        catch(IOException ex) {}
    }

    public void loadState(int id){
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("Patient" + id + ".ser");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            curPatient = (Patient) in.readObject();

            in.close();
            file.close();

            curID = id;
            repaint();
        }

        catch(IOException | ClassNotFoundException ex)
        {}
    }

    public DentalDiagnoser(){
        try{
            manager = new TextureManager();
        }catch(Exception e){}

        curPatient = new Patient();
//legacy
//        for(int i = 0; i<3; i++){
//            _patients.add(new Patient());
//        }

        buttonsList = new ArrayList<>();
        CanvasButton but1 = new CanvasButton(450,500,"Extract tooth");
        but1.setBehavior(() -> {
            currentTooth.set_isExtracted();

            if(!currentTooth.isExtracted()){
                currentTooth.set_isSick(true);
            }
            repaint();
        });

        CanvasButton but2 = new CanvasButton(550,500,"Mark as infected");
        but2.setBehavior(() -> {
            currentTooth.set_isSick(false);
            repaint();
        });

        buttonsList.add(but1);
        buttonsList.add(but2);
        _downJaw = new Jaw();
        _upperJaw = new Jaw();

        addMouseListener(this);

        generateCoordinates();

        try {
            finalUpperJaw = ImageIO.read(new File("D:\\OneDrive\\Studia\\Semestr 5\\Programowanie komponentowe\\DentalDiagnosis\\out\\production\\DentalDiagnosis\\images\\upperJaw.png")).getScaledInstance(300,270,Image.SCALE_DEFAULT);
            finalDownJaw = ImageIO.read(new File("D:\\OneDrive\\Studia\\Semestr 5\\Programowanie komponentowe\\DentalDiagnosis\\out\\production\\DentalDiagnosis\\images\\downJaw.png")).getScaledInstance(300,270,Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    private void generateCoordinates() {
        curPatient._downJaw.getToothList().get(0).setCoords(48,320);
        curPatient._downJaw.getToothList().get(1).setCoords(48,360);
        curPatient. _downJaw.getToothList().get(2).setCoords(52,400);
        curPatient._downJaw.getToothList().get(3).setCoords(60,440);
        curPatient._downJaw.getToothList().get(4).setCoords(70,480);
        curPatient._downJaw.getToothList().get(5).setCoords(90,515);
        curPatient._downJaw.getToothList().get(6).setCoords(115,540);
        curPatient._downJaw.getToothList().get(7).setCoords(155,550);

        curPatient._downJaw.getToothList().get(8).setCoords(310,320);
        curPatient._downJaw.getToothList().get(9).setCoords(310,360);
        curPatient._downJaw.getToothList().get(10).setCoords(308,400);
        curPatient._downJaw.getToothList().get(11).setCoords(299,440);
        curPatient._downJaw.getToothList().get(12).setCoords(290,480);
        curPatient._downJaw.getToothList().get(13).setCoords(270,515);
        curPatient._downJaw.getToothList().get(14).setCoords(247,540);
        curPatient._downJaw.getToothList().get(15).setCoords(205,550);


        curPatient._upperJaw.getToothList().get(0).setCoords(48,260);
        curPatient._upperJaw.getToothList().get(1).setCoords(48,220);
        curPatient._upperJaw.getToothList().get(2).setCoords(52,175);
        curPatient._upperJaw.getToothList().get(3).setCoords(60,130);
        curPatient._upperJaw.getToothList().get(4).setCoords(70,88);
        curPatient._upperJaw.getToothList().get(5).setCoords(90,50);
        curPatient._upperJaw.getToothList().get(6).setCoords(115,28);
        curPatient._upperJaw.getToothList().get(7).setCoords(155,20);

        curPatient._upperJaw.getToothList().get(8).setCoords(310,260);
        curPatient._upperJaw.getToothList().get(9).setCoords(310,220);
        curPatient._upperJaw.getToothList().get(10).setCoords(308,175);
        curPatient._upperJaw.getToothList().get(11).setCoords(299,130);
        curPatient._upperJaw.getToothList().get(12).setCoords(290,88);
        curPatient._upperJaw.getToothList().get(13).setCoords(270,50);
        curPatient._upperJaw.getToothList().get(14).setCoords(247,28);
        curPatient._upperJaw.getToothList().get(15).setCoords(205,20);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.CYAN);

        g2.drawImage(finalUpperJaw,50,30,null);

        g2.drawImage(finalDownJaw,50,320,null);

        for(int i = 0; i<curPatient._downJaw.getToothList().size() ;i++) {
            if (curPatient._downJaw.getToothList().get(i).isExtracted() == true) {
                continue;
            }
            if (curPatient._downJaw.getToothList().get(i).get_type().equals("jedynka") || curPatient._downJaw.getToothList().get(i).get_type().equals("dwójka")) {
                if (curPatient._downJaw.getToothList().get(i).isSick()) {
                    g2.drawImage(manager.getToothsImages().get("tThreeState1INF.png"), (int) curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);
                } else {
                    g2.drawImage(manager.getToothsImages().get("tThreeState1.png"), (int)curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);

                }
            } else if (curPatient._downJaw.getToothList().get(i).get_type().equals("trójka")) {
                if (curPatient._downJaw.getToothList().get(i).getSide().equals("Lewa")) {
                    if (curPatient._downJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tThreeState2INF.png"), (int) curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);
                    } else {
                        g2.drawImage(manager.getToothsImages().get("tThreeState2.png"), (int)curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);
                    }
                } else if (curPatient._downJaw.getToothList().get(i).getSide().equals("Prawa")) {
                    if (curPatient._downJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tThreeState2LEFTINF.png"), (int) curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);
                    } else {
                        g2.drawImage(manager.getToothsImages().get("tThreeState2LEFT.png"), (int) curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);

                    }
                }
            } else if (curPatient._downJaw.getToothList().get(i).get_type().equals("czwórka") || curPatient._downJaw.getToothList().get(i).get_type().equals("piątka")) {
                if (curPatient._downJaw.getToothList().get(i).isSick()) {
                    g2.drawImage(manager.getToothsImages().get("tTwoINF.png"), (int) curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);
                } else {
                    g2.drawImage(manager.getToothsImages().get("tTwo.png"), (int) curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);

                }
            } else if (curPatient._downJaw.getToothList().get(i).get_type().equals("szóstka") || curPatient._downJaw.getToothList().get(i).get_type().equals("siódemka") || curPatient._downJaw.getToothList().get(i).get_type().equals("ósemka")) {
                if (curPatient._downJaw.getToothList().get(i).isSick()) {
                    g2.drawImage(manager.getToothsImages().get("tOneINF.png"), (int) curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);

                } else {
                    g2.drawImage(manager.getToothsImages().get("tOne.png"), (int) curPatient._downJaw.getToothList().get(i).body.getX(), (int) curPatient._downJaw.getToothList().get(i).body.getY(), null);

                }
            }
        }
            for(int i = 0; i<_upperJaw.getToothList().size() ;i++){
                if(curPatient._upperJaw.getToothList().get(i).isExtracted()==true){
                    continue;
                }
                if (curPatient._upperJaw.getToothList().get(i).get_type().equals("jedynka") || curPatient._upperJaw.getToothList().get(i).get_type().equals("dwójka")) {
                    if (curPatient._upperJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tThreeState11INF.png"), (int) curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);
                    }else{
                        g2.drawImage(manager.getToothsImages().get("tThreeState11.png"), (int) curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);

                    }
                } else if (curPatient._upperJaw.getToothList().get(i).get_type().equals("trójka")) {
                    if (curPatient._upperJaw.getToothList().get(i).getSide().equals("Lewa")) {
                        if (curPatient._upperJaw.getToothList().get(i).isSick()) {
                            g2.drawImage(manager.getToothsImages().get("tThreeState22INF.png"), (int) curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);
                        }else{
                            g2.drawImage(manager.getToothsImages().get("tThreeState22.png"), (int) curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);

                        }
                    } else if (curPatient._upperJaw.getToothList().get(i).getSide().equals("Prawa")) {
                        if (curPatient._upperJaw.getToothList().get(i).isSick()) {
                            g2.drawImage(manager.getToothsImages().get("tThreeState22LEFTINF.png"), (int) curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);
                        }else{
                            g2.drawImage(manager.getToothsImages().get("tThreeState22LEFT.png"), (int)curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);

                        }
                    }
                } else if (curPatient._upperJaw.getToothList().get(i).get_type().equals("czwórka") || curPatient._upperJaw.getToothList().get(i).get_type().equals("piątka")) {
                    if (curPatient._upperJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tTwoINF.png"), (int) curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);
                    }else{
                        g2.drawImage(manager.getToothsImages().get("tTwo.png"), (int)curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);

                    }
                } else if (curPatient._upperJaw.getToothList().get(i).get_type().equals("szóstka") || curPatient._upperJaw.getToothList().get(i).get_type().equals("siódemka") || curPatient._upperJaw.getToothList().get(i).get_type().equals("ósemka")) {
                    if (curPatient._upperJaw.getToothList().get(i).isSick()) {
                        g2.drawImage(manager.getToothsImages().get("tOneINF.png"), (int) curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);
                    }else{
                        g2.drawImage(manager.getToothsImages().get("tOne.png"), (int) curPatient._upperJaw.getToothList().get(i).body.getX(), (int) curPatient._upperJaw.getToothList().get(i).body.getY(), null);
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

    public void updateButtons(Tooth currentTooth){
        if(currentTooth.isExtracted()){
            buttonsList.get(0).set_text("Insert tooth");
        }else{
            buttonsList.get(0).set_text("Extract tooth");
        }

        if(currentTooth.isSick()){
            buttonsList.get(1).set_text("Mark as cured");
        }else{
            buttonsList.get(1).set_text("Mark as infected");
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int a=0;a<buttonsList.size();a++){
            if(buttonsList.get(a).getButShape().contains(e.getPoint())){

                buttonsList.get(a).Behavior();
                updateButtons(currentTooth);
            }
        }

        if(e.getY()<310){
            for(int i=0; i<_upperJaw.getToothList().size();i++){
                if(curPatient._upperJaw.getToothList().get(i).body.contains(e.getPoint())){
                    currentTooth = curPatient._upperJaw.getToothList().get(i);
                    updateButtons(currentTooth);
                    this.repaint();
                }
            }
        }else{
            for(int i=0; i<_downJaw.getToothList().size();i++){
                if(curPatient._downJaw.getToothList().get(i).body.contains(e.getPoint())){
                    currentTooth = curPatient._downJaw.getToothList().get(i);
                    updateButtons(currentTooth);
                    this.repaint();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
