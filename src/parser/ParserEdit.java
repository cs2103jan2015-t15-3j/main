package parser;

import java.text.ParseException;


import java.util.Date;

import logic.Enumerator.TaskType;

public class ParserEdit {

	public static void editTask(Interpreter item, String[] inputArray)
			throws ParseException {
		int ID = Integer.parseInt(inputArray[1]);
		item.setTaskID(ID);
		String[] newInputArray = new String[inputArray.length - 2];
		for (int i = 2; i < inputArray.length; i++) {
			newInputArray[i - 2] = inputArray[i];
		}
		
		if(newInputArray.length == 1) {
			item.setType(TaskType.FLOATING);
			item.setIsDueDate(false);
			item.setIsStartDate(false);
			Date date = null;
			item.setStartDate(date);
			item.setDueDate(date);
			item.setLastIndexTaskName(newInputArray.length-1);
			
			defineTaskName(item, newInputArray);
		} else if(newInputArray.length < 1) {
			System.out.println("Error. Please input more information for update.");
		} else {
			defineTaskType(item, newInputArray);
			defineTaskName(item, newInputArray);	
		}
	}

	public static void defineTaskType(Interpreter item, String[] inputArray)
			throws ParseException {
		int inputArrayLength = inputArray.length;
		ParserDateAndTimeChecker.checkDateAndTime(item, inputArray,
				inputArrayLength);
	}

	public static void defineTaskName(Interpreter item, String[] inputArray)
			throws ParseException {
		TaskType checkTaskType = item.getType();
		switch (checkTaskType) {
		case FLOATING:
			defineTaskName(item, inputArray, item.getLastIndexTaskName());
			break;
		case DEADLINE:
			defineTaskName(item, inputArray, item.getLastIndexTaskName());
			break;
		case APPOINTMENT:
			defineTaskName(item, inputArray, item.getLastIndexTaskName());
			break;
		}
	}

	public static void defineTaskName(Interpreter item, String[] inputArray,
			int lastIndex) {
		String taskName = "";
		for (int i = 0; i <= lastIndex; i++) {
			if (i == lastIndex) {
				taskName = taskName.concat(inputArray[i] + " ");
			} else {
				taskName = taskName.concat(inputArray[i] + " ");
			}
		}
		item.setTaskName(taskName);
		item.setKey("taskname");
	}
}
