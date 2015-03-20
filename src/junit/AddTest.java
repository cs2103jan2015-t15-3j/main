package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logic.Enumerator.TaskType;
import logic.Task;
import logic.UnitTest;
import parser.Interpreter;
import logic.Memory;

public class AddTest {

	ArrayList<Task> buffer = new ArrayList<Task>();
	
	Memory mem = new Memory();

	Interpreter floating, deadline, appt, test;

	@Test
	public void test() throws ParseException {

		// Add Floating 
		floating = new Interpreter();
		floating.setTaskName("CS2103");
		floating.setType(TaskType.FLOATING);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = "07/06/2013";
		Date date = formatter.parse(dateInString);
		floating.setRemarks("YO");
		UnitTest.addTask(floating, mem.getBuffer(), mem.numberGenerator());

		// Add Deadline
		deadline = new Interpreter();
		deadline.setTaskName("CS2106");
		deadline.setType(TaskType.DEADLINE);
		dateInString = "09/06/2013";
		date = formatter.parse(dateInString);
		deadline.setDueDate(date);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, mem.getBuffer(), mem.numberGenerator());

		// Add appointment
		appt = new Interpreter();
		appt.setTaskName("CS2106");
		appt.setType(TaskType.APPOINTMENT);
		dateInString = "10/06/2013";
		String dateInString2 = "12/06/2013";
		date = formatter.parse(dateInString);
		Date date1 = formatter.parse(dateInString2);
		appt.setStartDate(date1);
		appt.setDueDate(date);
		appt.setRemarks("This is tedious!");
		UnitTest.addTask(appt, mem.getBuffer(), mem.numberGenerator());

		buffer = mem.getBuffer();
		assertEquals(3, buffer.size());

		//assertFalse(buffer.get(0).getTaskName().equals("CS2106"));

		//UnitTest.deleteTask(2, mem.getBuffer());
		UnitTest.sort(mem.getBuffer(), mem);
		System.out.println(mem.getBuffer());
		System.out.println(mem.getTempBuffer());

		/*
		assertEquals(2, buffer.size());
		
		appt.setKey("TASKNAME");
		appt.setTaskName("HELLO");
		appt.setType(TaskType.APPOINTMENT);

		dateInString = "10/06/2013";
		String dateInString3 = "12/06/2013";
		date = formatter.parse(dateInString);
		Date date2 = formatter.parse(dateInString3);
		appt.setStartDate(date2);
		appt.setDueDate(date);
		appt.setRemarks("This is tedious!");
		
		UnitTest.determineAmend(appt, mem);
		
		System.out.println("\n");
		System.out.println(buffer.get(0).toString());
		System.out.println(buffer.get(1).toString());
		
		//UnitTest.clearTask(deadline, mem.getBuffer());
		//assertEquals(0, buffer.size());
	}*/
	}

}
