package parser;
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
 * 
 * 
 */

public class ParserDateAndTimeChecker {
	
	public int inputArrayLength = 10;
	
	public void checkBoth() {
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
			
		
		
	}
	
}
