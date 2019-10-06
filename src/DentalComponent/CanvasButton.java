package DentalComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CanvasButton extends Rectangle2D.Double {

    IDentalable aaa;

    public Shape getButShape() {
        return butShape;
    }

    public void setButShape(Shape butShape) {
        this.butShape = butShape;
    }

    private Shape butShape;

    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        this._text = _text;
    }

    private String _text;

    CanvasButton(int x, int y, String text){
        _text = text;
        butShape = new Rectangle2D.Double(x,y,100,30);
    }

    public void setBehavior(IDentalable aaa){
        this.aaa = aaa;
    }

    public void Behavior(){
        aaa.onToothSelected();
    }
}
