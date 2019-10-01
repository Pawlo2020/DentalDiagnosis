package DentalComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class DentalDiagnoser implements Serializable {

    private ArrayList<String> _names;
    private Hashtable<Integer, Tooth> _upperJaw;

    private Hashtable<Integer, Tooth> _downJaw;

    public DentalDiagnoser(){
        generateJaws();

        _names = new ArrayList<>();
        _names.add("jedynka");
        _names.add("dwójka");
        _names.add("trójka");
        _names.add("czwórka");
        _names.add("piątka");
        _names.add("szóstka");
        _names.add("siódemka");
        _names.add("ósemka");
    }

    private void generateJaws() {
        int counter = 0;
        for(int i=7; i>=0;i--){
            _downJaw.put(counter,new Tooth(i,0));
            counter++;
        }

        for(int i = 0; i<=7; i++){
            _downJaw.put(counter, new Tooth(i,1));
            counter++;
        }
        _upperJaw = (Hashtable<Integer, Tooth>)_downJaw.clone();
    }

    public Tooth getTooth(int i, int jaw){
        if(jaw==0) {
            return _downJaw.get(i);
        }else {
            return _upperJaw.get(i);
        }
    }
}
