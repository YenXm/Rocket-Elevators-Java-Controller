package defaultopackage;

public class FloorRequestButton {
    public int ID;
    public String status;
    public int floor;
    public String direction;

    public FloorRequestButton(int _ID, String _status, int _floor, String _direction) {
        this.ID = _ID;
        this.status = _status;
        this.floor = _floor;
        this.direction = _direction;
    }
}