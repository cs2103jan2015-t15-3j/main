package logic;

import java.util.ArrayList;

import logic.Enumerator.TaskType;
import parser.Interpreter.CommandType;

public class RedoManager {
	protected static void determineRedo(Repository repo) {
		ArrayList<Task> buffer = repo.getBuffer();
		History history = repo.redoActionPop();

		if (history.getCommand().equals(CommandType.ADD)) {
			redoAddAction(history, buffer);
			repo.setFeedbackMsg(String.format(Message.REDO_ACTION,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.DELETE)) {
			redoDeleteAction(history.getIndex(), buffer);
			repo.setFeedbackMsg(String.format(Message.REDO_ACTION,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.COMPLETE)) {
			redoCompleteAction(history, buffer);
			repo.setFeedbackMsg(String.format(Message.COMPLETE_TASK,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.UNCOMPLETE)) {
			redoUncompleteAction(history, buffer);
			repo.setFeedbackMsg(String.format(Message.UNCOMPLETE_TASK,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.CLEAR)) {
			redoClearAction(history.getHistoryBuffer(), repo);
			repo.setFeedbackMsg(Message.DELETE_ALL_SUCCESSFUL);
		}

		if (history.getCommand().equals(CommandType.AMEND)) {
			redoClearAction(history.getHistoryBuffer(), repo);
			repo.setFeedbackMsg(String.format(Message.EDITED_SUCCESSFUL,
					history.getFeedbackMsg()));
		}

		if (history.getCommand().equals(CommandType.SORT)) {
			redoSortAction(history.getHistoryBuffer(), repo);
			repo.setFeedbackMsg(String.format(Message.SORTED_SUCCESSFUL,
					history.getFeedbackMsg()));
		}
	}

	private static void redoAddAction(History history, ArrayList<Task> buffer) {
		if (history.getTaskType().equals(TaskType.FLOATING)) {
			Affix.addToBuffer(history.getTask(), buffer);

		} else if (history.getTaskType().equals(TaskType.DEADLINE)) {
			Affix.addToBuffer(history.getDeadLine(), buffer);

		} else if (history.getTaskType().equals(TaskType.APPOINTMENT)) {
			Affix.addToBuffer(history.getAppointment(), buffer);
		}
	}

	private static void redoDeleteAction(int index, ArrayList<Task> buffer) {
		buffer.remove(index);
	}

	private static void redoCompleteAction(History history,
			ArrayList<Task> buffer) {
		buffer.get(history.getIndex()).setIsCompleted(true);
	}

	private static void redoUncompleteAction(History history,
			ArrayList<Task> buffer) {
		buffer.get(history.getIndex()).setIsCompleted(false);
	}

	private static void redoClearAction(ArrayList<Task> historyBuffer,
			Repository repo) {
		historyBuffer.removeAll(historyBuffer);
		repo.getBuffer().removeAll(repo.getBuffer());
	}

	private static void redoSortAction(ArrayList<Task> historyBuffer,
			Repository repo) {
		repo.setTempBuffer(historyBuffer);
	}
}
