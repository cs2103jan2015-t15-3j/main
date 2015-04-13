package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.Deadline;
import logic.Enumerator.TaskType;
import logic.Appointment;
import logic.LogicMain;
import logic.Task;
import logic.Repository;
import logic.UnitTest;

//@author A0112643R

public class MainUnitTest {
	/*
	 * This main unit testing class will test the possible output for the
	 * commands that the user inputs.
	 */

	@Test
	// This will cover the case of adding a floating task.
	public void testAddFloating() {
		Repository repo = new Repository();

		// Adding a floating task with only task name
		LogicMain.parseString("add CS2103", repo);

		// Adding a floating task with task name and remarks
		LogicMain.parseString("add EE2024 <Qn 1 important>", repo);

		// Current buffer size is 2 after adding
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		// Retrieving a floating task with only task name
		Task taskOne = UnitTest.retrieveTask(repo.getBuffer(), 1);
		assertEquals("ID", 1, taskOne.getTaskID());
		assertEquals("Task Description", "CS2103", taskOne.getTaskName());
		assertEquals("Task Type", TaskType.FLOATING, taskOne.getType());

		// Retrieving a floating task with task name and remarks
		Task taskTwo = UnitTest.retrieveTask(repo.getBuffer(), 2);
		assertEquals("ID", 2, taskTwo.getTaskID());
		assertEquals("Task Description", "EE2024", taskTwo.getTaskName());
		assertEquals("Task Type", TaskType.FLOATING, taskTwo.getType());
		assertEquals("Remarks", "Qn 1 important", taskTwo.getRemarks());
	}

	@Test
	// This will cover the case of adding a deadline task.
	public void testAddDeadline() {
		Repository repo = new Repository();

		// Adding a deadline task with task name and end date
		LogicMain.parseString("add CS2103 [21/03/15]", repo);

		// Adding a deadline task with task name and end date with remarks
		LogicMain.parseString(
				"add CS1020 [21/03/15 22:00] <Use linked list in Qn 5>", repo);

		// Current buffer size is 2 after adding.
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		/*
		 * Retrieving a deadline task with task name and end date. Default time
		 * is 2359 hrs since user did not enter time.
		 */
		Task taskOne = UnitTest.retrieveTask(repo.getBuffer(), 1);
		assertEquals("ID", 1, taskOne.getTaskID());
		assertEquals("Task Description", "CS2103", taskOne.getTaskName());
		if (taskOne.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) taskOne;
			assertEquals("Due Date", "21/03/15 23:59", item.getDueDateString());
		}
		assertEquals("Task type", TaskType.DEADLINE, taskOne.getType());

