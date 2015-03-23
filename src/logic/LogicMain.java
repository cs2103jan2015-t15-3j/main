package logic;

import java.text.ParseException;

import parser.Interpreter;
import parser.Interpreter.CommandType;
import parser.ProParser;
import storage.ProTaskStorage;

public class LogicMain {

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
		Interpreter input = new Interpreter();

		try {
			input = ProParser.parse(command);
		} catch (ParseException e) {
		}

		CommandType commandInfo = input.getCommand();

		switch (commandInfo) {
		case ADD:
			Affix.addTask(input, mem.getBuffer(), mem.numberGenerator());
			mem.setFeedbackMsg(Message.ADDED);
			break;
		case AMEND:
			Amend.determineAmend(input, mem);
			mem.setFeedbackMsg(Message.EDITED);
			break;
		case DELETE:
			Obliterator.deleteTask(input.getTaskID(), mem.getBuffer());
			mem.setFeedbackMsg(Message.DELETED);
			break;
		case CLEAR:
			Obliterator.clearTask(input, mem.getBuffer());
			mem.setFeedbackMsg(Message.DELETE_ALL);
			break;
		case DISPLAY:
			Printer.executePrint(mem.getBuffer());
			break;
		case SEARCH:
			SearchEngine.determineSearch(input.getKey(), mem);
			mem.setFeedbackMsg(Message.SEARCH);
			break;
		case SORT:
			Organizer.sort(mem);
			mem.setFeedbackMsg(Message.SORTED_BY_ID);
		case UNDO:
			if(mem.getUndoAction().isEmpty()) {
				mem.setFeedbackMsg(Message.UNDO_UNSUCCESSFUL);
			} else {
				//
			}
			break;
		case COMPLETE:
			Amend.setCompletion(input, mem.getBuffer());
			mem.setFeedbackMsg(Message.COMPLETE_TASK);
			break;
		case UNCOMPLETE:
			Amend.setCompletion(input, mem.getBuffer());
			mem.setFeedbackMsg(Message.INCOMPLETE_TASK);
			break;
		case POWERSEARCH:
			// incomplete
			break;
		case EXIT:
			System.exit(0);
		default:
			Printer.printToUser(Message.INVALID_COMMAND);
			break;
		}
		//writeToStorage(mem); incomplete
		return mem;
	}
}
