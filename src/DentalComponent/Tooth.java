package DentalComponent;

import java.util.List;

public class Tooth {

    private boolean _isSick;
    private boolean _isExtracted;
    private int _side;
    private String _type;

    Tooth(int type, int side){

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

    public boolean isSick() {
        return _isSick;
    }
}
