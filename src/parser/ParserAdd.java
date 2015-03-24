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
		defineTaskNameAndDate(item, inputArray);
		item.setTaskID(0);
		item.setIsCompleted(false);	
		item.setCommandType(Interpreter.CommandType.ADD);
	}
	
	public static void defineTaskType(Interpreter item, String[] inputArray) {
		int inputArrayLength = inputArray.length; 
		
		for(int i=0; i<1; i++) {
			
			if(inputArrayLength < 2) {
				System.out.print("Error. Please input again");
			} else if(inputArrayLength == 2) {
				// set floating
			} else if(inputArrayLength > 2 && inputArrayLength < 4) {
				// check last
				// if time--> error
				// if !time, if date --> deadline
				// if !time, !date --> floating
			} else if(inputArrayLength > 2 && inputArrayLength < 5) {
				// check last and 2ndlast
				// if !time and !date --> floating
				// if time and if date --> deadline
				// if time, if !date --> error
				// if time and if time --> error
				// if date and if date --> appointment

			} else if(inputArrayLength > 2 && inputArrayLength < 6) {
				// check 3rdlast, 2ndlast, last
				// ttt, dtt, tdt --> error
				// !t!d --> floating
				// dtd, ddt, nil dd --> appointment
				// nil dt, nil --> deadline
							
			} else {
				// else if(inputArrayLength >= 6) {	
				// check last 4 entries
				// nilnilnilnil --> floating
				// nilnil dt, nilnilnil d --> deadline
				// nilnil dd, nildtd, nilddt, dtdt --> appointment
			}
			
			
			
			
			
			String checkLast = inputArray[inputArrayLength - 1];
			String check2ndLast = inputArray[inputArrayLength - 2];
			
			boolean isLastEntryTimeValid = isTime(checkLast);
			boolean isLastEntryDateValid = isDate(checkLast);
			boolean is2ndLastEntryDateValid;
			
			if(!isLastEntryTimeValid) {
				if(!isLastEntryDateValid) {
					item.setType(TaskType.FLOATING);
					item.setIsDueDate(false);
					item.setIsStartDate(false);
					System.out.println("!isDateValid --> Floating");
				} else {
					if(inputArrayLength < 4) {
						item.setType(TaskType.DEADLINE);
						item.setIsStartDate(false);
						System.out.println("isDateValid --> Deadline");
					} else {
						
					}
				}
			
			} else {
				
				if(inputArrayLength < 4) {
					item.setType(TaskType.DEADLINE);
					item.setIsStartDate(false);
					System.out.println("isDateValid --> Deadline");
				} else {
					String check3rdLast = inputArray[inputArrayLength - 3];
					String check4thLast = inputArray[inputArrayLength - 4];
					boolean checkStartDate = isDate(check3rdLast);
					
					if(!checkStartDate) {
						item.setType(TaskType.DEADLINE);
						item.setIsStartDate(false);
						System.out.println("len>4 but no start date --> Deadline");
					} else {
						item.setType(TaskType.APPOINTMENT);
						System.out.println("len>4 with start date --> Appointment");
					}	
				}
			}
		}
	}	
	
	public static boolean isDate(String checkInput) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(checkInput);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}	
	
	public static boolean isTime(String checkInput) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm a");
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
			taskName = taskName.concat(inputArray[i] + " ");
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
