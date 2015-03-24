package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

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
		int inputArrayLength = inputArray.length; 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm a");
		TaskType checkTaskType = item.getType(); 
		switch(checkTaskType) {
		case FLOATING:
			defineTaskName(item, inputArray, inputArrayLength - 1);
			break;
		case DEADLINE:
			defineTaskName(item, inputArray, inputArrayLength - 2);
			break;		
		case APPOINTMENT:
			defineTaskName(item, inputArray, inputArrayLength - 4);
			break;
		}
	}
	
	public static void defineTaskName(Interpreter item, String[] inputArray, int lastIndex) {
		String taskName = "";
		for(int i=1; i<=lastIndex; i++){
			if(i==1) {
				taskName = taskName.concat(inputArray[i]); 
			} else {
				taskName = taskName.concat(" "+inputArray[i]);	
			}			
		}
		item.setTaskName(taskName);
	}
	
	public static Comparator<Task> numComparator = new Comparator<Task>() {
		public int compare(Task bufferOne, Task bufferTwo) {
			int first = bufferOne.getTaskID();
			int second = bufferTwo.getTaskID();
			return first - second;
		}
	};
}
