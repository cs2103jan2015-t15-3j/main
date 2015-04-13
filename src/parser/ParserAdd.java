package parser;

import java.text.ParseException;

public class ParserAdd {
	
	public static void addTask(Interpreter item, String input, String[] inputArray) throws ParseException {
		String[] dateArray = null;
		int startIndex = 1;
		try {
			if(input.length() < 2) {
				throw new ParserException();
			} else {
				if(input.contains("[")) {
					dateArray = defineDate(input);
					String[] newInputArray = input.split("\\[");
					//String taskName = convertArrayToString(newInputArray, startIndex, newInputArray.length - 2);
					inputArray = newInputArray[0].split(" ");
				}
				
				defineTaskType(item, inputArray, dateArray);
				defineTaskName(item, inputArray);
				item.setIsCompleted(false);	
			}
			
		} catch (ParserException npe) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_INPUT);
		}
	}
	
	public static String[] defineDate(String input) {
			String[] splitInput = input.split("\\[");
			String[] splitDate = splitInput[splitInput.length - 1].split("\\]");
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
		
		try {
			boolean isValidDateAndTime = ParserDateAndTimeChecker.isDateAndTime(item, dateArray, dateArrayLength);
			
			// This will report invalid formats for date and/or time 
			if(!isValidDateAndTime) {
				throw new ParserException();
			} 
		} catch (ParserException pe) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_DATE_TIME_FORMAT);
		}
	}
	
	public static void defineTaskName(Interpreter item, String[] inputArray) {
		String taskName = "";
		int startIndex = 1;
		int lastIndex = inputArray.length - 1;
		
		taskName = convertArrayToString(inputArray, startIndex, lastIndex);
		
		try {
			if(taskName.equals("") || taskName.equals(" ")) {
				throw new NullPointerException();
			} else {
				item.setTaskName(taskName);
			}
		} catch (NullPointerException npe) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_TEXT);
		}
	}
	
	public static String convertArrayToString(String[] inputArray, int startIndex, int lastIndex) {
		String taskName = "";
		for(int i=startIndex; i<=lastIndex; i++){
			if(i==lastIndex) {
				taskName = taskName.concat(inputArray[i]); 
			} else {
				taskName = taskName.concat(inputArray[i] + " ");	
			}			
		}
		return taskName;
	}
}
