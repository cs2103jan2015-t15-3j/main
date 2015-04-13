package junit;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import logic.Appointment;
import logic.Deadline;
import logic.Repository;
import logic.Task;
import logic.Enumerator.TaskType;
import logic.UnitTest;

import org.junit.Test;

import parser.Interpreter;

//@author A0112643R

public class LogicUnitTest {
	/*
	 * The class Repository is updated and shared among the architecture. This
	 * LogicUnitTest class will handle individual unit test for the CRUD as well
	 * as the search operations.
	 */

	Repository repo = new Repository();
	Interpreter floating, floatingOne, floatingTwo, deadline, appt;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	// This will cover the case of adding a floating task.
	public void testAddFloating() {
		floating = new Interpreter();
		floating.setTaskID(1);
		floating.setTaskName("CS2103");
		floating.setType(TaskType.FLOATING);
		floating.setRemarks("Question 1 is important.");
		UnitTest.addTask(floating, repo.getBuffer(), repo.numberGenerator());

		Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
				floating.getTaskID());

		assertEquals("ID should be 1", 1, tasks.getTaskID());
		assertEquals("Task name should be CS2103", "CS2103",
				tasks.getTaskName());
		assertEquals("Task type is classified as floating", TaskType.FLOATING,
				tasks.getType());
		assertEquals("Remarks is not empty", "Question 1 is important.",
				tasks.getRemarks());
		assertEquals("Current buffer size should be 1", 1, repo.getBufferSize());
	}

	@Test
	// This will cover the case of adding a deadline task.
	public void testAddDeadline() throws ParseException {
		String dateInString = "09/06/13";
		Date date = formatter.parse(dateInString);
		date = formatter.parse(dateInString);

		deadline = new Interpreter();
		deadline.setTaskID(1);
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
				deadline.getTaskID());

		assertEquals("ID should be 1", 1, tasks.getTaskID());
		assertEquals("Task name should be CS2103", "CS2103",
				tasks.getTaskName());
		assertEquals("Task type is classified as deadline", TaskType.DEADLINE,
				tasks.getType());
		assertEquals("Remarks is empty", "", tasks.getRemarks());
		if (tasks.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) tasks;
			assertEquals("Due Date is set", "09/06/13 00:00",
					item.getDueDateString());
		}
		assertEquals("Current size should be 1", 1, repo.getBufferSize());
	}

	@Test
	// This will cover the adding of an appointment task.
	public void testAddAppointment() throws ParseException {
		String dateInString = "09/06/2013";
		String dateInStringTwo = "10/06/2013";
		Date date = formatter.parse(dateInString);
		Date dateTwo = formatter.parse(dateInStringTwo);

		appt = new Interpreter();
		appt.setTaskID(1);
		appt.setTaskName("CS2103");
		appt.setStartDate(date);
		appt.setDueDate(dateTwo);
		appt.setType(TaskType.APPOINTMENT);
		appt.setRemarks("Do Q1-Q5");
		UnitTest.addTask(appt, repo.getBuffer(), repo.numberGenerator());

		Task tasks = UnitTest.retrieveTask(repo.getBuffer(), appt.getTaskID());

		assertEquals("ID", 1, tasks.getTaskID());
		assertEquals("Task name", "CS2103", tasks.getTaskName());
		assertEquals("Task Type", TaskType.APPOINTMENT, tasks.getType());
		assertEquals("Remarks", "Do Q1-Q5", tasks.getRemarks());
		if (tasks.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) tasks;
			assertEquals("Start Date", "09/06/13 00:00",
					item.getStartDateString());
			assertEquals("Due Date", "10/06/13 00:00", item.getDueDateString());
		}
		assertEquals("Current size is 1", 1, repo.getBufferSize());
	}

	@Test
	// This will cover the case of amending a floating task.
	public void testAmendFloating() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		floating = new Interpreter();
		floating.setTaskID(1);
		floating.setTaskName("CS2103");
		floating.setType(TaskType.FLOATING);
		floating.setRemarks("Question 1 is important.");
		UnitTest.addTask(floating, repo.getBuffer(), repo.numberGenerator());

		Task task = UnitTest.retrieveTask(repo.getBuffer(),
				floating.getTaskID());

		floating.setKey("TASKNAME");
		floating.setTaskName("CS2103T");
		UnitTest.determineAmend(floating, repo);
		assertEquals("CS2103 changed to CS2103T", "CS2103T", task.getTaskName());

		floating.setKey("REMARKS");
		floating.setRemarks("Not important anymore!");
		UnitTest.determineAmend(floating, repo);
		assertEquals("Remarks edited", "Not important anymore!",
				task.getRemarks());

		/*
		 * This will amend the floating task into a deadline task by setting the
		 * due date.
		 */
		floating.setKey("DUEDATE");
		floating.setDueDate(date);
		UnitTest.determineAmend(floating, repo);

		Task taskTwo = UnitTest.retrieveTask(repo.getBuffer(),
				floating.getTaskID());

		if (taskTwo.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) taskTwo;
			assertEquals("Floating to Deadline task", "09/06/13 00:00",
					item.getDueDateString());
		}
	}

	@Test
	// This will cover the case of amending a deadline task.
	public void testAmendDeadline() throws ParseException {
		String dateInString = "09/06/2013";
		String dateInString2 = "10/06/2013";
		String dateInString3 = "11/06/2013";
		Date date = formatter.parse(dateInString);
		Date date2 = formatter.parse(dateInString2);
		Date date3 = formatter.parse(dateInString3);

		deadline = new Interpreter();
		deadline.setTaskID(1);
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date2);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		deadline.setKey("TASKNAME");
		deadline.setTaskName("CS2103T");
		UnitTest.determineAmend(deadline, repo);

		Task task = UnitTest.retrieveTask(repo.getBuffer(),
				deadline.getTaskID());

		assertEquals("CS2103 changed to CS2103T", "CS2103T", task.getTaskName());

		/*
		 * This will ammend the deadline into an appointment task by setting the
		 * due date.
		 */

		deadline.setKey("DUEDATE");
		deadline.setDueDate(date3);
		UnitTest.determineAmend(deadline, repo);

		Task taskTwo = UnitTest.retrieveTask(repo.getBuffer(),
				deadline.getTaskID());

		if (taskTwo.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) taskTwo;
			assertEquals("Date edited", "11/06/13 00:00",
					item.getDueDateString());
		}

		deadline.setKey("REMARKS");
		deadline.setRemarks("Do Q1-Q5!");
		UnitTest.determineAmend(deadline, repo);
		assertEquals("Remarks edited", "Do Q1-Q5!", task.getRemarks());

		deadline.setKey("STARTDATE");
		deadline.setStartDate(date);
		UnitTest.determineAmend(deadline, repo);

		Task taskThree = UnitTest.retrieveTask(repo.getBuffer(),
				deadline.getTaskID());

		if (taskThree.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) taskThree;
			assertEquals("Deadline to Appointment", "09/06/13 00:00",
					item.getStartDateString());
		}
	}

	@Test
	// This will cover the case of amending an appointment task.
	public void testAmendAppointment() throws ParseException {
		String dateInString = "09/06/2013";
		String dateInString2 = "10/06/2013";
		String dateInString3 = "11/06/2013";
		String dateInString4 = "12/06/2013";
		Date date = formatter.parse(dateInString);
		Date date2 = formatter.parse(dateInString2);
		Date date3 = formatter.parse(dateInString3);
		Date date4 = formatter.parse(dateInString4);

		appt = new Interpreter();
		appt.setTaskID(1);
		appt.setTaskName("CS2103");
		appt.setStartDate(date);
		appt.setDueDate(date2);
		appt.setType(TaskType.APPOINTMENT);
		appt.setRemarks("Q3 is inheritance.");
		UnitTest.addTask(appt, repo.getBuffer(), repo.numberGenerator());

		Task task = UnitTest.retrieveTask(repo.getBuffer(), appt.getTaskID());

		appt.setKey("TASKNAME");
		appt.setTaskName("CS2103T");
		UnitTest.determineAmend(appt, repo);
		assertEquals("CS2103 changed to CS2103T", "CS2103T", task.getTaskName());

		appt.setKey("REMARKS");
		appt.setRemarks("");
		UnitTest.determineAmend(appt, repo);
		assertEquals("Remarks cleared", "", task.getRemarks());

		appt.setKey("STARTDATE");
		appt.setStartDate(date3);
		UnitTest.determineAmend(appt, repo);
		if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) task;
			assertEquals("Start date edited", "11/06/13 00:00",
					item.getStartDateString());
		}

		appt.setKey("DUEDATE");
		appt.setDueDate(date4);
		UnitTest.determineAmend(appt, repo);
		if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) task;
			assertEquals("Due date edited", "12/06/13 00:00",
					item.getDueDateString());
		}
	}

	@Test
	// This will cover the case of deleting a task.
	public void testDelete() throws ParseException {
		// Checks for both negative and positive range
		int taskID = -1, taskIDTwo = 10, taskIDThree = 3;

		String dateInString = "09/06/2013";
		String dateInStringTwo = "10/06/2013";
		Date date = formatter.parse(dateInString);
		Date dateTwo = formatter.parse(dateInStringTwo);

		deadline = new Interpreter();
		deadline.setTaskID(1);
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		appt = new Interpreter();
		appt.setTaskID(2);
		appt.setTaskName("CS2103");
		appt.setStartDate(date);
		appt.setDueDate(dateTwo);
		appt.setType(TaskType.APPOINTMENT);
		appt.setRemarks("Do Q1-Q5");
		UnitTest.addTask(appt, repo.getBuffer(), repo.numberGenerator());
		assertEquals("Current size is 2", 2, repo.getBufferSize());

		/*
		 * This will cover the boundary cases testing deleting a task based on
		 * negative values as well as positive value.
		 */
		try {
			UnitTest.deleteTask(taskID, repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskID + " does not exists!");
		}

		try {
			UnitTest.deleteTask(taskIDTwo, repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskIDTwo + " does not exists!");
		}

		try {
			UnitTest.deleteTask(taskIDThree, repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskIDThree + " does not exists!");
		}

		UnitTest.deleteTask(1, repo);
		assertEquals("Current size should be 1", 1, repo.getBufferSize());
	}

	@Test
	// This will cover the cases of search by keywords and ID.
	public void testSearch() throws ParseException {
		// Checks for both negative and positive range
		int taskID = -1, taskIDTwo = 10, taskIDThree = 3;

		floating = new Interpreter();
		floating.setTaskID(1);
		floating.setTaskName("CS2103");
		floating.setType(TaskType.FLOATING);
		floating.setRemarks("Question 1 is important.");
		UnitTest.addTask(floating, repo.getBuffer(), repo.numberGenerator());

		floatingOne = new Interpreter();
		floatingOne.setTaskID(2);
		floatingOne.setTaskName("CS2103T");
		floatingOne.setType(TaskType.FLOATING);
		floatingOne.setRemarks("Question 2 is important.");
		UnitTest.addTask(floatingOne, repo.getBuffer(), repo.numberGenerator());

		floatingTwo = new Interpreter();
		floatingTwo.setTaskID(3);
		floatingTwo.setTaskName("EE2024");
		floatingTwo.setType(TaskType.FLOATING);
		floatingTwo.setRemarks("All questions important!");
		UnitTest.addTask(floatingTwo, repo.getBuffer(), repo.numberGenerator());

		/*
		 * This will test the boundary cases for search based on keywords and
		 * the ID provided.
		 */
		UnitTest.determineSearch("C", repo);
		assertEquals("Found 2 in the list.", 2, repo.getTempBufferSize());

		UnitTest.determineSearch("CS", repo);
		assertEquals("Found 2 in the list.", 2, repo.getTempBufferSize());

		UnitTest.determineSearch("CS210", repo);
		assertEquals("Found 2 in the list.", 2, repo.getTempBufferSize());

		UnitTest.determineSearch("CG", repo);
		assertEquals("None found.", 0, repo.getTempBufferSize());

		try {
			UnitTest.determineSearch("-1", repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskID + " is not found!");
		}

		try {
			UnitTest.determineSearch("10", repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskIDTwo + " is not found!");
		}

		try {
			UnitTest.determineSearch("3", repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskIDThree + " is not found!");
		}
		assertEquals("Found 1 item.", 1, repo.getTempBufferSize());
	}

	@Test
	// This will cover the case of completion and incompletion of a task.
	public void testCompleteUncompleteTask() throws ParseException {
		String dateInString = "09/06/2013";
		String dateInStringTwo = "10/06/2013";
		Date date = formatter.parse(dateInString);
		Date dateTwo = formatter.parse(dateInStringTwo);

		deadline = new Interpreter();
		deadline.setTaskID(1);
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		appt = new Interpreter();
		appt.setTaskID(2);
		appt.setTaskName("CS2103");
		appt.setStartDate(date);
		appt.setDueDate(dateTwo);
		appt.setType(TaskType.APPOINTMENT);
		appt.setRemarks("Do Q1-Q5");
		UnitTest.addTask(appt, repo.getBuffer(), repo.numberGenerator());

		deadline.setIsCompleted(true);
		UnitTest.setCompletion(deadline, repo);

		Task task = UnitTest.retrieveTask(repo.getBuffer(),
				deadline.getTaskID());
		if (task.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) task;
			assertEquals("Result is true", true, item.getCompleted());
		}

		appt.setIsCompleted(true);
		UnitTest.setCompletion(appt, repo);

		Task taskTwo = UnitTest
				.retrieveTask(repo.getBuffer(), appt.getTaskID());

		if (taskTwo.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) taskTwo;
			assertEquals("Result is true", true, item.getCompleted());
		}

		appt.setIsCompleted(false);
		UnitTest.setCompletion(appt, repo);

		Task taskThree = UnitTest.retrieveTask(repo.getBuffer(),
				appt.getTaskID());

		if (taskThree.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) taskThree;
			assertEquals("Item is uncompleted", false, item.getCompleted());
		}
	}
}
