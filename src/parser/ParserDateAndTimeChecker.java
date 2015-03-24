package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import logic.Compare;
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
			if(length < 2) {
				System.out.println("l<2. Error. Please input again");
			} else if(length == 2) {
				// set floating
				setFloating(item,length);			
				
			} else if(length > 2 && length < 4) {
				System.out.println("len: "+length);

				// check last
				// if time--> error
				// if !time, if date --> deadline
				// if !time, !date --> floating
				last = input[length - 1];
				//!d,!t
				if(!isTime(last) && !isDate(last)) {
					setFloating(item,length);
				//d && !t
				} else if(!isTime(last) && isDate(last)) {
					setDeadline(last, "23:59", item);
					item.setLastIndexTaskName(length - 2);
				} else {
					System.out.println("2<l<4. Error. Please input again");
				}
				
			} else if(length > 2 && length < 5) {
				System.out.println("len: "+length);

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
					setFloating(item,length);
				//d && !t
				} else if(!isTime(last) && isDate(last)) {
					setDeadline(last, "23:59", item);
					item.setLastIndexTaskName(length - 2);
				//d && t
				} else if(isTime(last) && isDate(secondLast)) {
					setDeadline(secondLast, last, item);
					item.setLastIndexTaskName(length - 3);
				//d && d
				} else if(isDate(last) && isDate(secondLast)){
					setAppointment(secondLast, "23:59", last, "23:59", item);
					item.setLastIndexTaskName(length - 3);
				} else {
					System.out.println("2<l<5 Error. Please input again");
				}
				
			} else if(length > 2 && length < 6) {
				System.out.println("len: "+length);

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
					setFloating(item,length);
				//d && !t
				} else if(!isTime(last) && isDate(last)) {
					setDeadline(last, "23:59", item);
					item.setLastIndexTaskName(length - 2);
				//0dt
				} else if(isTime(last) && isDate(secondLast) && !isDate(thirdLast)) {
					setDeadline(secondLast, last, item);
					item.setLastIndexTaskName(length - 3);
				//0dd
				} else if(isDate(last) && isDate(secondLast)) {
					setAppointment(secondLast, "23:59", last, "23:59", item);
					item.setLastIndexTaskName(length - 3);
				//ddt
				} else if(isTime(last) && isDate(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, "23:59", secondLast, last, item);
					item.setLastIndexTaskName(length - 4);
				//dtd
				} else if (isDate(last) && isTime(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, secondLast, last, "23:59", item);
					item.setLastIndexTaskName(length - 4);
				} else {
					System.out.println("2<l<6. Error. Please input again");
				}
				
				
			} else {
				System.out.println("len: "+length);

				// else if(length >= 6) {	
				// check last 4 entries
				// nilnilnilnil --> floating
				// nilnil dt, nilnilnil d --> deadline
				// nilnil dd, nildtd, nilddt, dtdt --> appointment
				
				last = input[length - 1];
				secondLast = input[length - 2];
				thirdLast = input[length - 3];
				fourthLast = input[length - 4];
				
				System.out.println("last: "+last);
				System.out.println("secondLast: "+secondLast);
				System.out.println("thirdLast: "+thirdLast);
				System.out.println("fourthLast: "+fourthLast);
				
				//!t, !d
				if(!isTime(last) && !isDate(last)) {
					setFloating(item,length);
				//000d
				} else if(!isTime(last) && isDate(last) && !isDate(secondLast) && !isTime(secondLast)) {
					setDeadline(last, "23:59", item);
					item.setLastIndexTaskName(length - 2);
				//00dt
				} else if(isTime(last) && isDate(secondLast) && !isDate(thirdLast) && !isTime(thirdLast)) {
					setDeadline(secondLast, last, item);
					item.setLastIndexTaskName(length - 3);
				//00dd
				} else if(isDate(last) && isDate(secondLast)) {
					setAppointment(secondLast, "23:59", last, "23:59", item);
					item.setLastIndexTaskName(length - 3);
				//0ddt
				} else if(isTime(last) && isDate(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, "23:59", secondLast, last, item);
					item.setLastIndexTaskName(length - 4);
				//0dtd
				} else if (isDate(last) && isTime(secondLast) && isDate(thirdLast)) {
					setAppointment(thirdLast, secondLast, last, "23:59", item);
					item.setLastIndexTaskName(length - 4);
				//dtdt
				} else if (isTime(last) && isDate(secondLast) && isTime(thirdLast) && isDate(fourthLast)) {
					setAppointment(fourthLast, thirdLast, secondLast, last, item);
					item.setLastIndexTaskName(length - 5);
				} else {
					System.out.println("l>=6. Error. Please input again");
				}
			}
	}
	
	public static boolean isDate(String checkInput) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(checkInput);
			System.out.println("isDate: "+checkInput);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}	
	
	public static boolean isTime(String checkInput) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(checkInput);
			System.out.println("isTime: "+checkInput);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static void setFloating(Interpreter item, int length){
		item.setType(TaskType.FLOATING);
		item.setIsDueDate(false);
		item.setIsStartDate(false);
		Date date = null;
		item.setStartDate(date);
		item.setDueDate(date);
		item.setLastIndexTaskName(length - 1);
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
	
	public static void setAppointment(String startDate, String startTime, 
			String dueDate, String endTime, Interpreter item){
		
		Date resultStartDate = setDate(startDate, endTime);
		Date resultDueDate = setDate(dueDate, endTime);
		
		item.setType(TaskType.APPOINTMENT);
		item.setStartDate(resultStartDate);
		item.setDueDate(resultDueDate);
		System.out.println("len>4 with start date --> Appointment");
	}
	
	public static Date setDate(String inputDate, String inputTime) {
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
