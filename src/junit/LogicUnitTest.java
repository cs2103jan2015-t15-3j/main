package junit;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logic.Appointment;
import logic.Deadline;
import logic.Repository;
import logic.Task;
import logic.Enumerator.TaskType;
import logic.UnitTest;

import org.junit.Test;

import parser.Interpreter;

public class LogicUnitTest {
	/*
	 * The class Repository is updated and shared among the architecture. This
	 * LogicUnitTest class will handle individual unit test for the CRUD as well
	 * as the search operations without the use of parser and database.
	 */

	Repository repo = new Repository();
	ArrayList<Task> obtainedTask = repo.getBuffer();
	Interpreter floating, floatingOne, floatingTwo, deadline, appt;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void testAddFloating() {
		floating = new Interpreter();
		floating.setTaskName("CS2103");
		floating.setType(TaskType.FLOATING);
		floating.setRemarks("Question 1 is important.");
		UnitTest.addTask(floating, repo.getBuffer(), repo.numberGenerator());

		ArrayList<Task> obtainedTask = repo.getBuffer();
		Task tasks = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("ID", 1, tasks.getTaskID());
		assertEquals("Task name", "CS2103", tasks.getTaskName());
		assertEquals("Task Type", TaskType.FLOATING, tasks.getType());
		assertEquals("Remarks", "Question 1 is important.", tasks.getRemarks());
		assertEquals("Current buffer size", 1, repo.getBufferSize());
	}