		// Retrieving a deadline task with task name and end date with remarks
		Task taskTwo = UnitTest.retrieveTask(repo.getBuffer(), 2);
		assertEquals("ID", 2, taskTwo.getTaskID());
		assertEquals("Task Description", "CS1020", taskTwo.getTaskName());
		if (taskTwo.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) taskTwo;
			assertEquals("Due Date", "21/03/15 22:00", item.getDueDateString());
		}
		assertEquals("Task type", TaskType.DEADLINE, taskTwo.getType());
		assertEquals("Remarks", "Use linked list in Qn 5", taskTwo.getRemarks());
	}

	@Test
	// This will cover the case of adding a appointment task.
	public void testAddAppointment() {
		Repository repo = new Repository();

		// Adding a appointment task with task name and both start end date
		LogicMain.parseString("add CS2103 [21/03/15 13:30 22/03/15 14:30]",
				repo);

		/*
		 * Adding a appointment task with task name and both start end date with
		 * remarks
		 */
		LogicMain.parseString(
				"add CS1020 [21/03/15 22/03/15] <Do Qn 1 - 5 only>", repo);

		// Current buffer size is 2 after adding.
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		/*
		 * Retrieving a appointment task with task name and both start and end
		 * date.
		 */
		Task taskOne = UnitTest.retrieveTask(repo.getBuffer(), 1);
		assertEquals("ID", 1, taskOne.getTaskID());
		assertEquals("Task Description", "CS2103", taskOne.getTaskName());
		if (taskOne.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) taskOne;
			assertEquals("Start Date", "21/03/15 13:30",
					item.getStartDateString());
			assertEquals("Due Date", "22/03/15 14:30", item.getDueDateString());
		}
		assertEquals("Task Type", TaskType.APPOINTMENT, taskOne.getType());

		/*
		 * Retrieving a appointment task with task name, both start and end date
		 * and remarks. Default time is 2359 hrs since user did not enter time.
		 */
		Task taskTwo = UnitTest.retrieveTask(repo.getBuffer(), 2);
		assertEquals("ID", 2, taskTwo.getTaskID());
		assertEquals("Task Description", "CS1020", taskTwo.getTaskName());
		if (taskTwo.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) taskTwo;
			assertEquals("Start Date", "21/03/15 23:59",
					item.getStartDateString());
			assertEquals("Due Date", "22/03/15 23:59", item.getDueDateString());
		}
		assertEquals("Task Type", TaskType.APPOINTMENT, taskTwo.getType());
		assertEquals("Task Type", "Do Qn 1 - 5 only", taskTwo.getRemarks());
	}

	@Test
	// This will cover the case for wrong commands entered.
	public void testWrongCommandEntered() {
		Repository repo = new Repository();

		// adds is an invalid command
		LogicMain.parseString("adds", repo);
		assertEquals("Invalid command", "The command does not exists.",
				repo.getFeedback());

		// hello is an invalid command
		LogicMain.parseString("hello", repo);
		assertEquals("Invalid command", "The command does not exists.",
				repo.getFeedback());

		// An empty string is also an invalid command.
		LogicMain.parseString("", repo);
		assertEquals("Invalid command", "The command does not exists.",
				repo.getFeedback());

		// Adding an integer first is also an invalid command.
		LogicMain.parseString("1", repo);
		assertEquals("Invalid command", "The command does not exists.",
				repo.getFeedback());
	}

	@Test
	public void testSearchForTasks() {
		Repository repo = new Repository();

		// Checks for both negative and positive range
		int taskID = -1, taskIDTwo = 1, taskIDThree = 10;

		// Adding three floating tasks with only task name
		LogicMain.parseString("add CS2103", repo);
		LogicMain.parseString("add CS2106", repo);
		LogicMain.parseString("add EE2024", repo);

		// Current buffer size is 3 after adding.
		assertEquals("Current buffer size", 3, repo.getBufferSize());

		// task ID -1 is invalid.
		try {
			LogicMain.parseString("find -1", repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskID + " is not found.");
		}

		try {
			Task tasks = UnitTest.retrieveTask(repo.getBuffer(), taskIDTwo);
			LogicMain.parseString("find 1", repo);

			// task ID is found
			assertEquals("ID 1 is found.", 1, tasks.getTaskID());
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskIDTwo + " is not found.");
		}

		// task ID 10 is invalid.
		try {
			LogicMain.parseString("find 10", repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskIDThree + " is not found.");
		}

		/*
		 * The code below will cover the keywords that reside in the buffer and
		 * return the results
		 */
		LogicMain.parseString("search CS", repo);
		assertEquals("Found 2 in the list.", 2, repo.getTempBufferSize());

		LogicMain.parseString("search CS21", repo);
		assertEquals("Found 2 in the list.", 2, repo.getTempBufferSize());

		LogicMain.parseString("search EE", repo);
		assertEquals("Found 1 in the list.", 1, repo.getTempBufferSize());

		LogicMain.parseString("search CG", repo);
		assertEquals("Found 0 in the list.", 0, repo.getTempBufferSize());
	}

	@Test
	public void testDelete() {
		Repository repo = new Repository();

		// Adding a deadline task with task name and remarks
		LogicMain.parseString("add CS2103 [12/05/15] <Finish report>", repo);

		// Adding a appointment task with task name
		LogicMain.parseString("add EE2020 [13/06/15 14/06/15]", repo);

		// Current buffer size is 2 after adding.
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		LogicMain.parseString("d -1", repo);

		// Current buffer size should be still 2 after an invalid ID deletion
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		LogicMain.parseString("delete 10", repo);

		// Current buffer size should be still 2 after an invalid ID deletion
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		LogicMain.parseString("d 1", repo);

		// Current buffer size should be 1 after deletion
		assertEquals("Current buffer size", 1, repo.getBufferSize());

		// Task ID 2 is the only remaining in the buffer upon retrieval
		Task taskTwo = UnitTest.retrieveTask(repo.getBuffer(), 2);
		assertEquals("Task ID", 2, taskTwo.getTaskID());
	}

	@Test
	public void testClear() {
		Repository repo = new Repository();

		// Adding a deadline task with task name and remarks
		LogicMain.parseString("add CS2103 [12/05/15] <Finish report>", repo);

		// Adding a appointment task with task name
		LogicMain.parseString("add EE2020 [13/06/15 14/06/15]", repo);

		// Current buffer size is 2 after adding.
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		// Issue the clear command to clear the current buffer
		LogicMain.parseString("clear", repo);

		assertEquals("Current buffer size", 0, repo.getBufferSize());
	}

}
