package DentalComponent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class TextureManager {
    public Hashtable<String, Image> getToothsImages() {
        return ToothsImages;
    }

    Hashtable<String, Image> ToothsImages;

    public TextureManager() throws IOException {
        ToothsImages = new Hashtable<>();

        File directory = new File("D:\\OneDrive\\Studia\\Semestr 5\\Programowanie komponentowe\\DentalDiagnosis\\out\\production\\DentalDiagnosis\\images\\teeth");

        for(File aa:directory.listFiles()){
            System.out.println(aa.getAbsolutePath());
            ToothsImages.put(aa.getName(),ImageIO.read(aa).getScaledInstance(40,40,Image.SCALE_DEFAULT));
        }
    }
}
