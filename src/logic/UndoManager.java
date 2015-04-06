package logic;

import java.util.ArrayList;
import java.util.Iterator;

import logic.Enumerator.TaskType;
import parser.Interpreter;
import parser.Interpreter.CommandType;

public class UndoManager {

	protected static void determineUndo(Repository repo) {
		History history = repo.undoActionPop();
		ArrayList<Task> buffer = repo.getBuffer();

		if (history.getCommand().equals(CommandType.ADD)) {
			repo.redoActionPush(history);
			undoAddAction(history.getIndex(), buffer);
			repo.setFeedbackMsg(history.getFeedbackMsg() + Message.UNDO_ACTION);
		}

		if (history.getCommand().equals(CommandType.DELETE)) {
			repo.redoActionPush(history);
			undoDeleteAction(history, buffer);
			repo.setFeedbackMsg(history.getFeedbackMsg() + Message.UNDO_ACTION);
		}

		if (history.getCommand().equals(CommandType.AMEND)) {
			undoAmendAction(history, buffer);
			repo.setFeedbackMsg(history.getFeedbackMsg() + Message.UNDO_ACTION);
		}

		if (history.getCommand().equals(CommandType.COMPLETE)) {
			repo.redoActionPush(history);
			undoCompleteAction(history, buffer);
			repo.setFeedbackMsg(history.getFeedbackMsg()
					+ Message.UNCOMPLETE_TASK);
		}

		if (history.getCommand().equals(CommandType.UNCOMPLETE)) {
			repo.redoActionPush(history);
			undoUncompleteAction(history, buffer);
			repo.setFeedbackMsg(history.getFeedbackMsg()
					+ Message.COMPLETE_TASK);
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
		int taskID = repo.getCurrentID();

		int index = SearchEngine.searchBufferIndex(taskID, repo.getBuffer());

		addedHistory.setCommand(input.getCommand());
		addedHistory.setIndex(index);
		addedHistory.setFeedbackMsg(input.getTaskName());

		if (repo.getBuffer().get(index).getType().equals(TaskType.FLOATING)) {
			addedHistory.setTask(repo.getBuffer().get(index));
			addedHistory.setTaskType(repo.getBuffer().get(index).getType());

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) repo.getBuffer().get(index);
			addedHistory.setDeadline(deadline);
			addedHistory.setTaskType(repo.getBuffer().get(index).getType());

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) repo.getBuffer().get(index);
			addedHistory.setAppointment(appt);
			addedHistory.setTaskType(repo.getBuffer().get(index).getType());
		}
		return addedHistory;
	}

	protected static History pushCompleteOrUncompleteToStack(Interpreter input,
			Repository repo) {
		History completedHistory = new History();
		int index = SearchEngine.searchBufferIndex(input.getTaskID(),
				repo.getBuffer());
		String taskName = repo.getBuffer().get(index).getTaskName();

		completedHistory.setCommand(input.getCommand());
		completedHistory.setIndex(index);
		completedHistory.setFeedbackMsg(taskName);

		return completedHistory;
	}

	protected static History pushDeleteToStack(Interpreter input,
			Repository repo) {
		History deletedHistory = new History();
		int index = SearchEngine.searchBufferIndex(input.getTaskID(),
				repo.getBuffer());
		String taskName = repo.getBuffer().get(index).getTaskName();

		deletedHistory.setCommand(input.getCommand());
		deletedHistory.setFeedbackMsg(taskName);
		deletedHistory.setIndex(index);

		if (repo.getBuffer().get(index).getType().equals(TaskType.FLOATING)) {
			deletedHistory.setTask(repo.getBuffer().get(index));
			deletedHistory.setTaskType(repo.getBuffer().get(index).getType());

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) repo.getBuffer().get(index);
			deletedHistory.setDeadline(deadline);
			deletedHistory.setTaskType(repo.getBuffer().get(index).getType());

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) repo.getBuffer().get(index);
			deletedHistory.setAppointment(appt);
			deletedHistory.setTaskType(repo.getBuffer().get(index).getType());
		}

		return deletedHistory;
	}

	protected static History pushAmendToStack(Interpreter input, Repository repo) {
		History amendedHistory = new History();
		int index = SearchEngine.searchBufferIndex(input.getTaskID(),
				repo.getBuffer());

		amendedHistory.setCommand(input.getCommand());
		amendedHistory.setIndex(index);
		amendedHistory.setTaskType(input.getType());

		if (repo.getBuffer().get(index).getType().equals(TaskType.FLOATING)) {
			amendedHistory.setTask(repo.getBuffer().get(index));
			amendedHistory.setTaskType(TaskType.FLOATING);
		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) repo.getBuffer().get(index);
			amendedHistory.setDeadline(deadline);

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) repo.getBuffer().get(index);
			amendedHistory.setAppointment(appt);
		}

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

		if (history.getTaskType().equals(TaskType.FLOATING)) {
			Affix.addToBuffer(history.getTask(), buffer);

		} else if (history.getTaskType().equals(TaskType.DEADLINE)) {
			Affix.addToBuffer(history.getDeadLine(), buffer);

		} else if (history.getTaskType().equals(TaskType.APPOINTMENT)) {
			Affix.addToBuffer(history.getAppointment(), buffer);
		}
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
