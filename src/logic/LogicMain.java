package logic;

import java.text.ParseException;

import parser.Interpreter;
import parser.Interpreter.CommandType;
import parser.ProParser;
import storage.ProTaskStorage;

public class LogicMain {
	protected static final int MESSAGE_SYSTEM_EXIT = 0;

	// For testing
	public static void main(String[] args) {
		Printer.printToUser(Message.WELCOME);
	}

	public Repository displayToUI(Repository mem) {
		// storage.addTasks(mem);
		// storage.loadAllTasks();
		return executeCommand("display", mem);
	}

	protected static void writeToStorage(Repository mem) {
		ProTaskStorage storage = new ProTaskStorage();
		storage.writeToFile(mem);
	}

	public static Repository executeCommand(String command, Repository mem) {
		History history = new History();
		Interpreter input = new Interpreter();
		assert (!command.isEmpty());

		try {
			input = ProParser.parse(command);
		} catch (ParseException e) {
		}

		CommandType commandInfo = input.getCommand();

		switch (commandInfo) {
		case ADD:
			Affix.addTask(input, mem.getBuffer(), mem.numberGenerator());
			history = UndoManager.pushToAdd(input.getTaskID());
			mem.undoActionPush(history);
			mem.setFeedbackMsg(input.getTaskName() + Message.ADDED);
			break;
		case AMEND:
			Amend.determineAmend(input, mem);
			mem.setFeedbackMsg(input.getTaskName() + Message.EDITED);
			break;
		case DELETE:
			if (!mem.getBuffer().contains(input.getTaskID())) {
				mem.setFeedbackMsg(Message.TASK_NOT_FOUND);
			}

			Obliterator.deleteTask(input.getTaskID(), mem.getBuffer());
			mem.setFeedbackMsg(input.getTaskName() + Message.DELETED);
			break;
		case CLEAR:
			if (mem.getBuffer().isEmpty()) {
				mem.setFeedbackMsg(Message.DELETE_ALL_UNSUCCESSFUL);
			}

			Obliterator.clearTask(input, mem.getBuffer());
			mem.setFeedbackMsg(Message.DELETE_ALL);
			break;
		case DISPLAY:
			Printer.executePrint(mem.getBuffer());
			break;
		case SEARCH:
			if (mem.getBuffer().isEmpty()) {
				mem.setFeedbackMsg(Message.SEARCH_IS_EMPTY);
			}
			SearchEngine.determineSearch(input.getKey(), mem);

			int result = mem.getTempBuffer().size();
			mem.setFeedbackMsg(result + Message.SEARCH);

			break;
		case SORT:
			if (mem.getBuffer().isEmpty()) {
				mem.setFeedbackMsg(Message.SORT_UNSUCCESSFUL);
			}

			Organizer.sort(mem);
			mem.setFeedbackMsg(Message.SORTED_BY_ID);
		case UNDO:
			if (mem.getUndoAction().isEmpty()) {
				mem.setFeedbackMsg(Message.UNDO_UNSUCCESSFUL);
			} else {
				mem.undoActionPop();
				mem.setFeedbackMsg(Message.UNDO_ACTION);
			}
			break;
		case COMPLETE:
			if (!mem.getBuffer().contains(input.getTaskID())) {
				mem.setFeedbackMsg(input.getTaskName() + Message.TASK_NOT_FOUND);
			}

			Amend.setCompletion(input, mem.getBuffer());
			mem.setFeedbackMsg(Message.COMPLETE_TASK);
			break;
		case UNCOMPLETE:

			if (!mem.getBuffer().contains(input.getTaskID())) {
				mem.setFeedbackMsg(input.getTaskName() + Message.TASK_NOT_FOUND);
			}

			Amend.setCompletion(input, mem.getBuffer());
			mem.setFeedbackMsg(Message.INCOMPLETE_TASK);
			break;
		case POWERSEARCH:
			// incomplete
			break;
		case EXIT:
			System.exit(MESSAGE_SYSTEM_EXIT);
		default:
			mem.setFeedbackMsg(Message.INVALID_COMMAND);
			break;
		}
		// writeToStorage(mem); incomplete
		return mem;
	}
}
