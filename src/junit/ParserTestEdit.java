package junit;

import static org.junit.Assert.*;

import java.text.ParseException;

import logic.Enumerator.TaskType;

import org.junit.Test;

import parser.Interpreter;
import parser.ProParser;
import parser.Interpreter.CommandType;

public class ParserTestEdit {
	ProParser Pro = new ProParser();
	Interpreter item, item2;
	
	@Test
	public void test() throws ParseException {
		String input = "add DEFAULT ENTRY FOR TESTING PURPOSES [12/12/12 12:12] <ADDITIONAL REMARKS IF NECESSARY>";
		item = Pro.parse(input);
		
				String input0 = "edit 1 checking no date no time format <remarks here>";
		item = Pro.parse(input0);
		assertEquals("Command: ", CommandType.AMEND, item.getCommand());
		assertEquals("ID: ", 1, item.getTaskID());
		assertEquals("TaskType: ", TaskType.FLOATING, item.getType());
		assertEquals("Start Date does exist: ", false, item.getIsStartDate());
		assertEquals("Due Date does not exist: ", false, item.getIsDueDate());
		assertEquals("StartDateString: ", "null", item.getStartDateString());
		assertEquals("DueDateString: ", "null", item.getDueDateString());
		assertEquals("Remarks exist: ", true, item.getIsRemarks());
		assertEquals("Remarks: ", "remarks here", item.getRemarks());
		
		String input1 = "edit 1 checking date without time format [11/11/11] <remarks here>";
		item = Pro.parse(input1);
		assertEquals("Command: ", CommandType.AMEND, item.getCommand());
		assertEquals("ID: ", 1, item.getTaskID());
		assertEquals("TaskType: ", TaskType.DEADLINE, item.getType());
		assertEquals("Start Date does exist: ", false, item.getIsStartDate());
		assertEquals("Due Date exists: ", true, item.getIsDueDate());
		assertEquals("StartDateString: ", "null", item.getStartDateString());
		assertEquals("DueDateString: ", "11/11/11 23:59", item.getDueDateString());
		assertEquals("TaskName: ", "checking date without time format", item.getTaskName());
		assertEquals("Remarks exist: ", true, item.getIsRemarks());
		assertEquals("Remarks: ", "remarks here", item.getRemarks());
		
		String input2 = "e 1 checking double date without time format 11/11/11 12:12 [11/11/11 12:12]<remarks>";
		item = Pro.parse(input2);
		assertEquals("Command: ", CommandType.AMEND, item.getCommand());
		assertEquals("ID: ", 1, item.getTaskID());
		assertEquals("TaskType: ", TaskType.DEADLINE, item.getType());
		assertEquals("Start Date exists: ", false, item.getIsStartDate());
		assertEquals("Due Date exists: ", true, item.getIsDueDate());
		assertEquals("StartDateString: ", "null", item.getStartDateString());
		assertEquals("DueDateString: ", "11/11/11 12:12", item.getDueDateString());
		assertEquals("TaskName: ", "checking double date without time format 11/11/11 12:12", item.getTaskName());
		assertEquals("Remarks exist: ", true, item.getIsRemarks());
		assertEquals("Remarks: ", "remarks", item.getRemarks());
		
		System.out.println("INPUT3<");
		String input3 = "EdiT 1 checking double date without time format 11/11/11 12/12/12[11/11/11 12/12/12]<remarks>";
		item = Pro.parse(input3);
		assertEquals("Command: ", CommandType.AMEND, item.getCommand());
		assertEquals("ID: ", 1, item.getTaskID());
		assertEquals("TaskType: ", TaskType.APPOINTMENT, item.getType());
		assertEquals("Start Date exists: ", true, item.getIsStartDate());
		assertEquals("Due Date exists: ", true, item.getIsDueDate());
		assertEquals("StartDateString: ", "11/11/11 23:59", item.getStartDateString());
		assertEquals("DueDateString: ", "12/12/12 23:59", item.getDueDateString());
		assertEquals("TaskName: ", "checking double date without time format 11/11/11 12/12/12", item.getTaskName());
		assertEquals("Remarks exist: ", true, item.getIsRemarks());
		assertEquals("Remarks: ", "remarks", item.getRemarks());
		System.out.println(">INPUT3");
		
		String input4 = "edit 1 checking double date, start date without and due date with time format 11/11/11 12/12/12 12:12 [11/11/11 12/12/12 12:12]<Testing>";
		item = Pro.parse(input4);
		assertEquals("Command: ", CommandType.AMEND, item.getCommand());
		assertEquals("ID: ", 1, item.getTaskID());
		assertEquals("TaskType: ", TaskType.APPOINTMENT, item.getType());
		assertEquals("Start Date exists: ", true, item.getIsStartDate());
		assertEquals("Due Date exists: ", true, item.getIsDueDate());
		assertEquals("StartDateString: ", "11/11/11 23:59", item.getStartDateString());
		assertEquals("DueDateString: ", "12/12/12 12:12", item.getDueDateString());
		assertEquals("TaskName: ", "checking double date, start date without and due date with time format 11/11/11 12/12/12 12:12", item.getTaskName());
		assertEquals("Remarks exist: ", true, item.getIsRemarks());
		assertEquals("Remarks: ", "Testing", item.getRemarks());
		
		
		String input5 = "e 1 checking double date, start date with and due date without time format 11/11/11 11:11 12/12/12 [11/11/11 11:11 12/12/12]<YAYYYYYY>";
		item = Pro.parse(input5);
		assertEquals("Command: ", CommandType.AMEND, item.getCommand());
		assertEquals("ID: ", 1, item.getTaskID());
		assertEquals("TaskType: ", TaskType.APPOINTMENT, item.getType());
		assertEquals("Start Date exists: ", true, item.getIsStartDate());
		assertEquals("Due Date exists: ", true, item.getIsDueDate());
		assertEquals("StartDateString: ", "11/11/11 11:11", item.getStartDateString());
		assertEquals("DueDateString: ", "12/12/12 23:59", item.getDueDateString());
		assertEquals("TaskName: ", "checking double date, start date with and due date without time format 11/11/11 11:11 12/12/12", item.getTaskName());
		assertEquals("Remarks exist: ", true, item.getIsRemarks());
		assertEquals("Remarks: ", "YAYYYYYY", item.getRemarks());
		
		// Check if Appointment
		String input6 = "e 1 checking double date with double time format [11/11/11 11:11 12/12/12 12:12] <insert remarks here>";
		item = Pro.parse(input6);
		assertEquals("Command: ", CommandType.AMEND, item.getCommand());
		assertEquals("ID: ", 1, item.getTaskID());
		assertEquals("TaskType: ", TaskType.APPOINTMENT, item.getType());
		assertEquals("StartDateString: ", "11/11/11 11:11", item.getStartDateString());
		assertEquals("DueDateString: ", "12/12/12 12:12", item.getDueDateString());
		assertEquals("TaskName: ", "checking double date with double time format", item.getTaskName());
		assertEquals("Remarks exist: ", true, item.getIsRemarks());
		assertEquals("Remarks: ", "insert remarks here", item.getRemarks());
		
		
		String input7 = "e1 appt with wrong order of dates [12/12/12 11:11 11/11/11 12:12] <insert remarks here>";
		item2 = Pro.parse(input7);
		boolean result = false;
		if(item2.getIsError()) {
			result = true;
		}
		assertEquals("Invalid Command Format", true, result);
		System.out.println("Exception thrown due to Invalid Command Format");
		System.out.println();
		
		
		String input8 = "edit1 appt with wrong order of dates [12/12/12 11:11 12/12/12 10:10] <insert remarks here>";
		item2 = Pro.parse(input8);
		boolean result2 = false;
		if(item2.getIsError()) {
			result2 = true;
		}
		assertEquals("Invalid Command Format", true, result2);
		System.out.println("Exception thrown due to Invalid Command Format");
		System.out.println();
		
		
		String input9 = "EDIT appt with wrong order of dates [12/12/12 12:12 10/10/10 10:10] <insert remarks here>";
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
