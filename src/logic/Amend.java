package logic;

import java.util.ArrayList;

import logic.Enumerator.KEY;
import logic.Enumerator.TaskType;
import parser.Interpreter;

//@author A0112643R

public class Amend {

	protected static void setCompletion(Interpreter item, Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();

		int taskID = item.getTaskID();
		int index = SearchEngine.searchBufferIndex(taskID, buffer);

		String taskName = buffer.get(index).getTaskName();
		boolean isCompleted = item.getCompleted();
		boolean checkCompleted = buffer.get(index).getCompleted();

		if (isCompleted == false && checkCompleted == false) {
			repo.setFeedbackMsg(String.format(Message.UNCOMPLETED_TASK,
					taskName));

		} else if (isCompleted == true && checkCompleted == true) {
			repo.setFeedbackMsg(String.format(Message.COMPLETED_TASK, taskName));

		} else if (isCompleted == true && checkCompleted == false) {
			LogicMain.undoCompleteOrUncomplete(item, repo);
			buffer.get(index).setIsCompleted(isCompleted);
			repo.setFeedbackMsg(String.format(Message.COMPLETE_TASK, taskName));

		} else {
			LogicMain.undoCompleteOrUncomplete(item, repo);
			buffer.get(index).setIsCompleted(isCompleted);
			repo.setFeedbackMsg(String
					.format(Message.UNCOMPLETE_TASK, taskName));
		}
	}

	protected static void determineAmend(Interpreter item, Repository repo) {
		if (item.getIsEditTaskName() == true)
			amendName(item, repo);

		if (item.getIsEditStartDate() == true)
			amendStartDate(item, repo);

		if (item.getIsEditDueDate() == true)
			amendDueDate(item, repo);

		if (item.getIsRemarks() == true)
			amendRemarks(item, repo);
	}

	private static void amendName(Interpreter item, Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();
		// History amendedHistory = new History();

		// amendedHistory = UndoManager.pushAmendToStack(item,
		// LogicMain.createTempBuffer(repo));
		// System.out.println(amendedHistory.getHistoryBuffer());

		Task task = SearchEngine.retrieveTask(buffer, item.getTaskID());
		// repo.undoActionPush(amendedHistory);
		task.setTaskName(item.getTaskName());
		repo.setFeedbackMsg(String.format(Message.EDITED_SUCCESSFUL,
				item.getTaskID()));

		// System.out.println(amendedHistory.getHistoryBuffer());
	}

	/*
	 * Determines the task type. If appointment, start date is concerned. If
	 * deadline, we will store the existing data into an new appointment object.
	 * Floating tasks are not allowed.
	 */

	private static void amendStartDate(Interpreter item, Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();
		Task task = SearchEngine.retrieveTask(buffer, item.getTaskID());

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
		Task task = SearchEngine.retrieveTask(buffer, item.getTaskID());

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
		Task task = SearchEngine.retrieveTask(buffer, item.getTaskID());

		task.setRemarks(item.getRemarks());
	}
}
