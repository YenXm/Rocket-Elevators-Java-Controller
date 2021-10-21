package defaultopackage;

import java.util.ArrayList;
import java.lang.Math;

public class Battery {
    int columnID = 1;
    int floorRequestButtonID = 1;

    public int ID;
    public String status;
    public ArrayList<Column> columnsArrayList;
    public ArrayList<FloorRequestButton> floorRequestsButtonsArrayList;

    public Battery(int _ID, int _amountOfColumns, int _amountOfFloors, int _amountOfBasements,
            int _amountOfElevatorPerColumn) {
        this.ID = _ID;
        this.status = "idle";
        this.columnsArrayList = new ArrayList<Column>();
        this.floorRequestsButtonsArrayList = new ArrayList<FloorRequestButton>();

        if (_amountOfBasements > 0) {
            createBasementFloorRequestButtons(_amountOfBasements);
            createBasementColumn(_amountOfBasements, _amountOfElevatorPerColumn);
            _amountOfColumns--;
        }
        createFloorRequestButtons(_amountOfFloors);
        createColumns(_amountOfColumns, _amountOfFloors, _amountOfElevatorPerColumn);
    }

    public void createBasementColumn(int _amountOfBasements, int _amountOfElevatorPerColumn) {
        ArrayList<Integer> servedFloors = new ArrayList<Integer>();
        int floor = -1;
        for (int i = 0; i < _amountOfBasements; i++) {
            servedFloors.add(floor);

            floor -= 1;
        }

        Column column = new Column(String.valueOf(columnID), "online", _amountOfBasements, _amountOfElevatorPerColumn,
                servedFloors, true);
        this.columnsArrayList.add(column);
        columnID++;
    }

    public void createColumns(int _amountOfColumns, int _amountOfFloors, int _amountOfElevatorPerColumn)

    {
        int amountOfFloorPerColumn = (int) Math.ceil((double) (_amountOfFloors) / (double) (_amountOfColumns));
        int floor = 1;

        for (int i = 0; i < _amountOfColumns; i++) {
            ArrayList<Integer> servedFloors = new ArrayList<Integer>();
            for (int y = 0; y < amountOfFloorPerColumn; y++) {
                if (floor <= _amountOfFloors) {
                    servedFloors.add(floor);

                    floor++;
                }
            }
            Column column = new Column(String.valueOf(columnID), "online", _amountOfFloors, _amountOfElevatorPerColumn,
                    servedFloors, false);
            this.columnsArrayList.add(column);
            columnID++;
        }
    }

    public void createFloorRequestButtons(int _amountOfFloors) {
        int buttonFloor = 1;
        for (int i = 0; i < _amountOfFloors; i++) {
            FloorRequestButton floorRequestButton = new FloorRequestButton(floorRequestButtonID, "Off", buttonFloor,
                    "Up");
            this.floorRequestsButtonsArrayList.add(floorRequestButton);
            buttonFloor++;
            floorRequestButtonID++;
        }
    }

    public void createBasementFloorRequestButtons(int _amountOfBasements) {
        int buttonFloor = -1;
        for (int i = 0; i < _amountOfBasements; i++) {
            FloorRequestButton floorRequestButton = new FloorRequestButton(floorRequestButtonID, "Off", buttonFloor,
                    "Down");
            this.floorRequestsButtonsArrayList.add(floorRequestButton);
            buttonFloor--;
            floorRequestButtonID++;
        }
    }

    public Column findBestColumn(int _requestedFloor) {
        for (Column column : this.columnsArrayList) {
            if (column.servedFloorsArrayList.contains(_requestedFloor)) {
                return column;
            }
        }
        return this.columnsArrayList.get(0);
    }

    public Results assignElevator(int _requestFloor, String _direction) {
        Column column = findBestColumn(_requestFloor);
        Elevator elevator = column.findElevator(1, _direction);
        elevator.addNewRequest(1);
        elevator.move();
        elevator.addNewRequest(_requestFloor);
        elevator.move();

        Results chosenReturn = new Results(column, elevator);
        return chosenReturn;
    }
}