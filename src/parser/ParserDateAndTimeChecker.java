package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import logic.Compare;

import java.util.Comparator;
import java.util.Date;

import logic.Enumerator.TaskType;


/*
 *	There are several possibilities for the inputs of date and time 
 *	Floating:
 *	add taskName 
 *
 *	Deadline: 
 *	add taskName date
 *	add taskName date time
 *
 *	Appointment:
 *	add taskName date date 
 *	add taskName date time date 
 *	add taskName date date time 
 *	add taskName date time date time
 *
 */

public class ParserDateAndTimeChecker{
	
	// Variables stand for the last, secondLast, thirdLast and fourthLast 
	// elements of the input array respectively
	private static String last, secondLast, thirdLast, fourthLast;
	// If no time is given but a date is given,
	// set default time to 23:59
	private static String DEFAULT_TIME = "23:59";
	static Comparator<Date> dateComparator;
	
	public static void checkDateAndTime(Interpreter item, String[] input, int length) throws ParseException {
			if(length < 2) {
				System.out.println("Error. Please input again");
			} else if(length == 2) {
				last = input[length - 1];
				if(isDate(last)) {
					System.out.println("Error. Pls input taskName");
				} else {
					setFloating(item,length);
				}				
			} else if(length > 2 && length < 4) {
				last = input[length - 1];
				if(!isTime(last) && !isDate(last)) {
					setFloating(item,length);
				} else if(!isTime(last) && isDate(last)) {
					setDeadline(last, DEFAULT_TIME, item);
					item.setLastIndexTaskName(length - 2);
				} else {
					System.out.println("Error. Please input again");
				}
				
			} else if(length > 2 && length < 5) {
								
				last = input[length - 1];
				secondLast = input[length - 2];

				if(!isTime(last) && !isDate(last)) {
					setFloating(item,length);
				} else if(!isTime(last) && isDate(last) && !isDate(secondLast)) {
					setDeadline(last, DEFAULT_TIME, item);
					item.setLastIndexTaskName(length - 2);
				} else if(isTime(last) && isDate(secondLast)) {
					setDeadline(secondLast, last, item);
					item.setLastIndexTaskName(length - 3);
				} else if(isDate(last) && isDate(secondLast)){
					
					setAppointment(secondLast, DEFAULT_TIME, last, DEFAULT_TIME, item);
					item.setLastIndexTaskName(length - 3);
				} else {
					System.out.println("Error. Please input again");
				}
				
			} else if(length > 2 && length < 6) {
							
				last = input[length - 1];
				secondLast = input[length - 2];
				thirdLast = input[length - 3];
				
				if(!isTime(last) && !isDate(last)) {
					setFloating(item,length);
				} else if(!isTime(last) && isDate(last)) {
					setDeadline(last, DEFAULT_TIME, item);
					item.setLastIndexTaskName(length - 2);
				} else if(isTime(last) && isDate(secondLast) && !isDate(thirdLast)) {
					setDeadline(secondLast, last, item);
					item.setLastIndexTaskName(length - 3);
				} else if(isDate(last) && isDate(secondLast)) {
					setAppointment(secondLast, DEFAULT_TIME, last, DEFAULT_TIME, item);
					item.setLastIndexTaskName(length - 3);
				} else if(isTime(last) && isDate(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, DEFAULT_TIME, secondLast, last, item);
					item.setLastIndexTaskName(length - 4);
				} else if (isDate(last) && isTime(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, secondLast, last, DEFAULT_TIME, item);
					item.setLastIndexTaskName(length - 4);
				} else {
					System.out.println("Error. Please input again");
				}
				
				
			} else {
				
				last = input[length - 1];
				secondLast = input[length - 2];
				thirdLast = input[length - 3];
				fourthLast = input[length - 4];
				
				if(!isTime(last) && !isDate(last)) {
					setFloating(item,length);
				} else if(!isTime(last) && isDate(last) && !isDate(secondLast) && !isTime(secondLast)) {
					setDeadline(last, DEFAULT_TIME, item);
					item.setLastIndexTaskName(length - 2);
				} else if(isTime(last) && isDate(secondLast) && !isDate(thirdLast) && !isTime(thirdLast)) {
					setDeadline(secondLast, last, item);
					item.setLastIndexTaskName(length - 3);
				} else if(isDate(last) && isDate(secondLast)) {
					setAppointment(secondLast, DEFAULT_TIME, last, DEFAULT_TIME, item);
					item.setLastIndexTaskName(length - 3);
				} else if(isTime(last) && isDate(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, DEFAULT_TIME, secondLast, last, item);
					item.setLastIndexTaskName(length - 4);
				} else if (isDate(last) && isTime(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, secondLast, last, DEFAULT_TIME, item);
					item.setLastIndexTaskName(length - 4);
				} else if (isTime(last) && isDate(secondLast) && isTime(thirdLast) && isDate(fourthLast)) {
					setAppointment(fourthLast, thirdLast, secondLast, last, item);
					item.setLastIndexTaskName(length - 5);
				} else {
					System.out.println("Error. Please input again");
				}
			}
	}
	
	private static boolean isDate(String checkInput) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(checkInput);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}	
	
	private static boolean isTime(String checkInput) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(checkInput);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	private static void setFloating(Interpreter item, int length){
		item.setType(TaskType.FLOATING);
		item.setIsDueDate(false);
		item.setIsStartDate(false);
		Date date = null;
		item.setStartDate(date);
		item.setDueDate(date);
		item.setLastIndexTaskName(length - 1);
	}
	
	private static void setDeadline(String dueDate, String endTime, Interpreter item){
		item.setType(TaskType.DEADLINE);
		item.setIsStartDate(false);
		Date startDate = null;
		Date resultDueDate = setDate(dueDate, endTime);
		item.setStartDate(startDate);
		item.setDueDate(resultDueDate);
	}
	
	private static void setAppointment(String startDate, String startTime, 
			String dueDate, String endTime, Interpreter item){
		
		Date resultStartDate = setDate(startDate, startTime);
		Date resultDueDate = setDate(dueDate, endTime);
		item.setType(TaskType.APPOINTMENT);
		item.setStartDate(resultStartDate);
		item.setDueDate(resultDueDate);
	}
	
	private static Date setDate(String inputDate, String inputTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		String inputDateAndTime = inputDate + " " + inputTime;
		
		sdf.setLenient(true);
		try {
			Date date = sdf.parse(inputDateAndTime);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
	
}
