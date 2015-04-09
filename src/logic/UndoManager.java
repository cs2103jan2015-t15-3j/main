package logic;

import java.util.ArrayList;
import java.util.Iterator;

import logic.Enumerator.TaskType;
import parser.Interpreter;
import parser.Interpreter.CommandType;

public class UndoManager {

	protected static void determineUndo(Repository repo) {
		History history = repo.undoActionPop();
		System.out.println(history.getTask());

		ArrayList<Task> buffer = repo.getBuffer();

		if (history.getCommand().equals(CommandType.ADD)) {
			repo.redoActionPush(history);
			undoAddAction(history.getIndex(), buffer);
			repo.setFeedbackMsg(String.format(Message.UNDO_ACTION,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.DELETE)) {
			repo.redoActionPush(history);
			undoDeleteAction(history, buffer);

			repo.setFeedbackMsg(String.format(Message.UNDO_ACTION,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.AMEND)) {
			undoAmendAction(history, buffer);
			repo.setFeedbackMsg(String.format(Message.UNDO_ACTION,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.COMPLETE)) {
			repo.redoActionPush(history);
			undoCompleteAction(history, buffer);
			repo.setFeedbackMsg(String.format(Message.UNCOMPLETE_TASK,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.UNCOMPLETE)) {
			repo.redoActionPush(history);
			undoUncompleteAction(history, buffer);
			repo.setFeedbackMsg(String.format(Message.COMPLETE_TASK,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.CLEAR)) {
			repo.redoActionPush(history);
			undoClearAction(history.getHistoryBuffer(), repo);
			repo.setFeedbackMsg(Message.UNDO_DELETE_ALL);
		}

		if (history.getCommand().equals(CommandType.SORT)) {
			undoSortAction(history.getHistoryBuffer(), repo);
			repo.setFeedbackMsg(Message.CLEAR);
		}
	}

	protected static History pushAddToStack(Interpreter input, Repository repo) {
		History addedHistory = new History();

		Task task = SearchEngine.retrieveTask(repo.getBuffer(),
				repo.getCurrentID());
		int index = SearchEngine.searchBufferIndex(task.getTaskID(),
				repo.getBuffer());

		addedHistory.setCommand(input.getCommand());
		addedHistory.setFeedbackMsg(input.getTaskName());
		addedHistory.setIndex(index);

		if (task.getType().equals(TaskType.FLOATING)) {
			addedHistory.setTask(task);
			addedHistory.setTaskType(task.getType());

		} else if (task.getType().equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) task;
			addedHistory.setDeadline(deadline);
			addedHistory.setTaskType(task.getType());

		} else if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) task;
			addedHistory.setAppointment(appt);
			addedHistory.setTaskType(task.getType());
		}
		return addedHistory;
	}

	protected static History pushCompleteOrUncompleteToStack(Interpreter input,
			Repository repo) {
		History completedHistory = new History();

		Task task = SearchEngine.retrieveTask(repo.getBuffer(),
				input.getTaskID());
		int index = SearchEngine.searchBufferIndex(task.getTaskID(),
				repo.getBuffer());

		completedHistory.setCommand(input.getCommand());
		completedHistory.setFeedbackMsg(task.getTaskName());
		completedHistory.setIndex(index);

		return completedHistory;
	}

	protected static History pushDeleteToStack(Interpreter input,
			Repository repo) {
		History deletedHistory = new History();
		Task task = SearchEngine.retrieveTask(repo.getBuffer(),
				input.getTaskID());

		deletedHistory.setCommand(input.getCommand());
		deletedHistory.setFeedbackMsg(task.getTaskName());

		if (task.getType().equals(TaskType.FLOATING)) {
			deletedHistory.setTask(task);
			deletedHistory.setTaskType(task.getType());

		} else if (task.getType().equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) task;
			deletedHistory.setDeadline(deadline);
			deletedHistory.setTaskType(task.getType());

		} else if (task.getType().equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) task;
			deletedHistory.setAppointment(appt);
			deletedHistory.setTaskType(task.getType());
		}
		return deletedHistory;
	}

	protected static History pushAmendToStack(Interpreter input, Repository repo) {
		History amendedHistory = new History();
		Task task = SearchEngine.retrieveTask(repo.getBuffer(),
				input.getTaskID());

		amendedHistory.setCommand(input.getCommand());
		amendedHistory.setTask(task);

		System.out.println(amendedHistory.getHistoryBuffer().toString());

		return amendedHistory;
	}

	protected static History pushClearToStack(Interpreter input,
			ArrayList<Task> buffer) {
		History clearedHistory = new History();

		clearedHistory.setCommand(input.getCommand());
		clearedHistory.setHistoryBuffer(buffer);

		return clearedHistory;
	}

	protected static History pushSortToStack(Interpreter input,
			ArrayList<Task> buffer) {
		History sortedHistory = new History();

		sortedHistory.setCommand(input.getCommand());
		sortedHistory.setHistoryBuffer(buffer);

		return sortedHistory;
	}

	private static void undoAddAction(int index, ArrayList<Task> buffer) {
		buffer.remove(index);
	}

	private static void undoDeleteAction(History history, ArrayList<Task> buffer) {
		if (history.getTaskType().equals(TaskType.FLOATING)) {
			Affix.addToBuffer(history.getTask(), buffer);

		} else if (history.getTaskType().equals(TaskType.DEADLINE)) {
			Affix.addToBuffer(history.getDeadLine(), buffer);

		} else if (history.getTaskType().equals(TaskType.APPOINTMENT)) {
			Affix.addToBuffer(history.getAppointment(), buffer);
		}
	}

	private static void undoAmendAction(History history, ArrayList<Task> buffer) {
		buffer.remove(history.getIndex());
		buffer.add(history.getTask());
	}

	private static void undoCompleteAction(History history,
			ArrayList<Task> buffer) {
		buffer.get(history.getIndex()).setIsCompleted(false);
	}

	private static void undoUncompleteAction(History history,
			ArrayList<Task> buffer) {
		buffer.get(history.getIndex()).setIsCompleted(true);
	}

	private static void undoClearAction(ArrayList<Task> historyBuffer,
			Repository repo) {
		Iterator<Task> list = historyBuffer.iterator();
		ArrayList<Task> buffer = new ArrayList<Task>();

		while (list.hasNext()) {
			buffer.add(list.next());
		}
		repo.setBuffer(buffer);
	}

	private static void undoSortAction(ArrayList<Task> historyBuffer,
			Repository repo) {
		ArrayList<Task> tempBuffer = repo.getTempBuffer();

		tempBuffer.removeAll(tempBuffer);
	}
}
