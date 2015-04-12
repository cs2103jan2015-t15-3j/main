package logic;

import logic.Enumerator.KEY;

//@author A0112643R

public class Converter {

	protected static KEY KeyConverter(String key) {
		KEY keyReturn;

		switch (key) {
		case "taskname":
			keyReturn = KEY.TASKNAME;
			break;

		case "startdate":
			keyReturn = KEY.STARTDATE;
			break;

		case "duedate":
			keyReturn = KEY.DUEDATE;
			break;

		default:
			keyReturn = KEY.REMARKS;
		}
		return keyReturn;
	}

	protected static int convertToInt(String input) {
		int number = Integer.parseInt(input);
		return number;
	}
}
