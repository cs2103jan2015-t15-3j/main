package junit;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import logic.Enumerator.TaskType;

import org.junit.Test;

import parser.Interpreter.CommandType;
import parser.ProParser;
import parser.Interpreter;

public class ParserTestDateTime {
	
	ProParser Pro = new ProParser();
	Interpreter item, item2; 
	
	@Test
	public void test() throws ParseException {
		
		// Check if Floating
		// This is a boundary case for Floating
		String input0 = "add checking no date no time format <remarks here>";
		item = Pro.parse(input0);
		assertEquals("Command: ", CommandType.ADD, item.getCommand());
		assertEquals("TaskType: ", TaskType.FLOATING, item.getType());
		assertEquals("StartDateString: ", "null", item.getStartDateString());
		assertEquals("DueDateString: ", "null", item.getDueDateString());
		assertEquals("TaskName: ", "checking no date no time format", item.getTaskName());
		assertEquals("Remarks: ", "remarks here", item.getRemarks());
		
		// Check if Deadline
		// This is a boundary case for Deadline
		String input1 = "add checking date without time format [11/11/11] <remarks here>";
		item = Pro.parse(input1);
		assertEquals("Command: ", CommandType.ADD, item.getCommand());
		assertEquals("TaskType: ", TaskType.DEADLINE, item.getType());
		assertEquals("StartDateString: ", "null", item.getStartDateString());
		assertEquals("DueDateString: ", "11/11/11 23:59", item.getDueDateString());
		assertEquals("TaskName: ", "checking date without time format", item.getTaskName());
		assertEquals("Remarks: ", "remarks here", item.getRemarks());
		
		// Check if Deadline
		String input2 = "add checking single date with time format [11/11/11 11:11] <hello>";
		item = Pro.parse(input2);
		assertEquals("Command: ", CommandType.ADD, item.getCommand());
		assertEquals("TaskType: ", TaskType.DEADLINE, item.getType());
		assertEquals("StartDateString: ", "null", item.getStartDateString());
		assertEquals("DueDateString: ", "11/11/11 11:11", item.getDueDateString());
		assertEquals("TaskName: ", "checking single date with time format", item.getTaskName());
		assertEquals("Remarks: ", "hello", item.getRemarks());
		
		// Check if Appointment
		String input3 = "add checking double date without time format 11/11/11 12/12/12 [11/11/11 12/12/12]<remarks>";
		item = Pro.parse(input3);
		assertEquals("Command: ", CommandType.ADD, item.getCommand());
		assertEquals("TaskType: ", TaskType.APPOINTMENT, item.getType());
		assertEquals("StartDateString: ", "11/11/11 23:59", item.getStartDateString());
		assertEquals("DueDateString: ", "12/12/12 23:59", item.getDueDateString());
		assertEquals("TaskName: ", "checking double date without time format 11/11/11 12/12/12", item.getTaskName());
		assertEquals("Remarks: ", "remarks", item.getRemarks());
		
		
		String input4 = "add checking double date, start date without and due date with time format 11/11/11 12/12/12 12:12 [11/11/11 12/12/12 12:12]<Testing>";
		item = Pro.parse(input4);
		assertEquals("Command: ", CommandType.ADD, item.getCommand());
		assertEquals("TaskType: ", TaskType.APPOINTMENT, item.getType());
		assertEquals("StartDateString: ", "11/11/11 23:59", item.getStartDateString());
		assertEquals("DueDateString: ", "12/12/12 12:12", item.getDueDateString());
		assertEquals("TaskName: ", "checking double date, start date without and due date with time format 11/11/11 12/12/12 12:12", item.getTaskName());
		assertEquals("Remarks: ", "Testing", item.getRemarks());
		
		
		String input5 = "add checking double date, start date with and due date without time format 11/11/11 11:11 12/12/12[11/11/11 11:11 12/12/12]<YAYYYYYY>";
		item = Pro.parse(input5);
		assertEquals("Command: ", CommandType.ADD, item.getCommand());
		assertEquals("TaskType: ", TaskType.APPOINTMENT, item.getType());
		assertEquals("StartDateString: ", "11/11/11 11:11", item.getStartDateString());
		assertEquals("DueDateString: ", "12/12/12 23:59", item.getDueDateString());
		assertEquals("TaskName: ", "checking double date, start date with and due date without time format 11/11/11 11:11 12/12/12", item.getTaskName());
		assertEquals("Remarks: ", "YAYYYYYY", item.getRemarks());
		
		// Check if Appointment
		String input6 = "add checking double date with double time format [11/11/11 11:11 12/12/12 12:12] <insert remarks here>";
		item = Pro.parse(input6);
		assertEquals("Command: ", CommandType.ADD, item.getCommand());
		assertEquals("TaskType: ", TaskType.APPOINTMENT, item.getType());
		assertEquals("StartDateString: ", "11/11/11 11:11", item.getStartDateString());
		assertEquals("DueDateString: ", "12/12/12 12:12", item.getDueDateString());
		assertEquals("TaskName: ", "checking double date with double time format", item.getTaskName());
		assertEquals("Remarks: ", "insert remarks here", item.getRemarks());
		
		
		String input7 = "add appt with wrong order of dates [12/12/12 11:11 11/11/11 12:12] <insert remarks here>";
		item2 = Pro.parse(input7);
		boolean result = false;
		if(item2.getIsError()) {
			result = true;
		}
		assertEquals("Wrong order of dates gives an exception", true, result);
		System.out.println("Exception thrown due to wrong order of dates");
		System.out.println();
		
		
		String input8 = "add appt with wrong order of dates [12/12/12 11:11 12/12/12 10:10] <insert remarks here>";
		item2 = Pro.parse(input8);
		boolean result2 = false;
		if(item2.getIsError()) {
			result2 = true;
		}
		assertEquals("Wrong order of dates gives an exception", true, result2);
		System.out.println("Exception thrown due to wrong order of times");
		System.out.println();
		
		
		String input9 = "add appt with wrong order of dates [12/12/12 12:12 10/10/10 10:10] <insert remarks here>";
		item2 = Pro.parse(input9);
		boolean result3 = false;
		if(item2.getIsError()) {
			result3 = true;
		}
		assertEquals("Wrong order of dates gives an exception", true, result3);
		System.out.println("Exception thrown due to wrong order of date and times");
		System.out.println();

	}
}
