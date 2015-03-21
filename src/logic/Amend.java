package logic;

import java.util.ArrayList;

import logic.Enumerator.KEY;
import logic.Enumerator.TaskType;
import parser.Interpreter;

public class Amend {

	protected static void setCompletion(Interpreter item, ArrayList<Task> buffer) {
		int taskID = item.getTaskID();
		boolean isCompleted = item.getCompleted();

		int index = SearchEngine.searchBufferIndex(taskID, buffer);
		buffer.get(index).setIsCompleted(isCompleted);
	}

	protected static void determineAmend(Interpreter item, Memory mem) {
		KEY key = Converter.KeyConverter(item.getKey().toLowerCase());
		ArrayList<Task> buffer = mem.getBuffer();

		switch (key) {
		case TASKNAME:
			amendName(item, buffer);
			break;
		case STARTDATE:
			amendStartDate(item, buffer);
			break;
		case DUEDATE:
			amendDueDate(item, buffer);
			break;
		default:
			amendRemarks(item, buffer);
		}
	}

	private static void amendName(Interpreter item, ArrayList<Task> buffer) {
		Task task = SearchEngine.retrieveTask(item, buffer);
		task.setTaskName(item.getTaskName());
	}

	/*
	 * Determines the task type. If appointment, start date is concerned. If
	 * deadline, we will store the existing data into an new appointment object.
	 * Floating tasks are not allowed.
	 */

	private static void amendStartDate(Interpreter item, ArrayList<Task> buffer) {
		Task task = SearchEngine.retrieveTask(item, buffer);

		if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) task;
			appt.setStartDate(item.getStartDate());
		} else if (task.getType().equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) task;
			Appointment appt = new Appointment();

			appt.setTaskID(deadline.getTaskID());
			appt.setTaskName(deadline.getTaskName());
			appt.setStartDate(item.getStartDate());
			appt.setDate(deadline.getDate());
			appt.setRemarks(deadline.getRemarks());

			Obliterator.deleteTask(deadline.getTaskID(), buffer);
			ToBuffer.addAppointmentToBuffer(appt, buffer);
		}
	}

	/*
	 * Determines the task type. If appointment or deadline, due date is
	 * concerned. If floating, we will store the existing data into an new
	 * deadline object.
	 */

	private static void amendDueDate(Interpreter item, ArrayList<Task> buffer) {
		Task task = SearchEngine.retrieveTask(item, buffer);

		if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) task;
			appt.setDate(item.getDueDate());
		} else if (task.getType().equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) task;
			deadline.setDate(deadline.getDate());
		} else if (task.getType().equals(TaskType.FLOATING)) {
			Task tasks = task;
			Deadline deadline = new Deadline();

			deadline.setTaskID(tasks.getTaskID());
			deadline.setTaskName(tasks.getTaskName());
			deadline.setRemarks(tasks.getRemarks());
			deadline.setDate(item.getDueDate());

			Obliterator.deleteTask(tasks.getTaskID(), buffer);
			ToBuffer.addDeadlineToBuffer(deadline, buffer);
		}
	}

	private static void amendRemarks(Interpreter item, ArrayList<Task> buffer) {
		Task task = SearchEngine.retrieveTask(item, buffer);
		task.setRemarks(item.getRemarks());
	}
}
