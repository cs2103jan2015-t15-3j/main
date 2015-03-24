package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class ParserDateAndTimeChecker {
	
	// Variables stand for the last, secondLast, thirdLast and fourthLast 
	// elements of the input array respectively
	private static String last, secondLast, thirdLast, fourthLast; 
	
	public static void checkDateAndTime(Interpreter item, String[] input, int length) throws ParseException {
		
		for(int i=0; i<1; i++) {
			
			if(length < 2) {
				System.out.print("Error. Please input again");
			} else if(length == 2) {
				// set floating
				item.setType(TaskType.FLOATING);
				item.setIsDueDate(false);
				item.setIsStartDate(false);
				System.out.println("!isDateValid --> Floating");
				
			} else if(length > 2 && length < 4) {
				// check last
				// if time--> error
				// if !time, if date --> deadline
				// if !time, !date --> floating
				last = input[length - 1];
				//!d,!t
				if(!isTime(last) && !isDate(last)) {
					setFloating(item);
				//d && !t
				} else if(!isTime(last) && isDate(last)) {
					setDeadline(last, "11:59 pm", item);
				} else {
					System.out.print("Error. Please input again");
				}
				
			} else if(length > 2 && length < 5) {
				// check last and 2ndlast
				// if time, if !date --> error
				// if time and if time --> error
				// if !time and !date --> floating
				// if time and if date --> deadline
				// if date and if date --> appointment
				last = input[length - 1];
				secondLast = input[length - 2];
				//!d,!t
				if(!isTime(last) && !isDate(last)) {
					setFloating(item);
				//d && !t
				} else if(!isTime(last) && isDate(last)) {
					setDeadline(last, "11:59 pm", item);
				//d && t
				} else if(isTime(last) && isDate(secondLast)) {
					setDeadline(secondLast, last, item);
				//d && d
				} else if(isDate(last) && isDate(secondLast)){
					setAppointment(secondLast, "11:59 pm", last, "11:59 pm", item);
				} else {
					System.out.print("Error. Please input again");
				}
				
			} else if(length > 2 && length < 6) {
				// check 3rdlast, 2ndlast, last
				// ttt, dtt, tdt --> error
				// !t!d --> floating
				// dtd, ddt, nil dd --> appointment
				// nil dt, nil nil d--> deadline
							
				last = input[length - 1];
				secondLast = input[length - 2];
				thirdLast = input[length - 3];
				//!t,!d
				if(!isTime(last) && !isDate(last)) {
					setFloating(item);
				//d && !t
				} else if(!isTime(last) && isDate(last)) {
					setDeadline(last, "11:59 pm", item);
				//0dt
				} else if(isTime(last) && isDate(secondLast) && !isDate(thirdLast)) {
					setDeadline(secondLast, last, item);
				//0dd
				} else if(isDate(last) && isDate(secondLast)) {
					setAppointment(secondLast, "11:59 pm", last, "11:59 pm", item);
				//ddt
				} else if(isTime(last) && isDate(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, "11:59 pm", secondLast, last, item);
				//dtd
				} else if (isDate(last) && isTime(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, secondLast, last, "11:59 pm", item);
				} else {
					System.out.print("Error. Please input again");
				}
				
				
			} else {
				// else if(length >= 6) {	
				// check last 4 entries
				// nilnilnilnil --> floating
				// nilnil dt, nilnilnil d --> deadline
				// nilnil dd, nildtd, nilddt, dtdt --> appointment
				
				last = input[length - 1];
				secondLast = input[length - 2];
				thirdLast = input[length - 3];
				fourthLast = input[length - 4];
				
				//!t, !d
				if(!isTime(last) && !isDate(last)) {
					setFloating(item);
				//000d
				} else if(!isTime(last) && isDate(last) && !isDate(thirdLast) && !isTime(thirdLast)) {
					setDeadline(last, "11:59 pm", item);
				//00dt
				} else if(isTime(last) && isDate(secondLast) && !isDate(thirdLast) && !isTime(thirdLast)) {
					setDeadline(secondLast, last, item);
				//00dd
				} else if(isDate(last) && isDate(secondLast)) {
					setAppointment(secondLast, "11:59 pm", last, "11:59 pm", item);
				//0ddt
				} else if(isTime(last) && isDate(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, "11:59 pm", secondLast, last, item);
				//0dtd
				} else if (isDate(last) && isTime(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, secondLast, last, "11:59 pm", item);
				//dtdt
				} else if (isDate(last) && isTime(secondLast) && isDate(thirdLast) && isTime(fourthLast)) {
					setAppointment(fourthLast, thirdLast, secondLast, last, item);
				} else {
					System.out.print("Error. Please input again");
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
	
	public static void setFloating(Interpreter item){
		item.setType(TaskType.FLOATING);
		item.setIsDueDate(false);
		item.setIsStartDate(false);
		Date date = null;
		item.setStartDate(date);
		item.setDueDate(date);
		System.out.println("!isDateValid --> Floating");
	}
	
	public static void setDeadline(String dueDate, String endTime, Interpreter item){
		item.setType(TaskType.DEADLINE);
		item.setIsStartDate(false);
		Date startDate = null;
		Date resultDueDate = setDate(dueDate, endTime);
		item.setStartDate(startDate);
		item.setDueDate(resultDueDate);
		System.out.println("isDateValid --> Deadline");
	}
	
	public static void setAppointment(String startDate, String startTime, String dueDate, String endTime, Interpreter item){
		item.setType(TaskType.APPOINTMENT);
		Date resultStartDate = setDate(startDate, endTime);
		Date resultDueDate = setDate(dueDate, endTime);
		item.setStartDate(resultStartDate);
		item.setDueDate(resultDueDate);
		System.out.println("len>4 with start date --> Appointment");
	}
	
	public static Date setDate(String inputDate, String inputTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
		String inputDateAndTime = inputDate + " " + inputTime;
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(inputDateAndTime);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
	
}
