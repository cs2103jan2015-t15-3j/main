package junit;

import java.text.ParseException;

import org.junit.Test;

import parser.ProParser;
import parser.Interpreter;
import static org.junit.Assert.*;

public class ParserTestDateTime {
	
	ProParser Pro = new ProParser();
	Interpreter item; 
	@Test
	public void test() throws ParseException {
		
		// Check if Floating
		// This is a boundary case for Floating
		String input0 = "add checking no date no time format <remarks here>";
		item = Pro.parse(input0);
		System.out.println("TaskType: " + item.getType());
		System.out.println("TaskName: " + item.getTaskName());
		System.out.println("Remarks: " + item.getRemarks());
		System.out.println();
		
		// Check if Deadline
		// This is a boundary case for Deadline
		String input1 = "add checking date without[] [time] format [11/11/11] <remarks here>";
		item = Pro.parse(input1);
		System.out.println("TaskType: " + item.getType());
		System.out.println("DueDateString: " + item.getDueDateString());
		System.out.println("TaskName: " + item.getTaskName());
		System.out.println("Remarks: " + item.getRemarks());
		System.out.println();
		
		// Check if Deadline
		String input2 = "add checking single date with time format [11/11/11 11:11] <hello>";
		item = Pro.parse(input2);
		System.out.println("TaskType: " + item.getType());
		System.out.println("DueDateString: " + item.getDueDateString());
		System.out.println("TaskName: " + item.getTaskName());
		System.out.println("Remarks: " + item.getRemarks());
		System.out.println();
		
		// Check if Appointment
		String input3 = "add checking double date without time format 11/11/11 12/12/12 [11/11/11 12/12/12]<remarks>";
		item = Pro.parse(input3);
		System.out.println("TaskType: " + item.getType());
		System.out.println("StartDateString: " + item.getStartDateString());
		System.out.println("DueDateString: " + item.getDueDateString());
		System.out.println("TaskName: " + item.getTaskName());
		System.out.println("Remarks: " + item.getRemarks());
		System.out.println();
		
		// Check if Appointment
		String input4 = "add checking double date with double time format [11/11/11 11:11 12/12/12 12:12] <insert remarks here>";
		item = Pro.parse(input4);
		System.out.println("TaskType: " + item.getType());
		System.out.println("StartDateString: " + item.getStartDateString());
		System.out.println("DueDateString: " + item.getDueDateString());
		System.out.println("TaskName: " + item.getTaskName());
		System.out.println("Remarks: " + item.getRemarks());
		System.out.println();
	}

}
