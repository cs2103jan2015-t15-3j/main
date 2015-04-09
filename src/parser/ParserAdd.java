package parser;

import java.text.ParseException;
import java.util.Comparator;

import logic.Task;
import logic.Enumerator.TaskType;

public class ParserAdd {
	
	public static void addTask(Interpreter item, String input, String[] inputArray) throws ParseException {
		String[] dateArray = null;
		
		if(input.contains("[")) {
			dateArray = defineDate(input);
			String[] newInputArray = input.split("\\[");
			System.out.println("newInputArray "+newInputArray[0]);
			inputArray = newInputArray[0].split(" ");
		}
		
		defineTaskType(item, inputArray, dateArray);
		defineTaskName(item, inputArray);
		item.setTaskID(0);
		item.setIsCompleted(false);	
	}
	
	public static String[] defineDate(String input) {
			System.out.println("1");
			System.out.println("input "+input);
			String[] splitDate = input.split("\\[");
			System.out.println("2");
			String[] dateArray = splitDate[1].split(" ");
			System.out.println("3");
			return dateArray;
	}
	
	public static void defineTaskType(Interpreter item, String[] inputArray, String[] dateArray) throws ParseException {
		int dateArrayLength; 
		if(dateArray == null) {
			dateArrayLength = 0;
		} else {
			dateArrayLength = dateArray.length; 
		}
		boolean isValidDateAndTime = ParserDateAndTimeChecker.isDateAndTime(item, dateArray, dateArrayLength);	
		if(!isValidDateAndTime) {
			item.setType(TaskType.INVALID);
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
