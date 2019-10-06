package DentalComponent;

import java.io.Serializable;
import java.util.Hashtable;

public class Jaw implements Serializable {

    private Hashtable<Integer, Tooth> toothList;

    public Hashtable<Integer, Tooth> getToothList() {
        return toothList;
    }

    public void setToothList(Hashtable<Integer, Tooth> toothList) {
        this.toothList = toothList;
    }

    Jaw(){
        toothList = new Hashtable<>();

        int counter = 0;
        for(int i=7; i>=0;i--){
            toothList.put(counter,new Tooth(i,0));
            toothList.get(counter).setCoords(48,260);
            counter++;
        }

        for(int i = 7; i>=0; i--){
            toothList.put(counter, new Tooth(i,1));
            toothList.get(counter).setCoords(48,260);
            counter++;
        }
    }
}
