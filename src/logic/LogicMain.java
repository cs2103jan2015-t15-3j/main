package logic;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;

import java.util.Scanner;

import parser.Interpreter;
import parser.Interpreter.CommandType;
import parser.ProParser;
import storage.ProTaskStorage;
import userInterface.Logging;

public class LogicMain {
	private static final int MESSAGE_SYSTEM_EXIT = 0;
	private static ProTaskStorage storage;

	public static void main(String[] args) throws ParseException {
		Repository repo = new Repository();
		Scanner sc = new Scanner(System.in);

		while (true) {
			Printer.printToUser("Command: ");
			String command = sc.nextLine();
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

	private static void catchException(Interpreter input, Repository repo) {
		repo.setIsError(input.getIsError());
		repo.setFeedbackMsg(input.getFeedbackMsg());
	}

	protected static ArrayList<Task> createTempBuffer(Repository repo) {
		ArrayList<Task> tempBuffer = new ArrayList<Task>();
		Iterator<Task> list = repo.getBuffer().iterator();

		while (list.hasNext()) {
			tempBuffer.add(list.next());
		}
		return tempBuffer;
	}

	public static Repository parseString(String command, Repository repo) {
		assert (command != null);
		Interpreter input = new Interpreter();

		try {
			input = ProParser.parse(command);
			executeCommand(input, repo);
		} catch (ParseException e) {
			Logging.getInputLog("ParseException");
		} catch (NullPointerException npe) {
			Logging.getInputLog("NullPointerException");
		}
		return repo;
	}

	private static void executeCommand(Interpreter input, Repository repo) {
		CommandType commandInfo = input.getCommand();
		boolean isError = input.getIsError();

		if (isError) {
			catchException(input, repo);
		}

		switch (commandInfo) {
		case ADD:
			if (isError) {
				catchException(input, repo);
			}
			Affix.addTask(input, repo.getBuffer(), repo.numberGenerator());
			undoAdd(input, repo);

			repo.setFeedbackMsg(String.format(Message.ADDED_SUCCESSFUL,
					input.getTaskName()));
			writeToStorage(repo);
			break;

		case AMEND:
			try {
				if (isError) {
					catchException(input, repo);
				} else {
					undoAmend(input, repo);
					Amend.determineAmend(input, repo);
					updateStorage(repo);
				}
			} catch (IndexOutOfBoundsException e) {
				repo.setFeedbackMsg(String.format(Message.TASK_NOT_FOUND,
						input.getTaskID()));
			}
			break;

		case DELETE:
			try {
				if (isError) {
					catchException(input, repo);
				} else {
					undoDelete(input, repo);
					Obliterator.deleteTask(input.getTaskID(), repo);
					updateStorage(repo);
				}
			} catch (IndexOutOfBoundsException e) {
				repo.setFeedbackMsg(String.format(Message.TASK_NOT_FOUND,
						input.getTaskID()));
			}
			break;

		case CLEAR:
			if (repo.getBuffer().isEmpty()) {
				repo.setFeedbackMsg("There is nothing to clear");
			}
			if (isError) {
				catchException(input, repo);
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
				if (isError) {
					catchException(input, repo);
				} else {
					SearchEngine.determineSearch(input.getKey(), repo);
				}
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
				undoSort(input, repo);
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
				if (isError) {
					catchException(input, repo);
				} else {
					Amend.setCompletion(input, repo);
					updateStorage(repo);
				}
			} catch (IndexOutOfBoundsException e) {
				repo.setFeedbackMsg(String.format(Message.TASK_NOT_FOUND,
						input.getTaskID()));
				Logging.getInputLog(Message.COMPLETE_ERROR);
			}
			break;

		case UNCOMPLETE:
			try {
				if (isError) {
					catchException(input, repo);
				} else {
					Amend.setCompletion(input, repo);
					updateStorage(repo);
				}
			} catch (IndexOutOfBoundsException e) {
				repo.setFeedbackMsg(String.format(Message.TASK_NOT_FOUND,
						input.getTaskID()));
				Logging.getInputLog(Message.UNCOMPLETE_ERROR);
			}
			break;

		// case MOVE:
		// if (isError) {
		// catchException(input, repo);
		// } else {
		// initializeStorage();
		// storage.moveDatabase(input.getDataBasePath());
		// }

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
		ArrayList<Task> tempBuffer = createTempBuffer(repo);

		amendedHistory = UndoManager.pushAmendToStack(input, tempBuffer);
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
		ArrayList<Task> tempBuffer = createTempBuffer(repo);

		clearedHistory = UndoManager.pushClearToStack(input, tempBuffer);
		repo.undoActionPush(clearedHistory);
	}

	private static void undoSort(Interpreter input, Repository repo) {
		History sortedHistory = new History();

		sortedHistory = UndoManager
				.pushSortToStack(input, repo.getTempBuffer());
		repo.undoActionPush(sortedHistory);
	}
}
