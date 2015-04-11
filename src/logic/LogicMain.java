package logic;

import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Scanner;

import logic.Enumerator.ErrorType;
import logic.Enumerator.TaskType;
import parser.Interpreter;
import parser.Interpreter.CommandType;
import parser.ProParser;
import storage.ProTaskStorage;
import userInterface.Logging;

public class LogicMain {
	private static final int MESSAGE_SYSTEM_EXIT = 0;
	private static ProTaskStorage storage;
	private static Repository repo;

	public static void main(String[] args) throws ParseException {
		Repository repo = new Repository();
		Scanner sc = new Scanner(System.in);
		while (true) {
			Printer.printToUser("Command: ");
			String sentence = sc.nextLine(); //
			parseString(sentence, repo);
			Printer.executePrint(repo.getBuffer());
			System.out.println(repo.getBuffer().size());
			Printer.printToUser(repo.getFeedback());
		}

	}

	private static void initializeStorage() {
		if (storage == null) {
			storage = new ProTaskStorage();
		}
	}

	public static Repository loadStorage() throws FileNotFoundException {
		initializeStorage();
		return storage.getAllTasks();
	}

	private static void updateStorageToClear(Repository repo) {
		try {
			initializeStorage();
			if (repo == null) {
				repo = new Repository();
			}
			storage.clearAllTasks(repo);
		} catch (FileNotFoundException e) {
			Logging.getInputLog(Message.FILE_INEXISTS);
		}
	}

	private static void updateStorage(Repository repo) {
		storage.updateDeleteTask(repo);
	}

	private static void writeToStorage(Repository repo) {
		storage.writeToFile(repo);
	}

	public static Repository parseString(String command, Repository repo) {
		assert (command != null);
		Interpreter input = new Interpreter();

		try {
			input = ProParser.parse(command);
			executeCommand(input, repo);
		} catch (ParseException npe) {
			Logging.getInputLog("ParseException");
		} catch (NullPointerException e) {
			Logging.getInputLog("NullPointerException");
		}
		return repo;
	}

	private static void executeCommand(Interpreter input, Repository repo) {
		CommandType commandInfo = input.getCommand();

		switch (commandInfo) {
		case ADD:
			Affix.addTask(input, repo.getBuffer(), repo.numberGenerator());
			undoAdd(input, repo);

			repo.setFeedbackMsg(String.format(Message.ADDED_SUCCESSFUL,
					input.getTaskName()));
			writeToStorage(repo);
			break;

		case AMEND:
			try {
				undoAmend(input, repo);
				Amend.determineAmend(input, repo);
				updateStorage(repo);
			} catch (IndexOutOfBoundsException e) {
				repo.setFeedbackMsg(String.format(Message.TASK_NOT_FOUND,
						input.getTaskID()));
			}
			break;

		case DELETE:
			try {
				undoDelete(input, repo);
				Obliterator.deleteTask(input.getTaskID(), repo);
				updateStorage(repo);
				if (input.getErrorType().equals(ErrorType.INVALID_TEXT)) {
					repo.setFeedbackMsg("HI");
				}
			} catch (IndexOutOfBoundsException e) {
				repo.setFeedbackMsg(String.format(Message.TASK_NOT_FOUND,
						input.getTaskID()));
			}
			if (input.getErrorType().equals(ErrorType.INVALID_ID)) {
				repo.setFeedbackMsg(Message.SPECIFIED_COMMAND);
			}
			break;

		case CLEAR:
			if (repo.getBuffer().isEmpty()) {
				repo.setFeedbackMsg("There is nothing to clear");
			} else {
				undoClear(input, repo);
				Obliterator.determineClear(input, repo.getBuffer());
				repo.setFeedbackMsg(Message.DELETE_ALL_SUCCESSFUL);
				updateStorageToClear(repo);
			}
			break;

		case DISPLAY:
			repo.setFeedbackMsg(Message.CLEAR);
			break;

		case SEARCH:
			try {
				SearchEngine.determineSearch(input.getKey(), repo);
			} catch (IndexOutOfBoundsException e) {
				repo.setFeedbackMsg(String.format(Message.TASK_NOT_FOUND,
						input.getKey()));
			}
			break;

		case SORT:
			if (repo.getBuffer().size() <= 0) {
				repo.setFeedbackMsg(Message.SORT_UNSUCCESSFUL);
			} else {
				Organizer.sort(repo);
				repo.setFeedbackMsg(Message.SORTED_SUCCESSFUL);
			}
			break;

		case UNDO:
			try {
				UndoManager.determineUndo(repo);
				updateStorage(repo);
			} catch (EmptyStackException e) {
				repo.setFeedbackMsg(Message.UNDO_UNSUCCESSFUL);
			}
			break;

		case REDO:
			try {
				RedoManager.determineRedo(repo);
				updateStorage(repo);
			} catch (EmptyStackException e) {
				repo.setFeedbackMsg(Message.REDO_UNSUCCESSFUL);
			}
			break;

		case COMPLETE:
			try {
				Amend.setCompletion(input, repo);
				updateStorage(repo);
			} catch (IndexOutOfBoundsException e) {
				repo.setFeedbackMsg(String.format(Message.TASK_NOT_FOUND,
						input.getTaskID()));
				Logging.getInputLog(Message.COMPLETE_ERROR);
			}
			break;

		case UNCOMPLETE:
			try {
				Amend.setCompletion(input, repo);
				updateStorage(repo);
			} catch (IndexOutOfBoundsException e) {
				repo.setFeedbackMsg(String.format(Message.TASK_NOT_FOUND,
						input.getTaskID()));
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
	}

	private static void undoAdd(Interpreter input, Repository repo) {
		History addedHistory = new History();
		addedHistory = UndoManager.pushAddToStack(input, repo);
		repo.undoActionPush(addedHistory);
	}

	private static void undoDelete(Interpreter input, Repository repo) {
		History deletedHistory = new History();
		deletedHistory = UndoManager.pushDeleteToStack(input, repo);
		repo.undoActionPush(deletedHistory);
	}

	private static Repository undoAmend(Interpreter input, Repository repo) {
		History amendedHistory = new History();
		amendedHistory = UndoManager.pushAmendToStack(input, repo);
		repo.undoActionPush(amendedHistory);
		return repo;
	}

	protected static void undoCompleteOrUncomplete(Interpreter input,
			Repository repo) {
		History completedHistory = new History();
		completedHistory = UndoManager.pushCompleteOrUncompleteToStack(input,
				repo);
		repo.undoActionPush(completedHistory);
	}

	private static void undoClear(Interpreter input, Repository repo) {
		History clearedHistory = new History();
		ArrayList<Task> tempBuffer = new ArrayList<Task>();
		Iterator<Task> bufferList = repo.getBuffer().iterator();
		while (bufferList.hasNext()) {
			tempBuffer.add(bufferList.next());
		}
		clearedHistory = UndoManager.pushClearToStack(input, tempBuffer);
		repo.undoActionPush(clearedHistory);
	}
}
