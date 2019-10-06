package DentalComponent;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.List;

public class Tooth {

    private boolean _isSick;
    private boolean _isExtracted;
    private int _side;



    Rectangle2D body;

    public String get_type() {
        return _type;
    }

    private String _type;





    Tooth(int type, int side){

        body = new Rectangle2D.Double();
        _side = side;

        switch (type){
            case 0:
                _type = "jedynka";
                break;

            case 1:
                _type = "dwójka";
                break;

            case 2:
                _type = "trójka";
                break;

            case 3:
                _type = "czwórka";
                break;

            case 4:
                _type = "piątka";
                break;

            case 5:
                _type = "szóstka";
                break;

            case 6:
                _type = "siódemka";
                break;

            case 7:
                _type = "ósemka";
                break;
        }
    }


    public Rectangle2D getBody() {
        return body;
    }

    public void setCoords(double x, double y) {
        this.body.setRect(x,y,48,48);
    }




    public String getSide(){
        if(_side==1){
            return "Lewa";
        }else{
            return "Prawa";
        }
    }

    public boolean isExtracted() {
        return _isExtracted;
    }

    public void set_isExtracted(){
        _isExtracted = !isExtracted();

    }

    public void set_isSick(){
        _isSick = true;
    }
    public void set_isCured(){
        _isSick = false;
    }

    public boolean isSick() {
        return _isSick;
    }
}
