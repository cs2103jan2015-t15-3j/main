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
	Interpreter floating, deadline, appt, deadline2;

	@Test
	public void test() throws ParseException {
		// --------------- Add Floating task ---------------------
		floating = new Interpreter();
		floating.setTaskName("ghc");
		floating.setType(TaskType.FLOATING);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = "07/06/2013";
		Date date = formatter.parse(dateInString);
		floating.setRemarks("YO");
		UnitTest.addTask(floating, mem.getBuffer(), mem.numberGenerator());

		// ------------ Add Deadline task -------------------
		deadline = new Interpreter();
		deadline.setTaskName("hcp");
		deadline.setType(TaskType.DEADLINE);
		dateInString = "09/06/2013";
		date = formatter.parse(dateInString);
		deadline.setDueDate(date);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, mem.getBuffer(), mem.numberGenerator());

		// ------------- Add appointment task -----------------
		appt = new Interpreter();
		appt.setTaskName("CPH");
		appt.setType(TaskType.APPOINTMENT);
		dateInString = "10/06/2013";
		String dateInString2 = "12/06/2013";
		date = formatter.parse(dateInString);
		Date date1 = formatter.parse(dateInString2);
		appt.setStartDate(date1);
		appt.setDueDate(date);
		appt.setRemarks("This is tedious!");
		UnitTest.addTask(appt, mem.getBuffer(), mem.numberGenerator());

		// ---------------- Add another deadline ----------------
		deadline2 = new Interpreter();
		deadline2.setTaskName("GOOOO");
		deadline2.setType(TaskType.DEADLINE);
		dateInString = "20/06/2013";
		String dateInString3 = "12/06/2013";
		date = formatter.parse(dateInString);
		Date date2 = formatter.parse(dateInString3);
		deadline2.setStartDate(date2);
		deadline2.setDueDate(date);
		deadline2.setRemarks("This is tedious!");
		UnitTest.addTask(deadline2, mem.getBuffer(), mem.numberGenerator());

		// ------------- Check if size is correct --------
		assertEquals(4, mem.getBuffer().size());

		// assertFalse(buffer.get(0).getTaskName().equals("CS2106"));
		// -------------- Delete --------------------------
		System.out.println(mem.getBuffer());
		UnitTest.deleteTask(2, mem.getBuffer());

		// ------------ Sort -------------------------------
		UnitTest.sort(mem);
		System.out.println(mem.getBuffer());
		System.out.println(mem.getTempBuffer());

		UnitTest.determineSearch("3", mem);
		System.out.println(mem.getTempBuffer());

		appt.setKey("TASKNAME");
		appt.setTaskName("HELLO");
		UnitTest.determineAmend(appt, mem);
		System.out.println(mem.getBuffer());
		/*
		 * System.out.println("\n");
		 * System.out.println(buffer.get(0).toString());
		 * System.out.println(buffer.get(1).toString());
		 * 
		 * //UnitTest.clearTask(deadline, mem.getBuffer()); //assertEquals(0,
		 * buffer.size()); }
		 */
	}

}
