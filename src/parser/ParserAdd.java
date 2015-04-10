package parser;

import java.text.ParseException;
import logic.Enumerator.ErrorType;

public class ParserAdd {
	
	public static void addTask(Interpreter item, String input, String[] inputArray) throws ParseException {
		String[] dateArray = null;
		
		if(input.contains("[")) {
			dateArray = defineDate(input);
			String[] newInputArray = input.split("\\[");
			inputArray = newInputArray[0].split(" ");
		}
		
		defineTaskType(item, inputArray, dateArray);
		defineTaskName(item, inputArray);
		item.setTaskID(0);
		item.setIsCompleted(false);	
	}
	
	public static String[] defineDate(String input) {
			String[] splitInput = input.split("\\[");
			String[] splitDate = splitInput[1].split("\\]");
			String[] dateArray = splitDate[0].split(" ");
			return dateArray;
	}
	
	// TaskType of the entry is set in ParserDateAndTimeChecker if the input is valid
	public static void defineTaskType(Interpreter item, String[] inputArray, String[] dateArray) throws ParseException {
		int dateArrayLength; 
		if(dateArray == null) {
			dateArrayLength = 0;
		} else {
			dateArrayLength = dateArray.length; 
		}
		boolean isValidDateAndTime = ParserDateAndTimeChecker.isDateAndTime(item, dateArray, dateArrayLength);
		
		// This will report invalid formats for date and/or time 
		if(!isValidDateAndTime) {
			item.setErrorType(ErrorType.INVALID_DATE_TIME_FORMAT);
		}
	}
	
	public static void defineTaskName(Interpreter item, String[] inputArray) {
		String taskName = "";
		int lastIndex = inputArray.length - 1;
		for(int i=1; i<=lastIndex; i++){
			if(i==lastIndex) {
				taskName = taskName.concat(inputArray[i]); 
			} else {
				taskName = taskName.concat(inputArray[i] + " ");	
			}			
		}
		item.setTaskName(taskName);
	}
}
