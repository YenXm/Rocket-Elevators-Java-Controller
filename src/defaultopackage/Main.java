package defaultopackage;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		Door newDoow = new Door(1, "closed");
		FloorRequestButton newFlooRequestButton = new FloorRequestButton(1, "off", 1, "up");
		CallButton newCallButton = new CallButton(1, "off", 1, "up");
		Elevator elevator = new Elevator("1", "idle", 20, 1);
		ArrayList<Integer> serveFloor = new ArrayList<Integer>();
		Battery battery = new Battery(1, 4, 60, 6, 5);
		Collections.addAll(serveFloor, 1, 2, 3, 4, 5);
		/* Column column = new Column("1", "online", 5, 2, serveFloor, false); */
		System.out.println(newDoow.ID);
		System.out.println(newCallButton.ID);
		System.out.println(newFlooRequestButton.ID);
		System.out.println("Elevator ID : " + elevator.ID);
		elevator.addNewRequest(3);
		System.out.println("Elevator Request List : " + elevator.floorRequestsList);
		elevator.move();
		System.out.println("Elevator Current Floor : " + elevator.currentFloor);

		battery.columnsArrayList.get(2).elevatorsArrayList.get(3).currentFloor = 2;
		System.out.println(battery.columnsArrayList.get(2).servedFloorsArrayList);
		System.out.println(battery.columnsArrayList.get(2).elevatorsArrayList.get(3).currentFloor);

	}

}
