package DentalComponent;

public class Patient {
    public Jaw get_upperJaw() {
        return _upperJaw;
    }

    public void set_upperJaw(Jaw _upperJaw) {
        this._upperJaw = _upperJaw;
    }

    public Jaw _upperJaw;

    public Jaw get_downJaw() {
        return _downJaw;
    }

    public void set_downJaw(Jaw _downJaw) {
        this._downJaw = _downJaw;
    }

    public Jaw _downJaw;

    public Patient(){
        _downJaw = new Jaw();
        _upperJaw = new Jaw();
    }
}
