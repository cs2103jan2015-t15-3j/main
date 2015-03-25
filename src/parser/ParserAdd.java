package parser;

import java.text.ParseException;
import java.util.Comparator;

import logic.Task;
import logic.Enumerator.TaskType;

public class ParserAdd {
	
	public static void addTask(Interpreter item, String[] inputArray) throws ParseException {
		defineTaskType(item, inputArray);
		defineTaskName(item, inputArray);
		item.setTaskID(0);
		item.setIsCompleted(false);	
	}
	
	public static void defineTaskType(Interpreter item, String[] inputArray) throws ParseException {
		int inputArrayLength = inputArray.length; 
		ParserDateAndTimeChecker.checkDateAndTime(item, inputArray, inputArrayLength);	
	}
	
	public static void defineTaskName(Interpreter item, String[] inputArray) throws ParseException {
		TaskType checkTaskType = item.getType(); 
		switch(checkTaskType) {
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
	
	public static void defineTaskName(Interpreter item, String[] inputArray, int lastIndex) {
		String taskName = "";
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
