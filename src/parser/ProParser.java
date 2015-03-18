package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import logic.Enumerator.TaskType;

public class ProParser {
	
	Interpreter item = new Interpreter();
	
	public static Interpreter parse(String input) throws ParseException {
		Interpreter item = new Interpreter();
		//Split the input string and check for remarks
		String[] splitInput = input.split("<");
		if(splitInput[1] != null) {
			String remarks = splitInput[1];
			item.setRemarks(remarks);
		} 
		
		String[] inputArray = splitInput[0].split(" ");
		
		defineCommand(item, inputArray);
		defineTaskType(item, inputArray);
		defineTaskNameAndDate(item, inputArray);
		item.setTaskID(0);
		item.setIsCompleted(false);
				
		return item;
	}
	
	public static void defineCommand(Interpreter item, String[] inputArray) {
		String command = inputArray[0].toLowerCase();
		
		switch (command) {
		case "add":
			item.setCommandType(Interpreter.CommandType.ADD);
			break;
		case "delete":
			item.setCommandType(Interpreter.CommandType.DELETE);
			break;
		case "display":
			item.setCommandType(Interpreter.CommandType.DISPLAY);
			break;
		case "search":
			item.setCommandType(Interpreter.CommandType.SEARCH);
			break;
		case "edit":
			item.setCommandType(Interpreter.CommandType.EDIT);
			break;
		case "undo":
			item.setCommandType(Interpreter.CommandType.UNDO);
			break;
		case "complete":
			item.setCommandType(Interpreter.CommandType.COMPLETE);
			break;
		case "uncomplete":
			item.setCommandType(Interpreter.CommandType.UNCOMPLETE);
			break;
		case "powersearch":
			item.setCommandType(Interpreter.CommandType.POWERSEARCH);
			break;
		}
	}
	
	public static void defineTaskType(Interpreter item, String[] inputArray) {
		int inputArrayLength = inputArray.length; 
		String checkLast = inputArray[inputArrayLength - 1];
		boolean isDateValid = isDate(checkLast);
		if(!isDateValid) {
			item.setType(TaskType.FLOATING);
		} else {
			String check2ndLast = inputArray[inputArrayLength - 2];
			boolean checkStartDate = isDate(check2ndLast);
			if(!checkStartDate) {
				item.setType(TaskType.DEADLINE);
			} else {
				item.setType(TaskType.APPOINTMENT);
			}
		}
	}
	
	public static boolean isDate(String checkInput) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(checkInput);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	public static void defineTaskNameAndDate(Interpreter item, String[] inputArray) throws ParseException {
		int inputArrayLength = inputArray.length; 
		SimpleDateFormat sdf = new SimpleDateFormat();
		TaskType checkTaskType = item.getType(); 
		switch(checkTaskType) {
		case FLOATING:
			defineTaskName(item, inputArray, inputArrayLength - 1);
			break;
		case DEADLINE:
			defineTaskName(item, inputArray, inputArrayLength - 2);
			Date date = sdf.parse(inputArray[inputArrayLength - 1]);
			item.setDueDate(date);
			break;		
		case APPOINTMENT:
			defineTaskName(item, inputArray, inputArrayLength - 3);
			Date dueDate = sdf.parse(inputArray[inputArrayLength - 1]);
			item.setDueDate(dueDate);
			Date startDate = sdf.parse(inputArray[inputArrayLength - 2]);
			item.setStartDate(startDate);
			break;
		}
	}
	
	public static void defineTaskName(Interpreter item, String[] inputArray, int lastIndex) {
		String taskName = " ";
		for(int i=1; i<=lastIndex; i++){
			taskName = taskName.concat(inputArray[i]);
		}
		item.setTaskName(taskName);
	}
}