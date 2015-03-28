package logic;

import java.text.ParseException;
import java.util.ArrayList;

import parser.Interpreter;
import parser.Interpreter.CommandType;
import parser.ProParser;
import storage.ProTaskStorage;

public class LogicMain {
	protected static final int MESSAGE_SYSTEM_EXIT = 0;
	protected static ProTaskStorage storage;

	// For testing
	public static void main(String[] args) {

	}

	// public static Repository displayToUI(String command, Repository repo)
	// return executeCommand(command, repo);
	// }

	protected static void writeToStorage(Repository repo) {
		storage.writeToFile(repo);
	}

	public static Repository executeCommand(String command, Repository repo) {
		assert (command != null);
		History history = new History();
		Interpreter input = new Interpreter();

		try {
			input = ProParser.parse(command);
		} catch (NullPointerException | ParseException e) {
			repo.setFeedbackMsg("Hello");
		}
		CommandType commandInfo = input.getCommand();

		if (commandInfo != CommandType.UNDO) {
		}

			switch (commandInfo) {
			case ADD:
				try {
					Affix.addTask(input, repo.getBuffer(), repo.numberGenerator());
					//history = UndoManager.pushToAdd(input.getTaskID());
					if (storage == null) {
						storage = new ProTaskStorage();
					}
					//repo.undoActionPush(history);
					repo.setFeedbackMsg(input.getTaskName()
							+ Message.ADDED_SUCCESSFUL);
					writeToStorage(repo);
				} catch (NullPointerException e) {
					repo.setFeedbackMsg("Please add something");
				}
				break;
			case AMEND:
				Amend.determineAmend(input, repo);
				repo.setFeedbackMsg(input.getTaskName()
						+ Message.EDITED_SUCCESSFUL);
				// storage.updateDeleteTask(repo);
				break;
			case DELETE:
				try {
					Obliterator.deleteTask(input.getTaskID(), repo.getBuffer());
					repo.setFeedbackMsg(Message.DELETED_SUCCESSFUL);
					storage.updateDeleteTask(repo);
				} catch (IndexOutOfBoundsException e) {
					repo.setFeedbackMsg(input.getTaskID()
							+ Message.TASK_NOT_FOUND);
				}
				break;
			case CLEAR:
				if (repo.getBuffer().isEmpty()) {
					repo.setFeedbackMsg(Message.DELETE_ALL_SUCCESSFUL);
				}
				Obliterator.clearTask(input, repo.getBuffer());
				repo.setFeedbackMsg(Message.DELETE_ALL_SUCCESSFUL);
				break;
			case DISPLAY:
				Printer.executePrint(repo.getBuffer());
				break;
			case SEARCH:
				SearchEngine.determineSearch(input.getKey(), repo);
				break;
			case SORT:
				Organizer.sort(repo);
				break;
			case UNDO:
				if (repo.getUndoAction().isEmpty()) {
					repo.setFeedbackMsg(Message.UNDO_UNSUCCESSFUL);
				} else {
					UndoManager.determineUndo(repo.getBuffer());

					repo.setFeedbackMsg(Message.UNDO_ACTION);
				}
				break;
			case COMPLETE:
				Amend.setCompletion(input, repo);
				repo.setFeedbackMsg(input.getTaskName() + Message.COMPLETE_TASK);
				storage.updateDeleteTask(repo);
				break;
			case UNCOMPLETE:
				Amend.setCompletion(input, repo);
				repo.setFeedbackMsg(Message.INCOMPLETE_TASK);
				storage.updateDeleteTask(repo);
				break;
			case POWERSEARCH:
				// incomplete
				break;
			case EXIT:
				System.exit(MESSAGE_SYSTEM_EXIT);
			default:
				break;
			}
		return repo;
	}
}
