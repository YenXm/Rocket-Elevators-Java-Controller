package defaultopackage;

public class Scenarios {
    Battery battery = new Battery(1, 4, 60, 6, 5);

    public Column moveAllElevators(Column column) {
        for (int i = 0; i < column.elevatorsArrayList.size(); i++) {
            while (column.elevatorsArrayList.get(i).floorRequestsList.size() != 0) {
                column.elevatorsArrayList.get(i).move();
            }
        }
        return column;
    }

    public Results scenario1() {
        Column column = battery.columnsArrayList.get(1);

        column.elevatorsArrayList.get(0).currentFloor = 20;
        column.elevatorsArrayList.get(0).direction = "down";
        column.elevatorsArrayList.get(0).status = "moving";
        column.elevatorsArrayList.get(0).floorRequestsList.add(5);

        column.elevatorsArrayList.get(1).currentFloor = 3;
        column.elevatorsArrayList.get(1).direction = "up";
        column.elevatorsArrayList.get(1).status = "moving";
        column.elevatorsArrayList.get(1).floorRequestsList.add(15);

        column.elevatorsArrayList.get(2).currentFloor = 13;
        column.elevatorsArrayList.get(2).direction = "down";
        column.elevatorsArrayList.get(2).status = "moving";
        column.elevatorsArrayList.get(2).floorRequestsList.add(1);

        column.elevatorsArrayList.get(3).currentFloor = 15;
        column.elevatorsArrayList.get(3).direction = "down";
        column.elevatorsArrayList.get(3).status = "moving";
        column.elevatorsArrayList.get(3).floorRequestsList.add(2);

        column.elevatorsArrayList.get(4).currentFloor = 6;
        column.elevatorsArrayList.get(4).direction = "down";
        column.elevatorsArrayList.get(4).status = "moving";
        column.elevatorsArrayList.get(4).floorRequestsList.add(2);

        Results chosenReturn = battery.assignElevator(20, "up");
        chosenReturn.chosenColumn = moveAllElevators(chosenReturn.chosenColumn);
        return chosenReturn;
    }

    public Results scenario2() {
        Column column = battery.columnsArrayList.get(2);

        column.elevatorsArrayList.get(0).currentFloor = 1;
        column.elevatorsArrayList.get(0).direction = "up";
        column.elevatorsArrayList.get(0).status = "stopped";
        column.elevatorsArrayList.get(0).floorRequestsList.add(21);

        column.elevatorsArrayList.get(1).currentFloor = 23;
        column.elevatorsArrayList.get(1).direction = "up";
        column.elevatorsArrayList.get(1).status = "moving";
        column.elevatorsArrayList.get(1).floorRequestsList.add(28);

        column.elevatorsArrayList.get(2).currentFloor = 33;
        column.elevatorsArrayList.get(2).direction = "down";
        column.elevatorsArrayList.get(2).status = "moving";
        column.elevatorsArrayList.get(2).floorRequestsList.add(1);

        column.elevatorsArrayList.get(3).currentFloor = 40;
        column.elevatorsArrayList.get(3).direction = "down";
        column.elevatorsArrayList.get(3).status = "moving";
        column.elevatorsArrayList.get(3).floorRequestsList.add(24);

        column.elevatorsArrayList.get(4).currentFloor = 39;
        column.elevatorsArrayList.get(4).direction = "down";
        column.elevatorsArrayList.get(4).status = "moving";
        column.elevatorsArrayList.get(4).floorRequestsList.add(1);

        Results chosenReturn = battery.assignElevator(36, "up");
        chosenReturn.chosenColumn = moveAllElevators(chosenReturn.chosenColumn);
        return chosenReturn;
    }

    public Results scenario3() {
        Column column = battery.columnsArrayList.get(3);

        column.elevatorsArrayList.get(0).currentFloor = 58;
        column.elevatorsArrayList.get(0).direction = "down";
        column.elevatorsArrayList.get(0).status = "moving";
        column.elevatorsArrayList.get(0).floorRequestsList.add(1);

        column.elevatorsArrayList.get(1).currentFloor = 50;
        column.elevatorsArrayList.get(1).direction = "up";
        column.elevatorsArrayList.get(1).status = "moving";
        column.elevatorsArrayList.get(1).floorRequestsList.add(60);

        column.elevatorsArrayList.get(2).currentFloor = 46;
        column.elevatorsArrayList.get(2).direction = "up";
        column.elevatorsArrayList.get(2).status = "moving";
        column.elevatorsArrayList.get(2).floorRequestsList.add(58);

        column.elevatorsArrayList.get(3).currentFloor = 1;
        column.elevatorsArrayList.get(3).direction = "up";
        column.elevatorsArrayList.get(3).status = "moving";
        column.elevatorsArrayList.get(3).floorRequestsList.add(54);

        column.elevatorsArrayList.get(4).currentFloor = 60;
        column.elevatorsArrayList.get(4).direction = "down";
        column.elevatorsArrayList.get(4).status = "moving";
        column.elevatorsArrayList.get(4).floorRequestsList.add(1);

        // We make the request
        Elevator chosenElevator = column.requestElevator(54, "down");
        column = moveAllElevators(column);
        Results chosenReturn = new Results(column, chosenElevator);
        return chosenReturn;
    }

    public Results scenario4() {
        Column column = battery.columnsArrayList.get(0);

        column.elevatorsArrayList.get(0).currentFloor = -4;

        column.elevatorsArrayList.get(1).currentFloor = 1;

        column.elevatorsArrayList.get(2).currentFloor = -3;
        column.elevatorsArrayList.get(2).direction = "down";
        column.elevatorsArrayList.get(2).status = "moving";
        column.elevatorsArrayList.get(2).floorRequestsList.add(-5);

        column.elevatorsArrayList.get(3).currentFloor = -6;
        column.elevatorsArrayList.get(3).direction = "up";
        column.elevatorsArrayList.get(3).status = "moving";
        column.elevatorsArrayList.get(3).floorRequestsList.add(1);

        column.elevatorsArrayList.get(4).currentFloor = -1;
        column.elevatorsArrayList.get(4).direction = "down";
        column.elevatorsArrayList.get(4).status = "moving";
        column.elevatorsArrayList.get(4).floorRequestsList.add(-6);

        // We make the request
        Elevator chosenElevator = column.requestElevator(-3, "up");
        column = moveAllElevators(column);
        Results chosenReturn = new Results(column, chosenElevator);
        return chosenReturn;
    }

    public void run(int scenarioNumber) {
        switch (scenarioNumber) {
        case 1:
            scenario1();
            break;
        case 2:
            scenario2();
            break;
        case 3:
            scenario3();
            break;
        case 4:
            scenario4();
            break;
        default:
            break;
        }
    }
}