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

public class MainUnitTest {
	/*
	 * This main unit testing class will test the possible output for the
	 * commands that the user inputs. The tasks to be completed by the earliest
	 * date will be sorted and inserted accordingly while the floating tasks
	 * will be at the back.
	 */

	Repository repo = new Repository();
	ArrayList<Task> obtainedTask = repo.getBuffer();
	ArrayList<Integer> tasksIDs = new ArrayList<Integer>();

	@Test
	public void testAddFloating() {

		Task task = new Task();
		LogicMain.parseString("add CS2103", repo);
		if (tasksIDs.size() == 0) {
			task = obtainedTask.get(0);
		}
		for (Task childTask : obtainedTask) {
			if (!tasksIDs.contains(task.getTaskID())) {
				task = childTask;
				tasksIDs.add(task.getTaskID());
			}
		}

		System.out.println(repo.getBuffer());
		assertEquals("ID", 1, task.getTaskID());
		assertEquals("Task Description", "CS2103", task.getTaskName());
		assertEquals("Task Type", TaskType.FLOATING, task.getType());
	}

	@Test
	public void testAddDeadline() {

		Task task = new Task();
		ArrayList<Task> obtainedTask = repo.getBuffer();
		LogicMain.parseString("add CS2103 21/03/15", repo);
		if (tasksIDs.size() == 0) {
			task = obtainedTask.get(0);
		}
		for (Task childTask : obtainedTask) {
			if (!tasksIDs.contains(task.getTaskID())) {
				task = childTask;
				tasksIDs.add(task.getTaskID());
			}
		}

		assertEquals("ID", 2, task.getTaskID());
		assertEquals("Task Description", "CS2103", task.getTaskName());
		if (task.getType().equals("DEADLINE")) {
			Deadline item = (Deadline) task;
			assertEquals("Due Date", "21/03/15 23:59", item.getDueDateString());
		}
		assertEquals("Task type", TaskType.DEADLINE, task.getType());
	}

	/*
	 * @Test public void testAddAppointment() {
	 * LogicMain.parseString("add CS2103 21/03/15 13:30 22/03/15 14:30", repo);
	 * 
	 * Task tasks = obtainedTask.get(obtainedTask.size() - 1);
	 * assertEquals("ID", 1, tasks.getTaskID());
	 * assertEquals("Task Description", "CS2103", tasks.getTaskName()); if
	 * (tasks.getType().equals("APPOINTMENT")) { Appointment item =
	 * (Appointment) tasks; assertEquals("Start Date", "21/03/15 13:30",
	 * item.getStartDate()); assertEquals("Due Date", "22/03/15 14:30",
	 * item.getDueDateString()); } assertEquals("Task Type",
	 * TaskType.APPOINTMENT, tasks.getType()); }
	 * 
	 * @Test public void testWrongCommandEntered() {
	 * LogicMain.parseString("adds Hello", repo);
	 * assertEquals("Command does not exists.",
	 * "The command you entered does not exist.", repo.getFeedback()); }
	 * 
	 * @Test public void searchForTasks() { LogicMain.parseString("display",
	 * repo);
	 * 
	 * LogicMain.parseString("search 1", repo);
	 * 
	 * assertEquals("Added to temp list", 1, repo.getBufferSize()); }
	 */
}
