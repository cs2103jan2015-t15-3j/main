package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//import logic.Compare;

//import java.util.Comparator;
import java.util.Date;

import logic.Enumerator.TaskType;


/*
 *	There are several possibilities for the inputs of date and time 
 *
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
	
	// If no time is given but a date is given,
	// set default time to 23:59
	private static String DEFAULT_TIME = "23:59";
	// private static Comparator<Date> dateComparator;
	
	public static void checkDateAndTime(Interpreter item, String[] input, int length) throws ParseException {
		
		switch(length) {
			case 0:
				setFloating(item);
			case 1:
				if(isDate(input[0])) {
					setDeadline(input[0], DEFAULT_TIME, item);
				} else {
					
				}
			case 2:
				if(isDate(input[0]) && isTime(input[1])) {
					setDeadline(input[0], input[1], item);
				} else if(isDate(input[0]) && isDate(input[1])) {
					setAppointment(input[0], DEFAULT_TIME, input[1], DEFAULT_TIME, item);
				} else {
					
				}
			case 3:
				if(isDate(input[0]) && isTime(input[1]) && isDate(input[2])) {
					setAppointment(input[0], input[1], input[2], DEFAULT_TIME, item);
				} else {
					
				}
			
			case 4: 
				if(isDate(input[0]) && isTime(input[1]) && isDate(input[2]) && isTime(input[3])) {
					setAppointment(input[0], input[1], input[2], input[3], item);
				} else {
					
				}
			default:
				
		}
		
	}
	
	private static boolean isDate(String checkInput) {
		System.out.println("isDate");
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
		System.out.println("isTime");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(checkInput);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	private static void setFloating(Interpreter item){
		System.out.println("setFloating");
		item.setType(TaskType.FLOATING);
		item.setIsDueDate(false);
		item.setIsStartDate(false);
		Date date = null;
		item.setStartDate(date);
		item.setDueDate(date);
	}
	
	private static void setDeadline(String dueDate, String endTime, Interpreter item){
		System.out.println("setDeadline");
		item.setType(TaskType.DEADLINE);
		item.setIsStartDate(false);
		Date startDate = null;
		Date resultDueDate = setDate(dueDate, endTime);
		item.setStartDate(startDate);
		item.setDueDate(resultDueDate);
	}
	
	private static void setAppointment(String startDate, String startTime, 
			String dueDate, String endTime, Interpreter item){
		System.out.println("setAppointment");
		Date resultStartDate = setDate(startDate, startTime);
		Date resultDueDate = setDate(dueDate, endTime);
	
			item.setType(TaskType.APPOINTMENT);
			item.setStartDate(resultStartDate);
			item.setDueDate(resultDueDate);
	}
	
	private static Date setDate(String inputDate, String inputTime) {
		System.out.println("setDate");
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
