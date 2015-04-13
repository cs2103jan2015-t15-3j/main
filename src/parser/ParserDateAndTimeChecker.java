package parser;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import logic.Enumerator.TaskType;

/*
 *	There are several possibilities for adding for the inputs of date and time 
 *
 *	Floating:
 *	add taskName 
 *
 *	Deadline: 
 *	add taskName [date]
 *	add taskName [date time]
 *
 *	Appointment:
 *	add taskName [date date] 
 *	add taskName [date time date] 
 *	add taskName [date date time] 
 *	add taskName [date time date time]
 *
 */

public class ParserDateAndTimeChecker {
	
	// If no time is given but a date is given,
	// set default time to 23:59
	private static String DEFAULT_TIME = "23:59";
	
	public static boolean isDateAndTime(Interpreter item, String[] input, int length) throws ParseException {
		if(input == null) {
			setFloating(item);
			return true;
		}
		
		switch(length) {				
			case 0:
				setFloating(item);
				return true;
			case 1:
				if(isDate(input[0])) {
					setDeadline(input[0], DEFAULT_TIME, item); 
					return true;
				} else {
					return false;
				}
			case 2:
				if(isDate(input[0]) && isTime(input[1])) {
					setDeadline(input[0], input[1], item);
					return true;
				} else if(isDate(input[0]) && isDate(input[1])) {
					setAppointment(input[0], DEFAULT_TIME, input[1], DEFAULT_TIME, item);
					return true;
				} else {
					return false;
				}
			case 3:
				if(isDate(input[0]) && isTime(input[1]) && isDate(input[2])) {
					setAppointment(input[0], input[1], input[2], DEFAULT_TIME, item);
					return true;
				} else if(isDate(input[0]) && isDate(input[1]) && isTime(input[2])) {
					setAppointment(input[0], DEFAULT_TIME, input[1], input[2], item);
					return true;
				} else {
					return false;
				}
			
			case 4: 
				if(isDate(input[0]) && isTime(input[1]) && isDate(input[2]) && isTime(input[3])) {
					setAppointment(input[0], input[1], input[2], input[3], item);
					return true;
				} else {
					return false;
				}
			default:
				return false;
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
	
	private static void setFloating(Interpreter item) {
		item.setType(TaskType.FLOATING);
		item.setIsDueDate(false);
		item.setIsStartDate(false);
		Date date = null;
		item.setStartDate(date);
		item.setDueDate(date);
	}
	
	private static void setDeadline(String dueDate, String endTime, Interpreter item) {
		item.setType(TaskType.DEADLINE);
		item.setIsStartDate(false);
		Date startDate = null;
		Date resultDueDate = setDate(item, dueDate, endTime);
		System.out.println(dueDate);
		System.out.println(endTime);
		item.setIsStartDate(false);
		item.setIsDueDate(true);
		item.setStartDate(startDate);
		item.setDueDate(resultDueDate);
	}
	
	private static void setAppointment(String startDate, String startTime, 
			String dueDate, String endTime, Interpreter item){
	
		Date resultStartDate = setDate(item, startDate, startTime);
		Date resultDueDate = setDate(item, dueDate, endTime);
		
		int compareResult = compareDates(resultStartDate, resultDueDate);
		
		try {	
			if(compareResult > 0) {
				throw new ParserException();
			} else {
				item.setType(TaskType.APPOINTMENT);
				item.setIsStartDate(true);
				item.setIsDueDate(true);
				item.setStartDate(resultStartDate);
				item.setDueDate(resultDueDate);
			}	
		} catch (ParserException pe){
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_DATE_TIME_FORMAT);
		}
	}
	
	// Returns:
	// value == 0 if this Date is equal to the argument Date 
	// value < 0 if this Date is before the Date argument
	// value > 0 if this Date is after the Date argument
	private static int compareDates(Date startDate, Date dueDate) {
		return startDate.compareTo(dueDate);
	}
	
	private static Date setDate(Interpreter item, String inputDate, String inputTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		String inputDateAndTime = inputDate + " " + inputTime;
		sdf.setLenient(true);
		try {
			Date date = sdf.parse(inputDateAndTime);
			return date;
		} catch (ParseException e) {
			item.setIsError(true);
			item.setFeedbackMsg(ParserMessage.INVALID_DATE_TIME_FORMAT);
			return null;
		}
	}	
}
