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
		Memory mem = new Memory();
		// Storage.openFile(InternalStorage.getFilePath(),
		// InternalStorage.getBuffer());
		toDoManager(mem);
	}

	// For testing
	public static void toDoManager(Memory mem) {
		while (true) {
			mem = executeCommand(mem.getScanner().nextLine(), mem);
			Printer.executePrint(mem.getBuffer());
		}
	}

	/*
	 * [UI --> logic --> storage(load, and update memory) --> return to logic
	 * --> return to UI Store feedback msg before return memory back to UI
	 */
	public Memory displayToUI(Memory mem) {
		// storage.addTasks(mem);
		// storage.loadAllTasks();
		return executeCommand("display", mem);
	}

	public static Memory executeCommand(String command, Memory mem) {
		ProTaskStorage initializeStorage = new ProTaskStorage();
		Interpreter input = new Interpreter();

		try {
			input = ProParser.parse(command);
		} catch (ParseException e) {
		}

		CommandType commandInfo = input.getCommand();

		switch (commandInfo) {
		case ADD:
			Affix.addTask(input, mem.getBuffer(), mem.numberGenerator());
			break;
		case AMEND:
			Amend.determineAmend(input, mem);
			break;
		case DELETE:
			Obliterator.deleteTask(input.getTaskID(), mem.getBuffer());
			break;
		case CLEAR:
			Obliterator.clearTask(input, mem.getBuffer());
			break;
		case DISPLAY:
			Printer.executePrint(mem.getBuffer());
			break;
		case SEARCH:
			SearchEngine.determineSearch(input.getKey(), mem);
			break;
		case SORT:
			Organizer.sort(mem);
		case UNDO:
			// incomplete
			break;
		case COMPLETE:
			Amend.setCompletion(input, mem.getBuffer());
			break;
		case UNCOMPLETE:
			Amend.setCompletion(input, mem.getBuffer());
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
		initializeStorage.writeToFile(mem);
		return mem;
	}
}
