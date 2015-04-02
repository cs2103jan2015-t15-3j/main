package logic;

import java.util.ArrayList;

import logic.Enumerator.KEY;
import logic.Enumerator.TaskType;
import parser.Interpreter;

public class Amend {

	protected static void setCompletion(Interpreter item, Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();

		int taskID = item.getTaskID();
		int index = SearchEngine.searchBufferIndex(taskID, buffer);

		String taskName = buffer.get(index).getTaskName();
		boolean isCompleted = item.getCompleted();
		boolean checkCompleted = buffer.get(index).getCompleted();

		if (isCompleted == false && checkCompleted == false) {
			repo.setFeedbackMsg(taskName + Message.UNCOMPLETED_TASK);
		} else if (isCompleted == true && checkCompleted == true) {
			repo.setFeedbackMsg(taskName + Message.COMPLETED_TASK);
		} else if (isCompleted == true && checkCompleted == false) {
			LogicMain.undoCompleteOrUncomplete(item, repo);
			buffer.get(index).setIsCompleted(isCompleted);
			repo.setFeedbackMsg(taskName + Message.COMPLETE_TASK);
		} else {
			LogicMain.undoCompleteOrUncomplete(item, repo);
			buffer.get(index).setIsCompleted(isCompleted);
			repo.setFeedbackMsg(taskName + Message.UNCOMPLETE_TASK);
		}
	}

	protected static void determineAmend(Interpreter item, Repository repo) {
		KEY key = Converter.KeyConverter(item.getKey().toLowerCase());

		switch (key) {
		case TASKNAME:
			amendName(item, repo);
			break;
		case STARTDATE:
			amendStartDate(item, repo);
			break;
		case DUEDATE:
			amendDueDate(item, repo);
			break;
		default:
			amendRemarks(item, repo);
		}
	}

	private static void amendName(Interpreter item, Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();
		Task task = SearchEngine.retrieveTask(item, buffer);
		task.setTaskName(item.getTaskName());
	}

	/*
	 * Determines the task type. If appointment, start date is concerned. If
	 * deadline, we will store the existing data into an new appointment object.
	 * Floating tasks are not allowed.
	 */

	private static void amendStartDate(Interpreter item, Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();
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

			Obliterator.deleteTask(deadline.getTaskID(), repo);
			Affix.addToBuffer(appt, buffer);
		}
	}

	/*
	 * Determines the task type. If appointment or deadline, due date is
	 * concerned. If floating, we will store the existing data into an new
	 * deadline object.
	 */

	private static void amendDueDate(Interpreter item, Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();
		Task task = SearchEngine.retrieveTask(item, buffer);

		if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) task;
			appt.setDate(item.getDueDate());
		} else if (task.getType().equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) task;
			deadline.setDate(item.getDueDate());
		} else if (task.getType().equals(TaskType.FLOATING)) {
			Task tasks = task;
			Deadline deadline = new Deadline();

			deadline.setTaskID(tasks.getTaskID());
			deadline.setTaskName(tasks.getTaskName());
			deadline.setRemarks(tasks.getRemarks());
			deadline.setDate(item.getDueDate());

			Obliterator.deleteTask(tasks.getTaskID(), repo);
			Affix.addToBuffer(deadline, buffer);
		}
	}

	private static void amendRemarks(Interpreter item, Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();
		Task task = SearchEngine.retrieveTask(item, buffer);
		task.setRemarks(item.getRemarks());
	}
}
