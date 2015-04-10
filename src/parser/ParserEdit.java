package parser;

import java.text.ParseException;
import java.util.Date;

import logic.Enumerator.TaskType;

public class ParserEdit {

	public static void editTask(Interpreter item, String input, String[] inputArray)
			throws ParseException {
		
		String[] dateArray = null;
		int ID = Integer.parseInt(inputArray[1]);
		String[] editedInputArray = new String[inputArray.length - 2];
		
		item.setTaskID(ID);
		
		if(input.contains("[")) {
			dateArray = defineDate(input);
			String[] newInputArray = input.split("\\[");
			inputArray = newInputArray[0].split(" ");
		}
		
		
		for (int i = 2; i < inputArray.length; i++) {
			editedInputArray[i - 2] = inputArray[i];
		}
		
		if(editedInputArray.length == 1) {
			item.setType(TaskType.FLOATING);
			item.setIsDueDate(false);
			item.setIsStartDate(false);
			Date date = null;
			item.setStartDate(date);
			item.setDueDate(date);
			
			defineTaskName(item, editedInputArray);
		} else if(editedInputArray.length < 1) {
			System.out.println("Error. Please input more information for update.");
		} else {
			defineTaskType(item, editedInputArray);
			defineTaskName(item, editedInputArray);	
		}
	}
	
	// This method checks the category targeted for edit
	// by the user
	public static void defineCategory() {
		
	}
	
	
	public static String[] defineDate(String input) {
		String[] splitInput = input.split("\\[");
		String[] splitDate = splitInput[1].split("\\]");
		String[] dateArray = splitDate[0].split(" ");
		return dateArray;
}

	public static void defineTaskType(Interpreter item, String[] inputArray)
			throws ParseException {
		int inputArrayLength = inputArray.length;
		ParserDateAndTimeChecker.isDateAndTime(item, inputArray,
				inputArrayLength);
	}

	public static void defineTaskName(Interpreter item, String[] inputArray)
			throws ParseException {
		String taskName = "";
		int lastIndex = inputArray.length - 1;
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
