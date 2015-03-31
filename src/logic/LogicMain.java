package logic;

import java.text.ParseException;

import parser.Interpreter;
import parser.Interpreter.CommandType;
import parser.ProParser;
import storage.ProTaskStorage;
import userInterface.Logging;

public class LogicMain {
	protected static final int MESSAGE_SYSTEM_EXIT = 0;
	protected static ProTaskStorage storage;

	// For testing
	public static void main(String[] args) {

	}

	protected static void initializeStorage() {
		if (storage == null) {
			storage = new ProTaskStorage();
		}
	}

	protected static void updateStorage(Repository repo) {
		storage.updateDeleteTask(repo);
	}

	protected static void writeToStorage(Repository repo) {
		storage.writeToFile(repo);
	}

	protected static void undoAdd(Interpreter input, Repository repo) {
		History history = new History();
		history = UndoManager.pushAddToStack(input, repo.getCurrentID());
		repo.undoActionPush(history);
	}

	protected static void undoDelete(Interpreter input, Repository repo) {
		History history = new History();
		history = UndoManager.pushDeleteToStack(input, repo);
		repo.undoActionPush(history);
	}

	protected static void undoAmend(Interpreter input, Repository repo) {
		History history = new History();
		history = UndoManager.pushAmendToStack(input, repo);
		repo.undoActionPush(history);
	}
	
	protected static void undoComplete(Interpreter input, Repository repo) {
		History history = new History();
		history = UndoManager.pushCompleteToStack(input, repo);
		repo.undoActionPush(history);
	}
	
	protected static void initializeInterpreter() {
		
	}

	public static Repository executeCommand(String command, Repository repo) {
		assert (command != null);
		
		Interpreter input = new Interpreter();

		try {
			input = ProParser.parse(command);
			CommandType commandInfo = input.getCommand();

			if (commandInfo != CommandType.UNDO) {
			}

			switch (commandInfo) {
			case ADD:
				Affix.addTask(input, repo.getBuffer(), repo.numberGenerator());
				initializeStorage();
				undoAdd(input, repo);
				repo.setFeedbackMsg(input.getTaskName()
						+ Message.ADDED_SUCCESSFUL);
				writeToStorage(repo);
				break;
			case AMEND:
				undoAmend(input, repo);
				Amend.determineAmend(input, repo);
				repo.setFeedbackMsg(input.getTaskName()
						+ Message.EDITED_SUCCESSFUL);
				// storage.updateDeleteTask(repo);
				break;
			case DELETE:
				try {
					undoDelete(input, repo);
					Obliterator.deleteTask(input.getTaskID(), repo.getBuffer());
					repo.setFeedbackMsg(Message.DELETED_SUCCESSFUL);
					updateStorage(repo);
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
				try {
					SearchEngine.determineSearch(input.getKey(), repo);
				} catch (IndexOutOfBoundsException e) {
					repo.setFeedbackMsg(Message.SEARCH_IS_EMPTY);
				}
				break;
			case SORT:
				Organizer.sort(repo);
				break;
			case UNDO:
				if (repo.getUndoAction().isEmpty()) {
					repo.setFeedbackMsg(Message.UNDO_UNSUCCESSFUL);
				} else {
					UndoManager.determineUndo(repo);
					repo.setFeedbackMsg(Message.UNDO_ACTION);
					storage.updateDeleteTask(repo);
				}
				break;
			case COMPLETE:
				try {
					Amend.setCompletion(input, repo);
					repo.setFeedbackMsg(Message.COMPLETE_TASK);
					storage.updateDeleteTask(repo);
				} catch (IndexOutOfBoundsException e) {
					repo.setFeedbackMsg(input.getTaskID()
							+ Message.TASK_NOT_FOUND);
					Logging.getInputLog(Message.COMPLETE_ERROR);
				}
				break;
			case UNCOMPLETE:
				try {
					Amend.setCompletion(input, repo);
					repo.setFeedbackMsg(Message.INCOMPLETE_TASK);
					storage.updateDeleteTask(repo);
				} catch (IndexOutOfBoundsException e) {
					repo.setFeedbackMsg(input.getTaskID()
							+ Message.TASK_NOT_FOUND);
					Logging.getInputLog(Message.UNCOMPLETE_ERROR);
				}
				break;
			case POWERSEARCH:
				// incomplete
				break;
			case EXIT:
				System.exit(MESSAGE_SYSTEM_EXIT);
			default:
				break;
			}
		} catch (NullPointerException | ParseException e) {
			repo.setFeedbackMsg(Message.SPECIFIED_COMMAND);
		}
		return repo;
	}
}
