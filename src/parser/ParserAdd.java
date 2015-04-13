package parser;

import java.text.ParseException;

//@author: A0110818M
public class ParserAdd {
	
	public static void addTask(Interpreter item, String input, String[] inputArray) throws ParseException {
		String[] dateArray = null;
		int secondEntry = 1;
		try {
			if(inputArray.length < 2 || inputArray[secondEntry].equals("[]") || inputArray[secondEntry].equals("[")) {
				throw new ParserException();
			} else {
				if(input.contains("[")) {
					dateArray = defineDate(input);
					String[] newInputArray = input.split("\\[");
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
	
	private static String[] defineDate(String input) {
		String[] dateArray = null;
		String[] splitInput = input.split("\\[");
		if(splitInput[splitInput.length - 1].equals("]")) {
			dateArray = null;
			return dateArray;
		} else {
			if(splitInput[splitInput.length - 1].contains("]")) {
				String[] splitDate = splitInput[splitInput.length - 1].split("\\]");
				dateArray = splitDate[0].split(" ");
			} else {
				dateArray = splitInput[splitInput.length - 1].split(" ");
			}
		return dateArray;
		}
	}
	
	// TaskType of the entry is set in ParserDateAndTimeChecker if the input is valid
	private static void defineTaskType(Interpreter item, String[] inputArray, String[] dateArray) throws ParseException {
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
	
	private static void defineTaskName(Interpreter item, String[] inputArray) {
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
	
	private static String convertArrayToString(String[] inputArray, int startIndex, int lastIndex) {
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
