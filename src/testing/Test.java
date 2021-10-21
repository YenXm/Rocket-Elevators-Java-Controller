package testing;

import static org.junit.jupiter.api.Assertions.*;

import defaultopackage.*;

public class Test {

	@org.junit.jupiter.api.Test
	public void TestScenario1() {
		Battery battery = new Battery(1, 4, 60, 6, 5);
		Scenarios scenarios = new Scenarios();

		Column expectedColumn = battery.columnsArrayList.get(1);
		Elevator expectedElevator = battery.columnsArrayList.get(1).elevatorsArrayList.get(4);
		int userPosition = 1;
		int destination = 20;
		int[] expectedFinalPositions = { 5, 15, 1, 2, 20 };

		Results results = scenarios.scenario1();

		Elevator chosenElevator = results.chosenElevator;
		Column chosenColumn = results.chosenColumn;
		assertEquals(chosenColumn.ID, expectedColumn.ID,
				"Wrong column selected, expected Column " + expectedColumn.ID + ", got Column " + chosenColumn.ID);

		assertEquals(chosenElevator.ID, expectedElevator.ID, "Wrong elevator selected, expected Elevator "
				+ expectedElevator.ID + ", got Elevator " + chosenElevator.ID);

		assertTrue(chosenElevator.completedRequestsList.contains(userPosition),
				"No elevator was sent to pick up the user");

		assertTrue(chosenElevator.completedRequestsList.contains(userPosition)
				&& chosenElevator.currentFloor == destination, "The user didn't reach its destination");

		for (int i = 0; i < chosenColumn.elevatorsArrayList.size(); i++) {
			assertEquals(chosenColumn.elevatorsArrayList.get(i).currentFloor, expectedFinalPositions[i],
					"Elevator " + chosenColumn.elevatorsArrayList.get(i).ID
							+ " didn't finish at the correct floor, expected " + expectedFinalPositions[i] + ", got "
							+ chosenColumn.elevatorsArrayList.get(0).currentFloor);
		}
	}

	@org.junit.jupiter.api.Test
	public void TestScenario2() {
		Battery battery = new Battery(1, 4, 60, 6, 5);
		Scenarios scenarios = new Scenarios();

		Column expectedColumn = battery.columnsArrayList.get(2);
		Elevator expectedElevator = expectedColumn.elevatorsArrayList.get(0);
		int userPosition = 1;
		int destination = 36;
		int[] expectedFinalPositions = { 36, 28, 1, 24, 1 };

		Results results = scenarios.scenario2();

		Elevator chosenElevator = results.chosenElevator;
		Column chosenColumn = results.chosenColumn;
		assertEquals(chosenColumn.ID, expectedColumn.ID,
				"Wrong column selected, expected Column " + expectedColumn.ID + ", got Column " + chosenColumn.ID);

		assertEquals(chosenElevator.ID, expectedElevator.ID, "Wrong elevator selected, expected Elevator "
				+ expectedElevator.ID + ", got Elevator " + chosenElevator.ID);

		assertTrue(chosenElevator.completedRequestsList.contains(userPosition),
				"No elevator was sent to pick up the user");

		assertTrue(chosenElevator.completedRequestsList.contains(userPosition)
				&& chosenElevator.currentFloor == destination, "The user didn't reach its destination");

		for (int i = 0; i < chosenColumn.elevatorsArrayList.size(); i++) {
			assertEquals(chosenColumn.elevatorsArrayList.get(i).currentFloor, expectedFinalPositions[i],
					"Elevator " + chosenColumn.elevatorsArrayList.get(i).ID
							+ " didn't finish at the correct floor, expected " + expectedFinalPositions[i] + ", got "
							+ chosenColumn.elevatorsArrayList.get(0).currentFloor);
		}
	}

	@org.junit.jupiter.api.Test
	public void TestScenario3() {
		Scenarios scenarios = new Scenarios();

		Results results = scenarios.scenario3();

		Elevator expectedElevator = results.chosenColumn.elevatorsArrayList.get(0);
		int userPosition = 54;
		int destination = 1;
		int[] expectedFinalPositions = { 1, 60, 58, 54, 1 };

		Elevator chosenElevator = results.chosenElevator;
		Column chosenColumn = results.chosenColumn;
		assertEquals(chosenElevator.ID, expectedElevator.ID, "Wrong elevator selected, expected Elevator "
				+ expectedElevator.ID + ", got Elevator " + chosenElevator.ID);

		assertTrue(chosenElevator.completedRequestsList.contains(userPosition),
				"No elevator was sent to pick up the user");

		assertTrue(chosenElevator.completedRequestsList.contains(userPosition)
				&& chosenElevator.currentFloor == destination, "The user didn't reach its destination");

		for (int i = 0; i < chosenColumn.elevatorsArrayList.size(); i++) {
			assertEquals(chosenColumn.elevatorsArrayList.get(i).currentFloor, expectedFinalPositions[i],
					"Elevator " + chosenColumn.elevatorsArrayList.get(i).ID
							+ " didn't finish at the correct floor, expected " + expectedFinalPositions[i] + ", got "
							+ chosenColumn.elevatorsArrayList.get(0).currentFloor);
		}
	}

	@org.junit.jupiter.api.Test
	public void TestScenario4() {
		Scenarios scenarios = new Scenarios();

		Results results = scenarios.scenario4();

		Elevator expectedElevator = results.chosenColumn.elevatorsArrayList.get(3);
		int userPosition = -3;
		int destination = 1;
		int[] expectedFinalPositions = { -4, 1, -5, 1, -6 };

		Elevator chosenElevator = results.chosenElevator;
		Column chosenColumn = results.chosenColumn;
		assertEquals(chosenElevator.ID, expectedElevator.ID, "Wrong elevator selected, expected Elevator "
				+ expectedElevator.ID + ", got Elevator " + chosenElevator.ID);

		assertTrue(chosenElevator.completedRequestsList.contains(userPosition),
				"No elevator was sent to pick up the user");

		assertTrue(chosenElevator.completedRequestsList.contains(userPosition)
				&& chosenElevator.currentFloor == destination, "The user didn't reach its destination");

		for (int i = 0; i < chosenColumn.elevatorsArrayList.size(); i++) {
			assertEquals(chosenColumn.elevatorsArrayList.get(i).currentFloor, expectedFinalPositions[i],
					"Elevator " + chosenColumn.elevatorsArrayList.get(i).ID
							+ " didn't finish at the correct floor, expected " + expectedFinalPositions[i] + ", got "
							+ chosenColumn.elevatorsArrayList.get(0).currentFloor);
		}
	}
}
