package parser;

import java.text.ParseException;
import java.util.Date;
import logic.Enumerator.TaskType;

public class ParserEdit {

	public static void editTask(Interpreter item, String input, String[] inputArray)
			throws ParseException {
		int ID;
		String[] dateArray = null;
		String[] editedInputArray = new String[inputArray.length - 2];
		
		try {
			ID = Integer.parseInt(inputArray[1]);
			item.setTaskID(ID);
			
			if(input.length() < 3) {
				throw new NullPointerException();
			}
			
			if(input.contains("[")) {
				dateArray = defineDate(input);
				String[] newInputArray = input.split("\\[");
				inputArray = newInputArray[0].split(" ");
				for(int i=0; i<inputArray.length; i++) {
				System.out.print(""+inputArray[i]+" ");
				}
				System.out.println();
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
				defineTaskName(item, editedInputArray, editedInputArray.length);
			} else if(editedInputArray.length < 1) {
				throw new ParserException();
			} else {
				defineTaskType(item, editedInputArray, dateArray);
				defineTaskName(item, editedInputArray, editedInputArray.length - dateArray.length);
			}
		} catch (NumberFormatException nfe) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_ID);
		} catch (ParserException pe) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_TEXT);
		} catch (NullPointerException npe) {
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

	public static void defineTaskName(Interpreter item, String[] inputArray, int length) {
		String taskName = "";
		int lastIndex = length - 1;
		for(int i=0; i<=lastIndex; i++){
			if(i==lastIndex) {
				taskName = taskName.concat(inputArray[i]);
				
			} else {
				taskName = taskName.concat(inputArray[i] + " ");
			}			
		}
		item.setIsEditTaskName(true);
		
		try {
			if(taskName.equals("") || taskName.equals(" ")) {
				throw new NullPointerException();
			} else {
				item.setIsEditTaskName(true);
				item.setTaskName(taskName);
			}
		} catch (NullPointerException npe) {
			item.setIsEditTaskName(false);
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_TEXT);
		}
	}
}
