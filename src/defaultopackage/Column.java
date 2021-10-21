package defaultopackage;

import java.util.ArrayList;
import java.lang.Math;

public class Column {

    int callButtonID = 1;
    int elevatorID = 1;

    public String ID;
    public String status;
    public int amountOfFloors;
    public int amountfOfElevators;
    public ArrayList<Elevator> elevatorsArrayList;
    public ArrayList<CallButton> callButtonsArrayList;
    public ArrayList<Integer> servedFloorsArrayList;
    public boolean isBasement;

    public Column(String _ID, String _status, int _amountOfFloors, int _amountOfElevators,
            ArrayList<Integer> _servedFloors, boolean _isBasement) {
        this.ID = _ID;
        this.status = _status;
        this.amountfOfElevators = _amountOfElevators;
        this.servedFloorsArrayList = _servedFloors;
        this.isBasement = _isBasement;
        this.elevatorsArrayList = new ArrayList<Elevator>();
        this.callButtonsArrayList = new ArrayList<CallButton>();
        this.servedFloorsArrayList = _servedFloors;

        createElevators(_amountOfFloors, _amountOfElevators);
        createCallButton(_amountOfFloors, _isBasement);
    }

    // Simulate when a user press a button on a floor to go back to the first floor
    public Elevator requestElevator(int userPosition, String direction) {
        Elevator elevator = findElevator(userPosition, direction);
        elevator.addNewRequest(userPosition);
        elevator.move();

        elevator.addNewRequest(1);
        elevator.move();
        return elevator;
    }

    public void createCallButton(int _amountOfFloors, boolean _isBasement) {
        if (_isBasement) {
            int buttonFloor = -1;
            for (int i = 0; i < _amountOfFloors; i++) {
                CallButton callbutton = new CallButton(callButtonID, "Off", buttonFloor, "up");
                this.callButtonsArrayList.add(callbutton);
                buttonFloor--;
                callButtonID++;
            }
        } else {
            int buttonFloor = 1;
            for (int i = 0; i < _amountOfFloors; i++) {
                CallButton callbutton = new CallButton(callButtonID, "Off", buttonFloor, "down");
                this.callButtonsArrayList.add(callbutton);
                buttonFloor++;
                callButtonID++;
            }
        }
    }

    public void createElevators(int _amountOfFloors, int _amountOfElevators) {
        for (int i = 0; i < _amountOfElevators; i++) {
            Elevator elevator = new Elevator(String.valueOf(elevatorID), "idle", _amountOfFloors, 1);
            this.elevatorsArrayList.add(elevator);
            elevatorID++;

        }
    }

    class bestElevatorInformations {
        Elevator bestElevator;
        int bestScore;
        int referenceGap;

        public bestElevatorInformations(Elevator _bestElevator, int _bestScore, int _referenceGap) {
            bestElevator = _bestElevator;
            bestScore = _bestScore;
            referenceGap = _referenceGap;
        }
    }

    public bestElevatorInformations checkIfElevatorIsBetter(int scoreToCheck, Elevator newElevator, int bestScore,
            int referenceGap, Elevator bestElevator, int floor) {
        if (scoreToCheck < bestScore) {
            bestScore = scoreToCheck;
            bestElevator = newElevator;
            referenceGap = Math.abs(newElevator.currentFloor - floor);
        } else if (bestScore == scoreToCheck) {
            int gap = Math.abs(newElevator.currentFloor - floor);
            if (referenceGap > gap) {
                bestElevator = newElevator;
                referenceGap = gap;
            }
        }
        bestElevatorInformations newBEI = new bestElevatorInformations(bestElevator, bestScore, referenceGap);
        return newBEI;
    }

    public Elevator findElevator(int _requestedFloor, String _requestedDirection) {
        Elevator bestElevator = null;
        int bestScore = 6;
        int referenceGap = 1000000;
        bestElevatorInformations BEI;

        if (_requestedFloor == 1) {
            for (Elevator elevator : this.elevatorsArrayList) {
                if (elevator.currentFloor == 1 && elevator.status == "stopped") {
                    BEI = checkIfElevatorIsBetter(1, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                } else if (elevator.currentFloor == 1 && elevator.status == "idle") {
                    BEI = checkIfElevatorIsBetter(2, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                } else if (elevator.currentFloor < 1 && elevator.direction == "up") {
                    BEI = checkIfElevatorIsBetter(3, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                } else if (elevator.currentFloor > 1 && elevator.direction == "down") {
                    BEI = checkIfElevatorIsBetter(3, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                } else if (elevator.status == "idle") {
                    BEI = checkIfElevatorIsBetter(4, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                } else {
                    BEI = checkIfElevatorIsBetter(5, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                }
                bestElevator = BEI.bestElevator;
                bestScore = BEI.bestScore;
                referenceGap = BEI.referenceGap;
            }
        } else {
            for (Elevator elevator : this.elevatorsArrayList) {
                if (_requestedFloor == elevator.currentFloor && elevator.status == "stopped"
                        && _requestedDirection == elevator.direction) {
                    BEI = checkIfElevatorIsBetter(1, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                } else if (_requestedFloor > elevator.currentFloor && elevator.direction == "up"
                        && _requestedDirection == "up") {
                    BEI = checkIfElevatorIsBetter(2, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                } else if (_requestedFloor < elevator.currentFloor && elevator.direction == "down"
                        && _requestedDirection == "down") {
                    BEI = checkIfElevatorIsBetter(2, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                } else if (elevator.status == "idle") {
                    BEI = checkIfElevatorIsBetter(4, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                } else {
                    BEI = checkIfElevatorIsBetter(5, elevator, bestScore, referenceGap, bestElevator, _requestedFloor);
                }
                bestElevator = BEI.bestElevator;
                bestScore = BEI.bestScore;
                referenceGap = BEI.referenceGap;
            }
        }
        return bestElevator;
    }
}