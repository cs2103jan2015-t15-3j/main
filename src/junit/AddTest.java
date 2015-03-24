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
import logic.Repository;

public class AddTest {

	ArrayList<Task> buffer = new ArrayList<Task>();
	Repository mem = new Repository();
	Interpreter floating, deadline, appt, deadline2;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void test() throws ParseException {
		String dateInString = "07/06/2013";
		Date date = formatter.parse(dateInString);

		// --------------- Add Floating task ---------------------
		floating = new Interpreter();
		floating.setTaskName("ghc");
		floating.setType(TaskType.FLOATING);
		floating.setRemarks("YO");
		UnitTest.addTask(floating, mem.getBuffer(), mem.numberGenerator());
		System.out.println(mem.getRedoAction().size());

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
		date = formatter.parse(dateInString);
		String dateInString2 = "12/06/2013";
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
		date = formatter.parse(dateInString);
		deadline2.setDueDate(date);
		deadline2.setRemarks("This is tedious!");
		UnitTest.addTask(deadline2, mem.getBuffer(), mem.numberGenerator());

		// ------------- Check if size is correct --------
		assertEquals(4, mem.getBuffer().size());

		// -------------- Delete --------------------------
		System.out.println(mem.getBuffer());
		UnitTest.deleteTask(2, mem.getBuffer());

		// ------------ Sort -------------------------------
		UnitTest.sort(mem);
		System.out.println(mem.getBuffer());
		System.out.println(mem.getTempBuffer());

		// ------------ Search ----------------------------
		UnitTest.determineSearch("3", mem);
		System.out.println(mem.getTempBuffer());

		// Interpreter amendment = new Interpreter();
		// amendment.setTaskID(3);
		// Task task = UnitTest.retrieveTask(amendment, mem.getBuffer());
		// System.out.println(task.toString());
		appt.setKey("TASKNAME");
		appt.setTaskID(3);
		appt.setTaskName("HELLO");
		UnitTest.determineAmend(appt, mem);
		System.out.println(mem.getBuffer());

		appt.setKey("DUEDATE");
		String dateInString4 = "07/06/2016";
		date = formatter.parse(dateInString4);
		appt.setDueDate(date);
		UnitTest.determineAmend(appt, mem);
		System.out.println(mem.getBuffer());


		/*
		 * floating.setKey("DUEDATE"); Interpreter float2 = new Interpreter();
		 * String dateInString5 = "23/06/2016"; date =
		 * formatter.parse(dateInString5); float2.setTaskName("GOOO");
		 * float2.setType(TaskType.DEADLINE); float2.setTaskID(1);
		 * float2.setDueDate(date); UnitTest.determineAmend(floating, mem);
		 * System.out.println(mem.getBuffer());
		 */

		/*
		 * System.out.println("\n");
		 * System.out.println(buffer.get(0).toString());
		 * System.out.println(buffer.get(1).toString());
		 * 
		 * //UnitTest.clearTask(deadline, mem.getBuffer()); //assertEquals(0,
		 * buffer.size()); }
		 */

		// assertFalse(buffer.get(0).getTaskName().equals("CS2106"));
	}

}
