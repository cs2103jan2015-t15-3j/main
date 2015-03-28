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
	 * This main unit testing class will test the possible output for the
	 * commands that the user inputs. The tasks to be completed by the earliest
	 * date will be sorted and inserted accordingly while the floating tasks
	 * will be at the back.
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
	}
}
