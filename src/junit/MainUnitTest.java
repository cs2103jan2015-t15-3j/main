package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import logic.Deadline;
import logic.Enumerator.TaskType;
import logic.Appointment;
import logic.LogicMain;
import logic.Task;
import logic.Repository;

public class MainUnitTest {
	/*
	 * This main unit testing class will test the possible output for the
	 * commands that the user inputs. The tasks to be completed by the earliest
	 * date will be sorted and inserted accordingly while the floating tasks
	 * will be at the back.
	 */

	Repository repo = new Repository();
	ArrayList<Task> obtainedTask = repo.getBuffer();

	@Test
	public void testAddFloating() {
		ArrayList<Task> obtainedTask = repo.getBuffer();
		LogicMain.executeCommand("add CS2103", repo);
		Task tasks = obtainedTask.get(obtainedTask.size() - 1);

		assertEquals("ID", 3, tasks.getTaskID());
		assertEquals("Task Description", "CS2103", tasks.getTaskName());
		assertEquals("Task Type", TaskType.FLOATING, tasks.getType());
	}

	@Test
	public void testAddDeadline() {
		LogicMain.executeCommand("add CS2103 21/03/15", repo);

		Task tasks = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("ID", 2, tasks.getTaskID());
		assertEquals("Task Description", "CS2103", tasks.getTaskName());
		if (tasks.getType().equals("DEADLINE")) {
			Deadline item = (Deadline) tasks;
			assertEquals("Due Date", "21/03/15 23:59", item.getDueDateString());
		}
		assertEquals("Task type", TaskType.DEADLINE, tasks.getType());
	}

	@Test
	public void testAddAppointment() {
		LogicMain.executeCommand("add CS2103 21/03/15 13:30 22/03/15 14:30",
				repo);

		Task tasks = obtainedTask.get(obtainedTask.size() - 1);
		assertEquals("ID", 1, tasks.getTaskID());
		assertEquals("Task Description", "CS2103", tasks.getTaskName());
		if (tasks.getType().equals("APPOINTMENT")) {
			Appointment item = (Appointment) tasks;
			assertEquals("Start Date", "21/03/15 13:30", item.getStartDate());
			assertEquals("Due Date", "22/03/15 14:30", item.getDueDateString());
		}
		assertEquals("Task Type", TaskType.APPOINTMENT, tasks.getType());
	}

	@Test
	public void testWrongCommandEntered() {
		LogicMain.executeCommand("adds Hello", repo);
		assertEquals("Command does not exists.",
				"The command you entered does not exist.", repo.getFeedback());
	}

	@Test
	public void searchForTasks() {
		LogicMain.executeCommand("display", repo);

		LogicMain.executeCommand("search 1", repo);

		assertEquals("Added to temp list", 1, repo.getBufferSize());
	}
}
