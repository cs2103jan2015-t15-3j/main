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
			undoAddAction(history.getTaskID(), buffer);
		}

		if (history.getCommand().equals(CommandType.DELETE)) {
			undoDeleteAction(history, buffer);
		}

		if (history.getCommand().equals(CommandType.AMEND)) {
			undoAmendAction(history, buffer);
		}

		if (history.getCommand().equals(CommandType.COMPLETE)) {
			undoCompleteAction(history, buffer);
		}

		if (history.getCommand().equals(CommandType.UNCOMPLETE)) {
			undoUncompleteAction(history, buffer);
		}

		if (history.getCommand().equals(CommandType.CLEAR)) {
			undoClearAction(history.getHistoryBuffer(), repo);
		}

		if (history.getCommand().equals(CommandType.SORT)) {
			undoSortAction(history.getHistoryBuffer(), repo);
		}
	}

	protected static History pushAddToStack(Interpreter input, int taskID) {
		History addedHistory = new History();
		addedHistory.setCommand(input.getCommand());
		addedHistory.setTaskID(taskID);
		return addedHistory;
	}

	protected static History pushCompleteOrUncompleteToStack(Interpreter input,
			Repository repo) {
		History completedHistory = new History();
		completedHistory.setCommand(input.getCommand());
		int index = SearchEngine.searchBufferIndex(input.getTaskID(),
				repo.getBuffer());
		completedHistory.setIndex(index);
		return completedHistory;
	}

	protected static History pushDeleteToStack(Interpreter input,
			Repository repo) {
		History deletedHistory = new History();
		deletedHistory.setCommand(input.getCommand());
		int index = SearchEngine.searchBufferIndex(input.getTaskID(),
				repo.getBuffer());

		if (repo.getBuffer().get(index).getType().equals(TaskType.FLOATING)) {
			deletedHistory.setTask(repo.getBuffer().get(index));
			deletedHistory.setTaskType(TaskType.FLOATING);

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.DEADLINE)) {
			Deadline deadline = (Deadline) repo.getBuffer().get(index);
			deletedHistory.setDeadline(deadline);

		} else if (repo.getBuffer().get(index).getType()
				.equals(TaskType.APPOINTMENT)) {
			Appointment appt = (Appointment) repo.getBuffer().get(index);
			deletedHistory.setAppointment(appt);
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

	private static void undoAddAction(int taskID, ArrayList<Task> buffer) {
		int index = SearchEngine.searchBufferIndex(taskID, buffer);
		buffer.remove(index);
	}

	private static void undoDeleteAction(History history, ArrayList<Task> buffer) {
		if (history.getTaskType().equals(TaskType.FLOATING)) {
			DataBuffer.addTaskToBuffer(history.getTask(), buffer);

		} else if (history.getTaskType().equals(TaskType.DEADLINE)) {
			DataBuffer.addDeadlineToBuffer(history.getDeadLine(), buffer);

		} else if (history.getTaskType().equals(TaskType.APPOINTMENT)) {
			DataBuffer.addAppointmentToBuffer(history.getAppointment(), buffer);
		}
	}

	private static void undoAmendAction(History history, ArrayList<Task> buffer) {
		buffer.remove(history.getIndex());

		if (history.getTaskType().equals(TaskType.FLOATING)) {
			DataBuffer.addTaskToBuffer(history.getTask(), buffer);

		} else if (history.getTaskType().equals(TaskType.DEADLINE)) {
			DataBuffer.addDeadlineToBuffer(history.getDeadLine(), buffer);

		} else if (history.getTaskType().equals(TaskType.APPOINTMENT)) {
			DataBuffer.addAppointmentToBuffer(history.getAppointment(), buffer);
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
		ArrayList<Task> buffer = repo.getBuffer();
		ArrayList<Task> tempBuffer = repo.getTempBuffer();

		tempBuffer.removeAll(tempBuffer);
	}
}
