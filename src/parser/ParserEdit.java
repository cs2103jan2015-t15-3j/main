package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import logic.Enumerator.TaskType;

public class ParserEdit {
	
	public static void editTask(Interpreter item, String[] inputArray) throws ParseException {
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
	
	public static void defineTaskType(Interpreter item, String[] inputArray) {
		int inputArrayLength = inputArray.length; 
		String checkLast = inputArray[inputArrayLength - 2] + " " + inputArray[inputArrayLength - 1];
		boolean isDateValid = isDate(checkLast);
		if(!isDateValid) {
			item.setType(TaskType.FLOATING);
			item.setIsDueDate(false);
			item.setIsStartDate(false);
		} else {
			String check2ndLast = inputArray[inputArrayLength - 4] + inputArray[inputArrayLength - 3];
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
	public static boolean isDate(String checkInput) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(checkInput);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}	
	
	public static void defineTaskNameAndDate(Interpreter item, String[] inputArray) throws ParseException {
		int inputArrayLength = inputArray.length; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TaskType checkTaskType = item.getType(); 
		switch(checkTaskType) {
		case FLOATING:
			defineTaskName(item, inputArray, inputArrayLength - 1);
			break;
		case DEADLINE:
			defineTaskName(item, inputArray, inputArrayLength - 2);
			Date date = sdf.parse(inputArray[inputArrayLength - 2] + inputArray[inputArrayLength - 1]);
			item.setDueDate(date);
			break;		
		case APPOINTMENT:
			defineTaskName(item, inputArray, inputArrayLength - 4);
			Date dueDate = sdf.parse(inputArray[inputArrayLength - 2] + inputArray[inputArrayLength - 1]);
			item.setDueDate(dueDate);
			Date startDate = sdf.parse(inputArray[inputArrayLength - 4] + inputArray[inputArrayLength - 3]);
			item.setStartDate(startDate);
			break;
		}
	}
	
	public static void defineTaskName(Interpreter item, String[] inputArray, int lastIndex) {
		String taskName = "";
		for(int i=1; i<=lastIndex; i++){
			taskName = taskName.concat(inputArray[i]);
		}
		item.setTaskName(taskName);
	}

	
}
