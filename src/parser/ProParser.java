package parser;

import java.text.ParseException;

public class ProParser {

	public static Interpreter parse(String input) throws ParseException {
		Interpreter item = new Interpreter();
		// Split the input string and check for remarks
		if (input.contains("<")) {
			String[] splitInput = input.split("<");
			defineRemarks(item, splitInput[1]);
			String[] inputArray = splitInput[0].split(" ");
			defineCommand(item, inputArray);
		} else {
			String[] inputArray = input.split(" ");
			item.setRemarks("");
			defineCommand(item, inputArray);
		}

		return item;
	}

	private static void defineRemarks(Interpreter item, String remarks) {
		String[] remarksArray = remarks.split(">");
		item.setRemarks(remarksArray[0]);
	}

	private static void defineCommand(Interpreter item, String[] inputArray)
			throws ParseException {

		String command = inputArray[0].toLowerCase();

		switch (command) {
		case "add":
		case "a":
			item.setCommandType(Interpreter.CommandType.ADD);
			ParserAdd.addTask(item, inputArray);
			break;
		case "delete":
		case "del":
			item.setCommandType(Interpreter.CommandType.DELETE);
			ParserDelete.deleteTask(item, inputArray);
			break;
		case "clear":
		case "clr":
			item.setCommandType(Interpreter.CommandType.CLEAR);
			break;
		case "display":
		case "disp":
			item.setCommandType(Interpreter.CommandType.DISPLAY);
			break;
		case "search":
		case "s":
			item.setCommandType(Interpreter.CommandType.SEARCH);
			ParserSearch.searchTask(item, inputArray);
			break;
		case "update":
		case "upd":
			item.setCommandType(Interpreter.CommandType.AMEND);
			ParserEdit.editTask(item, inputArray);
			break;
		case "undo":
		case "un":
			item.setCommandType(Interpreter.CommandType.UNDO);
			break;
		case "complete":
		case "comp":
			item.setCommandType(Interpreter.CommandType.COMPLETE);
			ParserCompleteTask.completeTask(item, inputArray);
			break;
		case "uncomplete":
		case "uncomp":
			item.setCommandType(Interpreter.CommandType.UNCOMPLETE);
			ParserUncompleteTask.uncompleteTask(item, inputArray);
			break;
		case "powersearch":
		case "psearch":
		case "ps":
			item.setCommandType(Interpreter.CommandType.POWERSEARCH);
			ParserPowerSearch.powerSearchTask(item, inputArray);
			break;
		case "sort":
			item.setCommandType(Interpreter.CommandType.SORT);
			break;
		case "exit":
			item.setCommandType(Interpreter.CommandType.EXIT);
			break;
		}
	}
}
