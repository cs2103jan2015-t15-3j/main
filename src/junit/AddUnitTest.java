package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

import logic.Deadline;
import logic.Enumerator.TaskType;
import logic.Appointment;
import logic.LogicMain;
import logic.Task;
import logic.Repository;

public class AddUnitTest {
	/*
	 * This unit testing class will test the possible output for the add
	 * command. The tasks to be completed by the earliest date will be sorted
	 * and inserted accordingly while the floating tasks will be at the back.
	 */
	
	protected static final String DELETED_SUCCESSFUL = "Your task has been deleted.";
	
	@Test
	public void testAddFloating() {
		Repository repo = new Repository();
		ArrayList<Task> obtainedTask = repo.getBuffer();
		LogicMain.executeCommand("add CS2103", repo);


		Task tasks = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("ID", 4, repo.getCurrentID());
		assertEquals("task description should be CS2103", "CS2103",
				tasks.getTaskName());
		assertEquals("Task Type", TaskType.FLOATING, tasks.getType());
	}

	@Test
	public void testAddDeadline() {
		Repository repo = new Repository();
		ArrayList<Task> obtainedTask = repo.getBuffer();
		LogicMain.executeCommand("add CS2103 21/03/15", repo);

		Task tasks = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("ID", 2, repo.getCurrentID());
		assertEquals("Task Description", "CS2103", tasks.getTaskName());
		if (tasks.getType().equals("DEADLINE")) {
			Deadline item = (Deadline) tasks;
			assertEquals("Due Date", "21/03/15 23:59", item.getDueDateString());
		}
		assertEquals("Task type", TaskType.DEADLINE, tasks.getType());
	}

	@Test
	public void testAddAppointment() {
		// This method test for adding deadline task
		Repository repo = new Repository();
		ArrayList<Task> obtainedTask = repo.getBuffer();
		LogicMain.executeCommand("add CS2103 21/03/15 13:30 22/03/15 14:30",
				repo);

		Task tasks = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("ID", 1, repo.getCurrentID());
		assertEquals("Task Description", "CS2103", tasks.getTaskName());
		if (tasks.getType().equals("APPOINTMENT")) {
			Appointment item = (Appointment) tasks;
			assertEquals("Start Date", "21/03/15 13:30", item.getStartDate());
			assertEquals("Due Date", "22/03/15 14:30", item.getDueDateString());
		}
		assertEquals("Task Type", TaskType.APPOINTMENT, tasks.getType());
	}

	@Test
	public void testWrongCommand() {
		// This test for a wrong command issued.
		Repository repo = new Repository();
		repo = LogicMain.executeCommand("adds Hello", repo);

		assertEquals("Wrong command issue",
				"The command you entered does not exist.", repo.getFeedback());
	}

	@Test
	public void testAddSecondAppointment() {
		Repository repo = new Repository();
		ArrayList<Task> obtainedTask = repo.getBuffer();
		LogicMain.executeCommand("add CS2103 22/03/15 23/03/15 22:30", repo);

		Task tasks = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("ID", 3, repo.getCurrentID());
		assertEquals("Task Description", "CS2103", tasks.getTaskName());
		if (tasks.getType().equals("APPOINTMENT")) {
			Appointment item = (Appointment) tasks;
			assertEquals("Start Date", "22/03/15 23:59", item.getStartDate());
			assertEquals("Due Date", "23/03/15 22:30", item.getDueDateString());
		}
		assertEquals("Task Type", TaskType.APPOINTMENT, tasks.getType());
	}
	
	@Test
	public void deleteSecondAppointment() {
		Repository repo = new Repository();
		
		LogicMain.executeCommand("delete 2", repo);
		ArrayList<Task> obtainedTask = repo.getBuffer();
		System.out.println(repo.getBuffer());
		
		assertEquals("Feedback Message", "Your task has been deleted.", DELETED_SUCCESSFUL);
		
	} 
	
}

/*
 * @Test // Adds a floating task public void test() throws ParseException {
 * String dateInString = "07/06/2013"; Date date =
 * formatter.parse(dateInString); deadline = new Interpreter();
 * deadline.setTaskName("hcp"); deadline.setType(TaskType.DEADLINE);
 * dateInString = "09/06/2013"; date = formatter.parse(dateInString);
 * deadline.setDueDate(date); deadline.setRemarks("");
 * UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());
 * 
 * // ------------- Add appointment task ----------------- appt = new
 * Interpreter(); appt.setTaskName("CPH"); appt.setType(TaskType.APPOINTMENT);
 * dateInString = "10/06/2013"; date = formatter.parse(dateInString); String
 * dateInString2 = "12/06/2013"; Date date1 = formatter.parse(dateInString2);
 * appt.setStartDate(date1); appt.setDueDate(date);
 * appt.setRemarks("This is tedious!"); UnitTest.addTask(appt, mem.getBuffer(),
 * mem.numberGenerator());
 * 
 * // ---------------- Add another deadline ---------------- deadline2 = new
 * Interpreter(); deadline2.setTaskName("GOOOO");
 * deadline2.setType(TaskType.DEADLINE); dateInString = "20/06/2013"; date =
 * formatter.parse(dateInString); deadline2.setDueDate(date);
 * deadline2.setRemarks("This is tedious!"); UnitTest.addTask(deadline2,
 * mem.getBuffer(), mem.numberGenerator());
 * 
 * // ------------- Check if size is correct -------- assertEquals(4,
 * mem.getBuffer().size());
 * 
 * // -------------- Delete --------------------------
 * System.out.println(mem.getBuffer()); UnitTest.deleteTask(2, mem.getBuffer());
 * 
 * // ------------ Sort ------------------------------- UnitTest.sort(mem);
 * System.out.println(mem.getBuffer()); System.out.println(mem.getTempBuffer());
 * 
 * // ------------ Search ----------------------------
 * UnitTest.determineSearch("3", mem); System.out.println(mem.getTempBuffer());
 * 
 * // Interpreter amendment = new Interpreter(); // amendment.setTaskID(3); //
 * Task task = UnitTest.retrieveTask(amendment, mem.getBuffer()); //
 * System.out.println(task.toString()); appt.setKey("TASKNAME");
 * appt.setTaskID(3); appt.setTaskName("HELLO"); UnitTest.determineAmend(appt,
 * mem); System.out.println(mem.getBuffer());
 * 
 * appt.setKey("DUEDATE"); String dateInString4 = "07/06/2016"; date =
 * formatter.parse(dateInString4); appt.setDueDate(date);
 * UnitTest.determineAmend(appt, mem); System.out.println(mem.getBuffer());
 * 
 * 
 * /* floating.setKey("DUEDATE"); Interpreter float2 = new Interpreter(); String
 * dateInString5 = "23/06/2016"; date = formatter.parse(dateInString5);
 * float2.setTaskName("GOOO"); float2.setType(TaskType.DEADLINE);
 * float2.setTaskID(1); float2.setDueDate(date);
 * UnitTest.determineAmend(floating, mem); System.out.println(mem.getBuffer());
 */

/*
 * System.out.println("\n"); System.out.println(buffer.get(0).toString());
 * System.out.println(buffer.get(1).toString());
 * 
 * //UnitTest.clearTask(deadline, mem.getBuffer()); //assertEquals(0,
 * buffer.size()); }
 */

// assertFalse(buffer.get(0).getTaskName().equals("CS2106"));

