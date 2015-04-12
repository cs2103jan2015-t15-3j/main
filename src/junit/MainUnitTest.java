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
import logic.UnitTest;

public class MainUnitTest {
	/*
	 * This main unit testing class will test the possible output for the
	 * commands that the user inputs.
	 */

	@Test
	// This will cover the case of adding a floating task.
	public void testAddFloating() {
		Repository repo = new Repository();
		ArrayList<Task> buffer = repo.getBuffer();

		LogicMain.parseString("add CS2103", repo);

		Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
				repo.getCurrentID());
		assertEquals("ID", 1, tasks.getTaskID());
		assertEquals("Task Description", "CS2103", tasks.getTaskName());
		assertEquals("Task Type", TaskType.FLOATING, tasks.getType());
	}

	@Test
	// This will cover the case of adding a deadline task.
	public void testAddDeadline() {
		Repository repo = new Repository();

		LogicMain.parseString("add CS2103 [21/03/15]", repo);

		Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
				repo.getCurrentID());
		assertEquals("ID", 1, tasks.getTaskID());
		assertEquals("Task Description", "CS2103", tasks.getTaskName());
		if (tasks.getType().equals(TaskType.DEADLINE)) {
			Deadline item = (Deadline) tasks;
			assertEquals("Due Date", "21/03/15 23:59", item.getDueDateString());
		}
		assertEquals("Task type", TaskType.DEADLINE, tasks.getType());
	}

	@Test
	// This will cover the case of adding a appointment task.
	public void testAddAppointment() {
		Repository repo = new Repository();

		LogicMain.parseString("add CS2103 [21/03/15 13:30 22/03/15 14:30]",
				repo);

		Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
				repo.getCurrentID());
		assertEquals("ID", 1, tasks.getTaskID());
		assertEquals("Task Description", "CS2103", tasks.getTaskName());
		if (tasks.getType().equals(TaskType.APPOINTMENT)) {
			Appointment item = (Appointment) tasks;
			assertEquals("Start Date", "21/03/15 13:30",
					item.getStartDateString());
			assertEquals("Due Date", "22/03/15 14:30", item.getDueDateString());
		}
		assertEquals("Task Type", TaskType.APPOINTMENT, tasks.getType());
	}

	@Test
	// This will cover the case for wrong commands entered.
	public void testWrongCommandEntered() {
		Repository repo = new Repository();

		// Wrong command entered
		LogicMain.parseString("adds", repo);
		assertEquals("Invalid command", "The command does not exists.",
				repo.getFeedback());

		// Wrong command entered
		LogicMain.parseString("hello", repo);
		assertEquals("Invalid command", "The command does not exists.",
				repo.getFeedback());

		// Adding an empty string
		LogicMain.parseString("", repo);
		assertEquals("Invalid command", "The command does not exists.",
				repo.getFeedback());

		// Adding an integer that is being input as command
		LogicMain.parseString("1", repo);
		assertEquals("Invalid command", "The command does not exists.",
				repo.getFeedback());
	}

	@Test
	public void searchForTasks() {
		Repository repo = new Repository();

		// Checks for both negative and positive range
		int taskID = -1, taskIDTwo = 1, taskIDThree = 10;

		UnitTest.parseString("add CS2103", repo);
		UnitTest.parseString("add CS2106", repo);
		UnitTest.parseString("add EE2024", repo);

		try {
			LogicMain.parseString("find -1", repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskID + " is not found.");
		}

		try {
			Task tasks = UnitTest.retrieveTask(repo.getBuffer(), taskIDTwo);
			LogicMain.parseString("find 1", repo);
			assertEquals("ID 1 is found.", 1, tasks.getTaskID());
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskIDTwo + " is not found.");
		}

		try {
			LogicMain.parseString("find 10", repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskIDThree + " is not found.");
		}

		LogicMain.parseString("search CS", repo);
		assertEquals("Found 2 in the list.", 2, repo.getTempBufferSize());

		LogicMain.parseString("search CS21", repo);
		assertEquals("Found 2 in the list.", 1, repo.getTempBufferSize());

		LogicMain.parseString("search EE", repo);
		assertEquals("Found 1 in the list.", 1, repo.getTempBufferSize());

		LogicMain.parseString("search CG", repo);
		assertEquals("Found 0 in the list.", 0, repo.getTempBufferSize());

	}
}
