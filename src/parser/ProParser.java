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
		if(input.contains("<")){
			String[] splitInput = input.split("<");
			String remarks = splitInput[1];
			item.setRemarks(remarks);
			String[] inputArray = splitInput[0].split(" ");
			defineCommand(item, inputArray);
		} else {
			String[] inputArray = input.split(" ");
			item.setRemarks("");
			defineCommand(item, inputArray);
		}
		
		return item;
	}
	
	private static void defineCommand(Interpreter item, String[] inputArray) throws ParseException {
		String command = inputArray[0].toLowerCase();
		
		switch (command) {
		case "add":
			item.setCommandType(Interpreter.CommandType.ADD);
			addTask(item, inputArray);
			break;
		case "delete":
			item.setCommandType(Interpreter.CommandType.DELETE);
			deleteTask(item, inputArray);
			break;
		case "clear":
			item.setCommandType(Interpreter.CommandType.CLEAR);
			break;	
		case "display":
			item.setCommandType(Interpreter.CommandType.DISPLAY);
			break;
		case "search":
			item.setCommandType(Interpreter.CommandType.SEARCH);
			searchTask(item, inputArray);
			break;
		case "edit":
			item.setCommandType(Interpreter.CommandType.EDIT);
			editTask(item, inputArray);
			break;
		case "undo":
			item.setCommandType(Interpreter.CommandType.UNDO);
			
			break;
		case "complete":
			item.setCommandType(Interpreter.CommandType.COMPLETE);
			completeTask(item, inputArray);
			break;
		case "uncomplete":
			item.setCommandType(Interpreter.CommandType.UNCOMPLETE);
			uncompleteTask(item, inputArray);
			break;
		case "powersearch":
			item.setCommandType(Interpreter.CommandType.POWERSEARCH);
			
			break;
		}
	}
	
	private static void addTask(Interpreter item, String[] inputArray) throws ParseException {
		defineTaskType(item, inputArray);
		defineTaskNameAndDate(item, inputArray);
		item.setTaskID(0);
		item.setIsCompleted(false);		
	}
	
	private static void editTask(Interpreter item, String[] inputArray) throws ParseException {
		int ID = Integer.parseInt(inputArray[1]);
		item.setTaskID(ID);
		int lengthText = inputArray.length - 2;
		String[] newInputArray = new String[lengthText];
		for(int i=2; i<=lengthText; i++){
			newInputArray[i-2] = inputArray[i];
		}
		defineTaskType(item, newInputArray);
		defineTaskNameAndDate(item, newInputArray);
	}
/*===========================ADD & EDIT METHODS===============================*/ 	
	private static void defineTaskType(Interpreter item, String[] inputArray) {
		int inputArrayLength = inputArray.length; 
		String checkLast = inputArray[inputArrayLength - 1];
		boolean isDateValid = isDate(checkLast);
		if(!isDateValid) {
			item.setType(TaskType.FLOATING);
			item.setIsDueDate(false);
			item.setIsStartDate(false);
		} else {
			String check2ndLast = inputArray[inputArrayLength - 2];
			boolean checkStartDate = isDate(check2ndLast);
			if(!checkStartDate) {
				item.setType(TaskType.DEADLINE);
				item.setIsStartDate(false);
			} else {
				item.setType(TaskType.APPOINTMENT);
			}
		}
	}
	
	//another method is to check for the '/', split by '/', then 
	//check if each box in the array matches the dd/mm/yy format
	//check if the integers are valid i.e within the range
	private static boolean isDate(String checkInput) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(checkInput);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}	
	
	private static void defineTaskNameAndDate(Interpreter item, String[] inputArray) throws ParseException {
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
	
	private static void defineTaskName(Interpreter item, String[] inputArray, int lastIndex) {
		String taskName = "";
		for(int i=1; i<=lastIndex; i++){
			taskName = taskName.concat(inputArray[i]);
		}
		item.setTaskName(taskName);
	}
/*===========================ADD & EDIT METHODS===============================*/
	
	private static void deleteTask(Interpreter item, String[] inputArray) {
		int ID = Integer.parseInt(inputArray[1]);
		item.setTaskID(ID);
	}
	
	private static void searchTask(Interpreter item, String[] inputArray) {
		String searchKey = "";
		int lengthSearchKey = inputArray.length-1;
		for(int i=1; i<=lengthSearchKey; i++){
			searchKey = searchKey.concat(inputArray[i]);
		}
		item.setKey(searchKey);
	}
	
	private static void completeTask(Interpreter item, String[] inputArray) {
		int ID = Integer.parseInt(inputArray[1]);
		item.setTaskID(ID);
		item.setIsCompleted(true);
	}
	
	private static void uncompleteTask(Interpreter item, String[] inputArray) {
		int ID = Integer.parseInt(inputArray[1]);
		item.setTaskID(ID);
		item.setIsCompleted(false);
	}
	
	private static void powerSearchTask(Interpreter item, String[] inputArray) {
		
	}
}