	@Test
	public void testAddDeadline() throws ParseException {
		String dateInString = "09/06/13";
		Date date = formatter.parse(dateInString);
		date = formatter.parse(dateInString);

		deadline = new Interpreter();
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		ArrayList<Task> obtainedTask = repo.getBuffer();
		Task tasks = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("ID", 1, tasks.getTaskID());
		assertEquals("Task name", "CS2103", tasks.getTaskName());
		assertEquals("Task Type", TaskType.DEADLINE, tasks.getType());
		assertEquals("Remarks", "", tasks.getRemarks());
		if (tasks.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) tasks;
			assertEquals("Due Date", "09/06/13 00:00 AM",
					item.getDueDateString());
		}
		assertEquals("Current buffer size", 1, repo.getBufferSize());
	}

	@Test
	public void testAddAppointment() throws ParseException {
		String dateInString = "09/06/2013";
		String dateInStringTwo = "10/06/2013";
		Date date = formatter.parse(dateInString);
		Date dateTwo = formatter.parse(dateInStringTwo);

		appt = new Interpreter();
		appt.setTaskName("CS2103");
		appt.setStartDate(date);
		appt.setDueDate(dateTwo);
		appt.setType(TaskType.APPOINTMENT);
		appt.setRemarks("Do Q1-Q5");
		UnitTest.addTask(appt, repo.getBuffer(), repo.numberGenerator());

		ArrayList<Task> obtainedTask = repo.getBuffer();
		Task tasks = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("ID", 1, tasks.getTaskID());
		assertEquals("Task name", "CS2103", tasks.getTaskName());
		assertEquals("Task Type", TaskType.APPOINTMENT, tasks.getType());
		assertEquals("Remarks", "Do Q1-Q5", tasks.getRemarks());
		if (tasks.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) tasks;
			assertEquals("Start Date", "09/06/13 00:00 AM",
					item.getStartDateString());
			assertEquals("Due Date", "10/06/13 00:00 AM",
					item.getDueDateString());
		}
		assertEquals("Current buffer size", 1, repo.getBufferSize());
	}

	@Test
	public void testAmendFloating() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		floating = new Interpreter();
		floating.setTaskName("CS2103");
		floating.setType(TaskType.FLOATING);
		floating.setRemarks("Question 1 is important.");
		UnitTest.addTask(floating, repo.getBuffer(), repo.numberGenerator());

		Task task = obtainedTask.get(obtainedTask.size() - 1);
		floating.setKey("TASKNAME");
		floating.setTaskID(1);
		floating.setTaskName("CS2103T");
		UnitTest.determineAmend(floating, repo);
		assertEquals("CS2103 changed to CS2103T", "CS2103T", task.getTaskName());

		floating.setKey("REMARKS");
		floating.setTaskID(1);
		floating.setRemarks("Not important anymore!");
		UnitTest.determineAmend(floating, repo);
		assertEquals("Remarks edited", "Not important anymore!",
				task.getRemarks());

		floating.setKey("DUEDATE");
		floating.setTaskID(1);
		floating.setDueDate(date);
		UnitTest.determineAmend(floating, repo);
		Task taskTwo = obtainedTask.get(obtainedTask.size() - 1);
		if (taskTwo.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) taskTwo;
			assertEquals("Floating to Deadline task", "09/06/13 00:00 AM",
					item.getDueDateString());
		}
	}

	@Test
	public void testAmendDeadline() throws ParseException {
		String dateInString = "09/06/2013";
		String dateInString2 = "10/06/2013";
		String dateInString3 = "11/06/2013";
		Date date = formatter.parse(dateInString);
		Date date2 = formatter.parse(dateInString2);
		Date date3 = formatter.parse(dateInString3);

		deadline = new Interpreter();
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date2);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		deadline.setKey("TASKNAME");
		deadline.setTaskID(1);
		deadline.setTaskName("CS2103T");
		UnitTest.determineAmend(deadline, repo);
		Task task = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("CS2103 changed to CS2103T", "CS2103T", task.getTaskName());

		deadline.setKey("DUEDATE");
		deadline.setTaskID(1);
		deadline.setDueDate(date3);
		UnitTest.determineAmend(deadline, repo);
		Task taskTwo = obtainedTask.get(obtainedTask.size() - 1);
		if (taskTwo.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) taskTwo;
			assertEquals("Date edited", "11/06/13 00:00 AM",
					item.getDueDateString());
		}

		deadline.setKey("REMARKS");
		deadline.setTaskID(1);
		deadline.setRemarks("Do Q1-Q5!");
		UnitTest.determineAmend(deadline, repo);
		assertEquals("Remarks edited", "Do Q1-Q5!", task.getRemarks());

		deadline.setKey("STARTDATE");
		deadline.setTaskID(1);
		deadline.setStartDate(date);
		UnitTest.determineAmend(deadline, repo);
		Task taskThree = obtainedTask.get(obtainedTask.size() - 1);
		if (taskThree.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) taskThree;
			assertEquals("Deadline to Appointment", "09/06/13 00:00 AM",
					item.getStartDateString());
		}
	}

	@Test
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
		appt.setTaskName("CS2103");
		appt.setStartDate(date);
		appt.setDueDate(date2);
		appt.setType(TaskType.APPOINTMENT);
		appt.setRemarks("Q3 is inheritance.");
		UnitTest.addTask(appt, repo.getBuffer(), repo.numberGenerator());

		ArrayList<Task> obtainedTask = repo.getBuffer();
		Task task = obtainedTask.get(obtainedTask.size() - 1);
		appt.setKey("TASKNAME");
		appt.setTaskID(1);
		appt.setTaskName("CS2103T");
		UnitTest.determineAmend(appt, repo);
		assertEquals("CS2103 changed to CS2103T", "CS2103T", task.getTaskName());

		appt.setKey("REMARKS");
		appt.setTaskID(1);
		appt.setRemarks("");
		UnitTest.determineAmend(appt, repo);
		assertEquals("Remarks cleared", "", task.getRemarks());

		appt.setKey("STARTDATE");
		appt.setTaskID(1);
		appt.setStartDate(date3);
		UnitTest.determineAmend(appt, repo);
		if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) task;
			assertEquals("Start date edited", "11/06/13 00:00 AM",
					item.getStartDateString());
		}

		appt.setKey("DUEDATE");
		appt.setTaskID(1);
		appt.setDueDate(date4);
		UnitTest.determineAmend(appt, repo);
		if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) task;
			assertEquals("Due date edited", "12/06/13 00:00 AM",
					item.getDueDateString());
		}
	}

	@Test
	public void testDelete() throws ParseException {
		String dateInString = "09/06/2013";
		String dateInStringTwo = "10/06/2013";
		Date date = formatter.parse(dateInString);
		Date dateTwo = formatter.parse(dateInStringTwo);

		deadline = new Interpreter();
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		appt = new Interpreter();
		appt.setTaskName("CS2103");
		appt.setStartDate(date);
		appt.setDueDate(dateTwo);
		appt.setType(TaskType.APPOINTMENT);
		appt.setRemarks("Do Q1-Q5");
		UnitTest.addTask(appt, repo.getBuffer(), repo.numberGenerator());
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		int taskID = -1;
		try {
			UnitTest.deleteTask(taskID, repo.getBuffer());
		} catch (IndexOutOfBoundsException e) {

		}

		int taskIDTwo = 10;
		try {
			UnitTest.deleteTask(taskIDTwo, repo.getBuffer());
		} catch (IndexOutOfBoundsException e) {
			assertFalse(taskIDTwo == repo.getBufferSize());
		}

		int taskIDThree = 1;
		UnitTest.deleteTask(taskIDThree, repo.getBuffer());
		assertEquals("Current size", 1, repo.getBufferSize());
	}

	@Test
	public void testSearch() throws ParseException {
		floating = new Interpreter();
		floating.setTaskName("CS2103");
		floating.setType(TaskType.FLOATING);
		floating.setRemarks("Question 1 is important.");
		UnitTest.addTask(floating, repo.getBuffer(), repo.numberGenerator());

		floatingOne = new Interpreter();
		floatingOne.setTaskName("CS2103T");
		floatingOne.setType(TaskType.FLOATING);
		floatingOne.setRemarks("Question 2 is important.");
		UnitTest.addTask(floatingOne, repo.getBuffer(), repo.numberGenerator());

		floatingTwo = new Interpreter();
		floatingTwo.setTaskName("EE2024");
		floatingTwo.setType(TaskType.FLOATING);
		floatingTwo.setRemarks("All questions important!");
		UnitTest.addTask(floatingTwo, repo.getBuffer(), repo.numberGenerator());

		UnitTest.determineSearch("C", repo);
		assertEquals("Added to temp list", 2, repo.getTempBufferSize());

		UnitTest.determineSearch("CS", repo);
		assertEquals("Added to temp list", 2, repo.getTempBufferSize());

		UnitTest.determineSearch("CS210", repo);
		assertEquals("Added to temp list", 2, repo.getTempBufferSize());

		UnitTest.determineSearch("CG", repo);
		assertEquals("Added to temp list", 0, repo.getTempBufferSize());

		UnitTest.determineSearch("-1", repo);
		assertEquals("Added to temp list", 0, repo.getTempBufferSize());

		UnitTest.determineSearch("3", repo);
		assertEquals("Added to temp list", 1, repo.getTempBufferSize());

		UnitTest.determineSearch("10", repo);
		assertEquals("Added to temp list", 0, repo.getTempBufferSize());
	}

	@Test
	public void testCompleteUncompleteTask() throws ParseException {
		String dateInString = "09/06/2013";
		String dateInStringTwo = "10/06/2013";
		Date date = formatter.parse(dateInString);
		Date dateTwo = formatter.parse(dateInStringTwo);

		deadline = new Interpreter();
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		appt = new Interpreter();
		appt.setTaskName("CS2103");
		appt.setStartDate(date);
		appt.setDueDate(dateTwo);
		appt.setType(TaskType.APPOINTMENT);
		appt.setRemarks("Do Q1-Q5");
		UnitTest.addTask(appt, repo.getBuffer(), repo.numberGenerator());
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		deadline.setIsCompleted(true);
		deadline.setTaskID(1);
		UnitTest.setCompletion(deadline, repo);
		Task task = obtainedTask.get(obtainedTask.size() - 2);
		if (task.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) task;
			assertEquals("Set completion", true, item.getCompleted());
		}

		appt.setIsCompleted(true);
		appt.setTaskID(2);
		UnitTest.setCompletion(appt, repo);
		System.out.println(repo.getBuffer());
		Task taskTwo = obtainedTask.get(obtainedTask.size() - 1);
		if (taskTwo.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) taskTwo;
			assertEquals("Set completion", true, item.getCompleted());
		}

		appt.setIsCompleted(false);
		appt.setTaskID(2);
		UnitTest.setCompletion(appt, repo);
		System.out.println(repo.getBuffer());
		Task taskThree = obtainedTask.get(obtainedTask.size() - 1);
		if (taskThree.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) taskThree;
			assertEquals("Set completion", false, item.getCompleted());
		}
	}
}
