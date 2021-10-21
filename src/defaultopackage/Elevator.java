package defaultopackage;

import java.util.ArrayList;
import java.util.Collections;

public class Elevator {
    public String ID;
    public String status;
    public int amountOfFloors;
    public int currentFloor;
    public Door door;
    public ArrayList<Integer> floorRequestsList;
    public ArrayList<Integer> completedRequestsList;
    public String direction;
    public boolean overweight;
    public boolean obstruction;

    public Elevator(String _ID, String _status, int _amountOfFloors, int _currentFloor) {
        this.ID = _ID;
        this.status = _status;
        this.amountOfFloors = _amountOfFloors;
        this.currentFloor = _currentFloor;
        this.door = new Door(Integer.parseInt(ID), "closed");
        this.floorRequestsList = new ArrayList<Integer>();
        this.completedRequestsList = new ArrayList<Integer>();
        this.direction = "";
        this.overweight = false;
        this.obstruction = false;
    }

    public void move() {
        while (this.floorRequestsList.size() != 0) {
            int destination = this.floorRequestsList.get(0);
            this.status = "moving";
            if (this.currentFloor < destination) {
                this.direction = "up";
                sortFloorList();
                while (this.currentFloor < destination) {
                    this.currentFloor++;
                    // SET THIS screenDisplay TO THIS currentFloor
                }
            } else if (this.currentFloor > destination) {
                this.direction = "down";
                while (this.currentFloor > destination) {
                    this.currentFloor--;
                    // SET THIS screenDisplay TO THIS currentFloor
                }
            }
            this.status = "stopped";
            operateDoor();
            this.completedRequestsList.add(this.floorRequestsList.get(0));
            this.floorRequestsList.remove(0);
        }
        this.status = "idle";
    }

    public void sortFloorList() {
        if (this.direction == "up") {
            Collections.sort(this.floorRequestsList);
        } else {
            Collections.sort(this.floorRequestsList, Collections.reverseOrder());
        }
    }

    public void operateDoor() {
        this.door.status = "opened";
        if (this.overweight != true) {
            this.door.status = "closing";
            if (this.obstruction != true) {
                this.door.status = "closed";
            } else {
                operateDoor();
            }
        } else {
            while (overweight) {
                // activate overweight Alarm
                this.overweight = false;
            }
            operateDoor();
        }
    }

    public void addNewRequest(int _requestedFloor) {
        if (this.floorRequestsList.contains(_requestedFloor) != true) {
            this.floorRequestsList.add(_requestedFloor);
        }

        if (this.currentFloor < _requestedFloor) {
            this.direction = "Up";
        }

        if (this.currentFloor > _requestedFloor) {
            this.direction = "Down";
        }
    }
}
